package ar.edu.utn.frba.dds.personas_por_localidad.services;

import ar.edu.utn.frba.dds.personas_por_localidad.connectors.dtos.DireccionDTOIn;
import ar.edu.utn.frba.dds.personas_por_localidad.connectors.dtos.PersonaVulnerableDTOIn;
import ar.edu.utn.frba.dds.personas_por_localidad.connectors.personaVulnerable.PersonaVulnerableConnector;
import ar.edu.utn.frba.dds.personas_por_localidad.domain.Localidad;
import ar.edu.utn.frba.dds.personas_por_localidad.domain.PersonaVulnerable;
import ar.edu.utn.frba.dds.personas_por_localidad.repositorios.interfaces.ILocalidadesRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class PersonaVulnerableServiceTest {

    @MockBean
    private PersonaVulnerableConnector personaVulnerableConnector;

    @Autowired
    private PersonaVulnerableService personaVulnerableService;

    @Autowired
    private ILocalidadesRepository localidadesRepository;

    @MockBean
    private LocalidadService localidadService;

    @Autowired
    private ObjectMapper objectMapper;

    private List<PersonaVulnerableDTOIn> personasVulnerablesDTOIn;

    private List<PersonaVulnerable> personasVulnerables;

    Localidad lanus;

    Localidad almagro;

    DireccionDTOIn direccionLanus;

    DireccionDTOIn direccionAlmagro;

    @BeforeEach
    public void setUp() throws IOException {
        personasVulnerablesDTOIn =  objectMapper.readValue(new ClassPathResource("example-json/personaVulnerableService/persona-vulnerable-in.json").getFile(), new TypeReference<List<PersonaVulnerableDTOIn>>() {});
        personasVulnerables =  objectMapper.readValue(new ClassPathResource("example-json/personaVulnerableService/persona-vulnerable-dominio.json").getFile(), new TypeReference<List<PersonaVulnerable>>() {});
        lanus = new Localidad("Remedios de Escalada, Lanús Oeste, Buenos Aires","Lanús","Lanús","Buenos Aires","Argentina");
        almagro = new Localidad("Almagro, Ciudad Autónoma de Buenos Aires","Almagro","Comuna 5","Ciudad Autónoma de Buenos Aires","Argentina");
        direccionLanus = new DireccionDTOIn(-34.603752,-58.381692);
        direccionAlmagro = new DireccionDTOIn(-34.616822,-58.434298);
    }

    @Test
    public void procesarPersonasVulnerablesTestDeIntegracion() throws InterruptedException {
        when(localidadService.obtenerLocalidadesDondeObtuvoViandas(
                any(PersonaVulnerableDTOIn.class),
                eq(Set.of(direccionLanus))))
                .thenReturn(List.of(lanus));
        when(localidadService.obtenerLocalidadesDondeObtuvoViandas(
                any(PersonaVulnerableDTOIn.class),
                eq(Set.of(direccionAlmagro))))
                .thenReturn(List.of(almagro));
        doAnswer(invocation -> {
            Localidad localidad = invocation.getArgument(0);
            localidadesRepository.save(localidad);
            return null;
        }).when(localidadService).actualizarLocalidad(any(Localidad.class));
        when(personaVulnerableConnector.getPersonasVulnerables())
                .thenReturn(personasVulnerablesDTOIn);
        localidadesRepository.save(lanus);
        localidadesRepository.save(almagro);
        personaVulnerableService.actualizarPersonasVulnerablesDeServicio();
        List<PersonaVulnerable> personaVulnerablesProcesadas = personaVulnerableService.buscarPersonasVulnerables();
        personaVulnerablesProcesadas.sort(PersonaVulnerable::compareTo);
        personasVulnerables.sort(PersonaVulnerable::compareTo);
        assertEquals(personasVulnerables,personaVulnerablesProcesadas);
    }
}

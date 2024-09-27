package ar.edu.utn.frba.dds.personas_por_localidad.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ar.edu.utn.frba.dds.personas_por_localidad.controllers.dtos.LocalidadDTOOut;
import ar.edu.utn.frba.dds.personas_por_localidad.domain.Localidad;
import ar.edu.utn.frba.dds.personas_por_localidad.domain.PersonaVulnerable;
import ar.edu.utn.frba.dds.personas_por_localidad.repositorios.interfaces.ILocalidadesRepository;
import ar.edu.utn.frba.dds.personas_por_localidad.repositorios.interfaces.IPersonasVulnerablesRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class LocalidadServiceTest {

  @MockBean
  private ILocalidadesRepository localidadesRepository;

  @MockBean
  private IPersonasVulnerablesRepository personasVulnerablesRepository;

  @Autowired
  private LocalidadService localidadService;

  @Autowired
  private ObjectMapper objectMapper;

  private List<PersonaVulnerable> personasVulnerables;

  @BeforeEach
  public void setUp() throws IOException {
    personasVulnerables =  objectMapper.readValue(new ClassPathResource("example-json/personas-vulnerables.json").getFile(), new TypeReference<List<PersonaVulnerable>>() {});
  }

  @Test
  public void testObtenerLocalidadesConPersonas() {
    // Arrange
    Localidad localidad1 = new Localidad(1L, "Localidad1", "Ciudad1", "Partido1", "Provincia1", "Pais1", 0);
    Localidad localidad2 = new Localidad(2L, "Localidad2", "Ciudad2", "Partido2", "Provincia2", "Pais2", 0);
    PersonaVulnerable persona1 = new PersonaVulnerable("Nombre1", "Apellido1", null, null, "123", Arrays.asList(localidad1, localidad2));
    PersonaVulnerable persona2 = new PersonaVulnerable("Nombre2", "Apellido2", null, null, "456", Collections.singletonList(localidad1));

    when(personasVulnerablesRepository.findAll()).thenReturn(Arrays.asList(persona1, persona2));

    // Act
    List<LocalidadDTOOut> result = localidadService.obtenerLocalidadesConPersonas();

    // Assert
    assertEquals(2, result.size());
    verify(personasVulnerablesRepository, times(1)).findAll();
  }

  @Test
  public void testMapToLocalidadDTOOut() {
    // Arrange
    Localidad localidad = new Localidad(1L, "Localidad1", "Ciudad1", "Partido1", "Provincia1", "Pais1", 0);
    List<String> personas = Arrays.asList("Persona1", "Persona2");
    Map<Localidad, List<String>> personasPorLocalidad = new HashMap<>();
    personasPorLocalidad.put(localidad, personas);

    // Act
    List<LocalidadDTOOut> result = localidadService.mapToLocalidadDTOOut(personasPorLocalidad);

    // Assert
    assertEquals(1, result.size());
    LocalidadDTOOut dto = result.get(0);
    assertEquals("Localidad1", dto.getNombre());
    assertEquals(2, dto.getCantidadDePersonas());
    assertEquals(personas, dto.getNombresDePersonasVulnerables());
  }

  @Test
  public void PersonasPorLocalidad(){
    LocalidadDTOOut localidadDTOOut = new LocalidadDTOOut("Localidad1", "Ciudad1", "Partido1",
        "Provincia1", "Pais1", 2, List.of("Juan", "Maria"));
    LocalidadDTOOut localidadDTOOut2 = new LocalidadDTOOut("Localidad2", "Ciudad2", "Partido2",
        "Provincia2", "Pais2", 1, List.of("Juan"));
    LocalidadDTOOut localidadDTOOut3 = new LocalidadDTOOut("Localidad3", "Ciudad3", "Partido3",
        "Provincia3", "Pais3", 1, List.of("Carlos"));
    List<LocalidadDTOOut> localidades = new ArrayList<>();
    localidades.add(localidadDTOOut3);
    localidades.add(localidadDTOOut2);
    localidades.add(localidadDTOOut);

    when(personasVulnerablesRepository.findAll()).thenReturn(personasVulnerables);
    List<LocalidadDTOOut> result = localidadService.obtenerLocalidadesConPersonas();
    assertEquals(localidades, result);
  }
}

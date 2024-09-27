package ar.edu.utn.frba.dds.personas_por_localidad.services;

import ar.edu.utn.frba.dds.personas_por_localidad.connectors.dtos.PersonaVulnerableDTOIn;
import ar.edu.utn.frba.dds.personas_por_localidad.connectors.personaVulnerable.PersonaVulnerableConnector;
import ar.edu.utn.frba.dds.personas_por_localidad.domain.PersonaVulnerable;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class PersonaVulnerableServiceTest {

    @MockBean
    private PersonaVulnerableConnector personaVulnerableConnector;

    @Autowired
    private PersonaVulnerableService personaVulnerableService;

    @Autowired
    private LocalidadService localidadService;

    @Autowired
    private ObjectMapper objectMapper;

    private List<PersonaVulnerableDTOIn> personasVulnerablseDTOIn;

    @BeforeEach
    public void setUp() throws IOException {
        personasVulnerablseDTOIn =  objectMapper.readValue(new ClassPathResource("example-json/persona-vulnerable-in.json").getFile(), new TypeReference<List<PersonaVulnerableDTOIn>>() {});
    }
}

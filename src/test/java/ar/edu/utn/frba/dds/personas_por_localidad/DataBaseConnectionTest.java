package ar.edu.utn.frba.dds.personas_por_localidad;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ar.edu.utn.frba.dds.personas_por_localidad.domain.PersonaVulnerable;
import ar.edu.utn.frba.dds.personas_por_localidad.repositorios.interfaces.IPersonasVulnerablesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
public class DataBaseConnectionTest {
  @Autowired
  private IPersonasVulnerablesRepository repository;

  @Test
  public void testDatabaseConnection() {
    long count = repository.count();
    assertTrue(count >= 0);
  }
}

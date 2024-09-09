package cron;

import ar.edu.utn.frba.dds.personas_por_localidad.connectors.personaVulnerable.PersonaVulnerableConnector;
import ar.edu.utn.frba.dds.personas_por_localidad.services.PersonaVulnerableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Refresh {

  private PersonaVulnerableService personaVulnerableService;

  @Autowired
  public Refresh(PersonaVulnerableService personaVulnerableService) {
    this.personaVulnerableService = personaVulnerableService;
  }

  @Scheduled(cron = "0 0 0 * * *")
  public void ejecutarRefresh() {
    System.out.println("Ejecutando refresh");
    personaVulnerableService.obtenerPersonasVulnerablesDeServicio();
  }

}

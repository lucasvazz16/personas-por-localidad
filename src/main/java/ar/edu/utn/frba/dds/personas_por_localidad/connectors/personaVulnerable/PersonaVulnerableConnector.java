package ar.edu.utn.frba.dds.personas_por_localidad.connectors.personaVulnerable;

import ar.edu.utn.frba.dds.personas_por_localidad.connectors.dtos.PersonaVulnerableDTOIn;
import ar.edu.utn.frba.dds.personas_por_localidad.excepciones.FalloConsultaServicioExternoException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class PersonaVulnerableConnector {

  private final RestClient restClient;

  @Autowired
  public PersonaVulnerableConnector(@Qualifier("personaVulnerableUrl") RestClient restClient) {
    this.restClient = restClient;
  }

  public List<PersonaVulnerableDTOIn> getPersonasVulnerables() {
    ResponseEntity<List<PersonaVulnerableDTOIn>> response = restClient.get()
        .uri("/personasVulerables")
        .retrieve()
        .toEntity(new ParameterizedTypeReference<List<PersonaVulnerableDTOIn>>() {
        });
    return comprobarRespuesta(response);
  }

  private <T> T comprobarRespuesta(ResponseEntity<T> response) {
    if (response.getStatusCode().is2xxSuccessful()) {
      return response.getBody();
    } else {
      throw new FalloConsultaServicioExternoException("Error al obtener las personas vulnerables del servicio externo");
    }
  }


}

package ar.edu.utn.frba.dds.personas_por_localidad.connectors.localidades;

import ar.edu.utn.frba.dds.personas_por_localidad.connectors.dtos.UbicacionDTOIn;
import ar.edu.utn.frba.dds.personas_por_localidad.excepciones.FalloConsultaServicioExternoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class LocalidadConnector {

  @Value("${query-param-zoom-localidades}")
  private String zoom;

  private final RestClient restClient;

  @Autowired
  public LocalidadConnector(@Qualifier("localidadesRestClient") RestClient restClient) {
    this.restClient = restClient;
  }

  public UbicacionDTOIn getLocalidad(Double latitud, Double longitud) {
    ResponseEntity<UbicacionDTOIn> response = restClient.get()
        .uri(uriBuilder -> uriBuilder.path("/reverse")
            .queryParam("lat", latitud)
            .queryParam("lon", longitud)
            .queryParam("zoom", zoom)
            .build())
        .retrieve()
        .toEntity(UbicacionDTOIn.class);

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

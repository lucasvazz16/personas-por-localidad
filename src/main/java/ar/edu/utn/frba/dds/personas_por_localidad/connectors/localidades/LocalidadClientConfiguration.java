package ar.edu.utn.frba.dds.personas_por_localidad.connectors.localidades;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class LocalidadClientConfiguration {

  @Value("${localidades-base.url}")
  private String url;


  @Bean(name = "localidadesRestClient")
  public RestClient restClient() {
    return RestClient.builder()
        .baseUrl(url)
        .build();
  }
}

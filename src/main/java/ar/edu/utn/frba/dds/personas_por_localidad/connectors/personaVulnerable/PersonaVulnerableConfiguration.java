package ar.edu.utn.frba.dds.personas_por_localidad.connectors.personaVulnerable;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class PersonaVulnerableConfiguration {

  @Value("${persona-vulnerable.url}")
  private String url;

  @Bean(name = "personaVulnerableUrl")
  public RestClient restClient() {
    return RestClient.builder().baseUrl(url).build();
  }
}

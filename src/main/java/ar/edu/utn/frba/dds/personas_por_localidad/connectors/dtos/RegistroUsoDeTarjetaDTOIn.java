package ar.edu.utn.frba.dds.personas_por_localidad.connectors.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter

public class RegistroUsoDeTarjetaDTOIn {


  @JsonProperty(required = true)
  private HeladeraDTOIn heladera;

  public DireccionDTOIn getDireccion(){
    return heladera.getDireccion();
  }
}

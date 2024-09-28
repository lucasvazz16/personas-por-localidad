package ar.edu.utn.frba.dds.personas_por_localidad.connectors.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
@Getter
public class UbicacionDTOIn {


  @JsonProperty(required = true, value = "display_name")
  private String nombre;

  @JsonProperty(required = true, value = "address")
  private LocalidadDTOIn localidad;
}

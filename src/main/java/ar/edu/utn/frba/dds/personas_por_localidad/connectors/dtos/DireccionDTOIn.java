package ar.edu.utn.frba.dds.personas_por_localidad.connectors.dtos;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonAutoDetect
public class DireccionDTOIn {

  private Double latitud;
  private Double longitud;

}

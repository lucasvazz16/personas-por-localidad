package ar.edu.utn.frba.dds.personas_por_localidad.connectors.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DireccionDTOIn {

  private Double latitud;
  private Double longitud;

}

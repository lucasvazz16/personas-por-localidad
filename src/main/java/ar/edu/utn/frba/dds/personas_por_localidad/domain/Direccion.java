package ar.edu.utn.frba.dds.personas_por_localidad.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Direccion {

  private Double latitud;
  private Double longitud;

}

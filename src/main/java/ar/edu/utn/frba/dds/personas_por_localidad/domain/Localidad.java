package ar.edu.utn.frba.dds.personas_por_localidad.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Localidad {

  private Long id;
  private String nombre;
  private String ciudad;
  private String partido;
  private String provincia;
  private String pais;
  private Integer cantidadDePersonasVulnerablesQueSolicitaronVianda;

  public Localidad(String nombre, String ciudad, String partido, String provincia, String pais) {
    this.nombre = nombre;
    this.ciudad = ciudad;
    this.partido = partido;
    this.provincia = provincia;
    this.pais = pais;
    this.cantidadDePersonasVulnerablesQueSolicitaronVianda = 0;
  }

  public void incrementarCantidadDePersonas(){
    this.cantidadDePersonasVulnerablesQueSolicitaronVianda++;
  }

}

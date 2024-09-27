package ar.edu.utn.frba.dds.personas_por_localidad.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "localidades")
public class Localidad {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(columnDefinition = "VARCHAR(255)")
  private String nombre;
  @Column(columnDefinition = "VARCHAR(50)")
  private String ciudad;
  @Column(columnDefinition = "VARCHAR(50)")
  private String partido;
  @Column(columnDefinition = "VARCHAR(50)")
  private String provincia;
  @Column(columnDefinition = "VARCHAR(50)")
  private String pais;
  @Column(columnDefinition = "INTEGER")
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

  public void incrementarCantidadDePersonas(Integer cant){
    this.cantidadDePersonasVulnerablesQueSolicitaronVianda += cant;
  }

}

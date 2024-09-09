package ar.edu.utn.frba.dds.personas_por_localidad.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@Embeddable
public class Direccion {


  @Column(columnDefinition = "DECIMAL(10,6)")
  private Double latitud;
  @Column(columnDefinition = "DECIMAL(10,6)")
  private Double longitud;



}

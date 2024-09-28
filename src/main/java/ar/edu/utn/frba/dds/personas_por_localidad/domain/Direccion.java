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

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@Embeddable
public class Direccion {


    @Column(columnDefinition = "DECIMAL(10,10)")
    private Double latitud;
    @Column(columnDefinition = "DECIMAL(10,10)")
    private Double longitud;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Direccion direccion)) return false;
        return Objects.equals(latitud, direccion.latitud) && Objects.equals(longitud, direccion.longitud);
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitud, longitud);
    }
}

package ar.edu.utn.frba.dds.personas_por_localidad.domain;

import ar.edu.utn.frba.dds.personas_por_localidad.connectors.dtos.DireccionDTOIn;
import ar.edu.utn.frba.dds.personas_por_localidad.connectors.dtos.TarjetaDTOIn;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@JsonAutoDetect
@Entity
@Table(name = "personas_vulnerables")
public class PersonaVulnerable {

    @Column(columnDefinition = "VARCHAR(50)")
    private String nombre;
    @Column(columnDefinition = "VARCHAR(50)")
    private String apellido;
    @Embedded
    private Direccion direccion;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)")
    private TipoDocumento tipoDocumento;
    @Id
    @Column(columnDefinition = "VARCHAR(50)")
    private String numeroDeDocumento;
    @OneToMany
    @JoinColumn(name = "persona_vulnerable_id", referencedColumnName = "numeroDeDocumento")
    private List<Localidad> localidadesDondeObtuvoViandas;

    public void agregarLocalidad(Localidad localidad) {
        localidadesDondeObtuvoViandas.add(localidad);
    }

    public Boolean personaTieneLocalidad(Localidad localidad) {
        return localidadesDondeObtuvoViandas.contains(localidad);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonaVulnerable that)) return false;
        return Objects.equals(nombre, that.nombre) && Objects.equals(apellido, that.apellido) && Objects.equals(direccion, that.direccion) && tipoDocumento == that.tipoDocumento && Objects.equals(numeroDeDocumento, that.numeroDeDocumento) && Objects.equals(localidadesDondeObtuvoViandas, that.localidadesDondeObtuvoViandas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, apellido, direccion, tipoDocumento, numeroDeDocumento, localidadesDondeObtuvoViandas);
    }
}

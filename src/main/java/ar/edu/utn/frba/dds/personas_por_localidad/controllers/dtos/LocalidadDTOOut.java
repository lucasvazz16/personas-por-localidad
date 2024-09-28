package ar.edu.utn.frba.dds.personas_por_localidad.controllers.dtos;

import ar.edu.utn.frba.dds.personas_por_localidad.domain.Localidad;
import java.util.ArrayList;
import lombok.*;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class LocalidadDTOOut {

  private String nombre;
  private String ciudad;
  private String partido;
  private String provincia;
  private String pais;
  private Integer cantidadDePersonas;
  private List<String> nombresDePersonasVulnerables;

  public LocalidadDTOOut(Localidad localidad) {
    this.nombre = localidad.getNombre();
    this.ciudad = localidad.getCiudad();
    this.partido = localidad.getPartido();
    this.provincia = localidad.getProvincia();
    this.pais = localidad.getPais();
    this.cantidadDePersonas = localidad.getCantidadDePersonasVulnerablesQueSolicitaronVianda();
    this.nombresDePersonasVulnerables = new ArrayList<>();

  }

  public void agregarPersonaVulnerable(String nombrePersonaVulnerable) {
    nombresDePersonasVulnerables.add(nombrePersonaVulnerable);
  }

  public void agregarPersonasVulnerables(List<String> nombresPersonasVulnerables) {
    nombresDePersonasVulnerables.addAll(nombresPersonasVulnerables);
  }

  public void calcularCantidadDePersonas() {
    this.cantidadDePersonas = nombresDePersonasVulnerables.size();
  }

  @Override
  public String toString() {
    return "LocalidadDTOOut{" +
            "nombre='" + nombre + '\'' +
            ", ciudad='" + ciudad + '\'' +
            ", partido='" + partido + '\'' +
            ", provincia='" + provincia + '\'' +
            ", pais='" + pais + '\'' +
            ", cantidadDePersonas=" + cantidadDePersonas +
            ", nombresDePersonasVulnerables=" + nombresDePersonasVulnerables +
            '}';
  }
}

package ar.edu.utn.frba.dds.personas_por_localidad.connectors.dtos;

import ar.edu.utn.frba.dds.personas_por_localidad.domain.TipoDocumento;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonAutoDetect
public class PersonaVulnerableDTOIn {

  @NonNull
  @JsonProperty(required = true)
  private String nombre;
  @NonNull
  @JsonProperty(required = true)
  private String apellido;

  private DireccionDTOIn direccion;

  private TipoDocumento tipoDocumento;

  private String numeroDeDocumento;

  private List<PersonaVulnerableDTOIn> menoresACargo;

  private TarjetaDTOIn tarjeta;

  public Set<DireccionDTOIn> obtenerDireccionesDondeUsoTarjeta() {
    return tarjeta.getDirecciones();
  }

}

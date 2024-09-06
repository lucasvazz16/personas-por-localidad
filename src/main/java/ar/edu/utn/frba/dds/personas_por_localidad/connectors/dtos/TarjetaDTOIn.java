package ar.edu.utn.frba.dds.personas_por_localidad.connectors.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TarjetaDTOIn {

  @NonNull
  @JsonProperty(required = true)
  private String id;
  @NonNull
  @JsonProperty(required = true)
  private List<RegistroUsoDeTarjetaDTOIn> historialDeUso;

  public Set<DireccionDTOIn> getDirecciones(){
    return historialDeUso.stream()
        .map(RegistroUsoDeTarjetaDTOIn::getDireccion)
        .collect(Collectors.toSet());
  }
}

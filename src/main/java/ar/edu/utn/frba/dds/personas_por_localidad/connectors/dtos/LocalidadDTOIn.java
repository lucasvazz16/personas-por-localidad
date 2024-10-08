package ar.edu.utn.frba.dds.personas_por_localidad.connectors.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
@NonNull
@Getter
public class LocalidadDTOIn {

  @NonNull
  @JsonAlias({"city", "town"})
  @JsonProperty(required = true)
  private String ciudad;

  @NonNull
  @JsonProperty(required = true, value = "state_district")
  private String partido;

  @NonNull
  @JsonProperty(required = true, value = "state")
  private String provincia;

  @NonNull
  @JsonProperty(required = true, value = "country")
  private String pais;

  public @NonNull String getCiudad() {
    return ciudad;
  }

  public @NonNull String getPartido() {
    return partido;
  }

  public @NonNull String getProvincia() {
    return provincia;
  }

  public @NonNull String getPais() {
    return pais;
  }


}

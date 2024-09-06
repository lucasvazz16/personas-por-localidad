package ar.edu.utn.frba.dds.personas_por_localidad.domain;

import ar.edu.utn.frba.dds.personas_por_localidad.connectors.dtos.DireccionDTOIn;
import ar.edu.utn.frba.dds.personas_por_localidad.connectors.dtos.TarjetaDTOIn;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PersonaVulnerable {
  private String nombre;
  private String apellido;
  private Direccion direccion;
  private TipoDocumento tipoDocumento;
  private String numeroDeDocumento;
  private List<String> localidadesDondeObtuvoViandas;


}

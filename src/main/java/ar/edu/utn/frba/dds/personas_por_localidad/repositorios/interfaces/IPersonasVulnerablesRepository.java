package ar.edu.utn.frba.dds.personas_por_localidad.repositorios.interfaces;

import ar.edu.utn.frba.dds.personas_por_localidad.domain.PersonaVulnerable;
import java.util.List;
import java.util.Optional;

public interface IPersonasVulnerablesRepository {

  public List<PersonaVulnerable> obtenerTodas();

  public Optional<PersonaVulnerable> obtenerPorId(Long id);

  public Optional<PersonaVulnerable> obtenerPorDocumento(String documento);

  public void agregar(PersonaVulnerable personaVulnerable);

  public void eliminar(PersonaVulnerable personaVulnerable);

  public void modificar(PersonaVulnerable personaVulnerable);


  public List<PersonaVulnerable> obtenerPorLocalidad(String localidad);

  public Boolean existePersonaVulnerable(PersonaVulnerable personaVulnerable);

  public Boolean existePersonaVulnerablePorDocumento(String documento);
}

package ar.edu.utn.frba.dds.personas_por_localidad.repositorios.interfaces;

import ar.edu.utn.frba.dds.personas_por_localidad.domain.PersonaVulnerable;
import java.util.List;

public interface IPersonasVulnerablesRepository {

  public List<PersonaVulnerable> obtenerTodas();

  public void agregar(PersonaVulnerable personaVulnerable);

  public void eliminar(PersonaVulnerable personaVulnerable);

  public void modificar(PersonaVulnerable personaVulnerable);

  public PersonaVulnerable obtenerPorDocumento(String documento);

  public List<PersonaVulnerable> obtenerPorLocalidad(String localidad);

  public Boolean existePersonaVulnerable(PersonaVulnerable personaVulnerable);

  public Boolean existePersonaVulnerablePorDocumento(String documento);
}

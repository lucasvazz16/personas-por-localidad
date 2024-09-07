package ar.edu.utn.frba.dds.personas_por_localidad.repositorios.implementaciones;

import ar.edu.utn.frba.dds.personas_por_localidad.domain.PersonaVulnerable;
import ar.edu.utn.frba.dds.personas_por_localidad.repositorios.interfaces.IPersonasVulnerablesRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public class PersonasVulnerablesRepository implements IPersonasVulnerablesRepository {

  @Override
  public List<PersonaVulnerable> obtenerTodas() {
    return null;
  }

  @Override
  public Optional<PersonaVulnerable> obtenerPorId(Long id) {
    return Optional.empty();
  }

  @Override
  public void agregar(PersonaVulnerable personaVulnerable) {

  }

  @Override
  public void eliminar(PersonaVulnerable personaVulnerable) {

  }

  @Override
  public void modificar(PersonaVulnerable personaVulnerable) {

  }

  @Override
  public Optional<PersonaVulnerable> obtenerPorDocumento(String documento) {
    return null;
  }

  @Override
  public List<PersonaVulnerable> obtenerPorLocalidad(String localidad) {
    return null;
  }

  @Override
  public Boolean existePersonaVulnerable(PersonaVulnerable personaVulnerable) {
    return null;
  }

  @Override
  public Boolean existePersonaVulnerablePorDocumento(String documento) {
    return null;
  }
}

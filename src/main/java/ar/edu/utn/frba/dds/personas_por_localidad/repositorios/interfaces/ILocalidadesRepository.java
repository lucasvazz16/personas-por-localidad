package ar.edu.utn.frba.dds.personas_por_localidad.repositorios.interfaces;

import ar.edu.utn.frba.dds.personas_por_localidad.domain.Localidad;
import ar.edu.utn.frba.dds.personas_por_localidad.domain.PersonaVulnerable;
import java.util.List;

public interface ILocalidadesRepository {

  public List<Localidad> obtenerTodas();

  public void agregar(Localidad localidad);

  public void agregar(List<Localidad> localidades);

  public void eliminar(Localidad localidad);

  public void modificar(Localidad localidad);

  public Localidad obtenerPorNombre(String nombre);

  public void agregarPersonaVulnerableQueSolicitoVianda(Long id, PersonaVulnerable personaVulnerable);

  public Localidad obtenerPorId(Long id);

  public Boolean existeLocalidad(Localidad localidad);

  public Boolean existeLocalidadPorId(Long id);

  public Boolean localidadTienePersonasVulnerables(Long id,PersonaVulnerable personaVulnerable);



}

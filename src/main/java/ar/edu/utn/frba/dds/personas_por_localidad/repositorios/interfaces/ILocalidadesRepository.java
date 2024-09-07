package ar.edu.utn.frba.dds.personas_por_localidad.repositorios.interfaces;

import ar.edu.utn.frba.dds.personas_por_localidad.domain.Localidad;
import ar.edu.utn.frba.dds.personas_por_localidad.domain.PersonaVulnerable;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ILocalidadesRepository {

  public List<Localidad> obtenerTodas();

  public Optional<Localidad> obtenerPorNombre(String nombre);

  public Optional<Localidad> obtenerPorId(Long id);

  public void agregar(Localidad localidad);

  public void agregar(List<Localidad> localidades);

  public void eliminar(Localidad localidad);

  public void modificar(Localidad localidad);

  public void agregarPersonaVulnerableQueSolicitoVianda(Long id);

  public Boolean existeLocalidad(Localidad localidad);

  public Boolean existeLocalidadPorId(Long id);




}

package ar.edu.utn.frba.dds.personas_por_localidad.repositorios.implementaciones;

import ar.edu.utn.frba.dds.personas_por_localidad.domain.Localidad;
import ar.edu.utn.frba.dds.personas_por_localidad.domain.PersonaVulnerable;
import ar.edu.utn.frba.dds.personas_por_localidad.repositorios.interfaces.ILocalidadesRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public class LocalidadesRepository implements ILocalidadesRepository {

    @Override
    public List<Localidad> obtenerTodas() {
        return null;
    }

    @Override
    public void agregar(Localidad localidad) {

    }

    @Override
    public void agregar(List<Localidad> localidades) {

    }

    @Override
    public void eliminar(Localidad localidad) {

    }

    @Override
    public void modificar(Localidad localidad) {

    }

    @Override
    public void agregarPersonaVulnerableQueSolicitoVianda(Long id) {

    }

    @Override
    public Optional<Localidad> obtenerPorNombre(String nombre) {
        return null;
    }


    @Override
    public Optional<Localidad> obtenerPorId(Long id) {
        return null;
    }

    @Override
    public Boolean existeLocalidad(Localidad localidad) {
        return null;
    }

    @Override
    public Boolean existeLocalidadPorId(Long id) {
        return null;
    }

}

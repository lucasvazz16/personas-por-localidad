package ar.edu.utn.frba.dds.personas_por_localidad.repositorios.interfaces;

import ar.edu.utn.frba.dds.personas_por_localidad.domain.Localidad;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILocalidadesRepository extends JpaRepository<Localidad, Long> {


  public Optional<Localidad> findByNombre(String nombre);

  public Boolean existsByNombre(String nombre);



}

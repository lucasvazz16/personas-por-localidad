package ar.edu.utn.frba.dds.personas_por_localidad.services;

import ar.edu.utn.frba.dds.personas_por_localidad.connectors.dtos.DireccionDTOIn;
import ar.edu.utn.frba.dds.personas_por_localidad.connectors.dtos.PersonaVulnerableDTOIn;
import ar.edu.utn.frba.dds.personas_por_localidad.connectors.dtos.UbicacionDTOIn;
import ar.edu.utn.frba.dds.personas_por_localidad.connectors.localidades.LocalidadConnector;
import ar.edu.utn.frba.dds.personas_por_localidad.domain.Localidad;
import ar.edu.utn.frba.dds.personas_por_localidad.domain.PersonaVulnerable;
import ar.edu.utn.frba.dds.personas_por_localidad.repositorios.interfaces.ILocalidadesRepository;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocalidadService {

  private LocalidadConnector localidadConnector;
  private ILocalidadesRepository localidadesRepository;

  @Autowired
  public LocalidadService(LocalidadConnector localidadConnector, ILocalidadesRepository localidadesRepository) {
    this.localidadConnector = localidadConnector;
    this.localidadesRepository = localidadesRepository;
  }

  public List<UbicacionDTOIn> obtenerLocalidadesDondeObtuvoViandas(PersonaVulnerableDTOIn personaVulnerableDTO) {
    Set<DireccionDTOIn> direccionesDTO = personaVulnerableDTO.obtenerDireccionesDondeUsoTarjeta();
    return direccionesDTO.stream()
        .map(direccionDTO ->
            localidadConnector.getLocalidad(direccionDTO.getLatitud(), direccionDTO.getLongitud()))
        .collect(Collectors.toList());
  }

  public List<String> obtenerCiudades(List<UbicacionDTOIn> ubicaciones) {
    return ubicaciones.stream()
        .map(ubicacion -> ubicacion.getLocalidad().getCiudad())
        .collect(Collectors.toList());
  }


  public void guardarLocalidades(List<Localidad> localidades) {

  }

  public void guardarLocalidad(Localidad localidad) {
    if(localidadesRepository.existeLocalidad(localidad)){
      localidadesRepository.modificar(localidad);
    }
    localidadesRepository.agregar(localidad);
  }

  public void agregarPersonaVulnerableQueSolicitoVianda(Long id, PersonaVulnerable personaVulnerable) {
    Localidad localidad = localidadesRepository.obtenerPorId(id);
    localidadesRepository.agregarPersonaVulnerableQueSolicitoVianda(id, personaVulnerable);
  }



  
}

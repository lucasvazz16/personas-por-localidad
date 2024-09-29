package ar.edu.utn.frba.dds.personas_por_localidad.services;

import ar.edu.utn.frba.dds.personas_por_localidad.connectors.dtos.*;
import ar.edu.utn.frba.dds.personas_por_localidad.connectors.localidades.LocalidadConnector;
import ar.edu.utn.frba.dds.personas_por_localidad.controllers.dtos.LocalidadDTOOut;
import ar.edu.utn.frba.dds.personas_por_localidad.domain.Localidad;
import ar.edu.utn.frba.dds.personas_por_localidad.domain.PersonaVulnerable;
import ar.edu.utn.frba.dds.personas_por_localidad.repositorios.interfaces.ILocalidadesRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import ar.edu.utn.frba.dds.personas_por_localidad.repositorios.interfaces.IPersonasVulnerablesRepository;
import ar.edu.utn.frba.dds.personas_por_localidad.utils.MapperLocalidades;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocalidadService {

  private LocalidadConnector localidadConnector;
  private ILocalidadesRepository localidadesRepository;
  private IPersonasVulnerablesRepository personasVulnerablesRepository;

  @Autowired
  public LocalidadService(LocalidadConnector localidadConnector, ILocalidadesRepository localidadesRepository, IPersonasVulnerablesRepository personasVulnerablesRepository) {
    this.localidadConnector = localidadConnector;
    this.localidadesRepository = localidadesRepository;
    this.personasVulnerablesRepository = personasVulnerablesRepository;
  }

  public List<Localidad> obtenerLocalidadesDondeObtuvoViandas(PersonaVulnerableDTOIn personaVulnerableDTO, Set<DireccionDTOIn> direccionesDTO) {
    List<UbicacionDTOIn> ubicacionDTOIns = direccionesDTO.stream()
        .map(direccionDTO ->
            localidadConnector.getLocalidad(direccionDTO.getLatitud(), direccionDTO.getLongitud()))
        .collect(Collectors.toList());
    return this.transformarUbicacionesDTO(ubicacionDTOIns);
  }

  private List<Localidad> transformarUbicacionesDTO(List<UbicacionDTOIn> ubicacionesDTO) {
    List<Localidad> localidadesARetornar = new ArrayList<>();
    for (UbicacionDTOIn ubicacionDTO : ubicacionesDTO) {
      Optional<Localidad> localidad = this.obtenerLocalidadPorNombre(ubicacionDTO.getNombre());
      localidad.ifPresentOrElse(
          localidadesARetornar::add,
          () -> {
            Localidad localidadNueva = MapperLocalidades.mapToLocalidad(ubicacionDTO);
            this.guardarLocalidad(localidadNueva);
            localidadesARetornar.add(localidadNueva);
          }
      );
    }
    return localidadesARetornar;
  }


  public void guardarLocalidades(List<Localidad> localidades) {
    localidades.forEach(this::guardarLocalidad);
  }

  public void guardarLocalidad(Localidad localidad) {
    if (localidadesRepository.existsByNombre(localidad.getNombre())) {
      localidadesRepository.save(localidad);
    } else {

      localidadesRepository.save(localidad);
    }
  }

  public Optional<Localidad> obtenerLocalidadPorNombre(String nombre) {
    return localidadesRepository.findByNombre(nombre);
  }

  // Recibo un List<PersonaVulnerable>, c/PersonaVulnerable con un List<Localidad>
  // Lo convierto en un Map con Localidad como key
  // Recorro con un for el repo de Personas
  // Al Map, le agrego los nombres de las personas a c/Localidad
  public List<LocalidadDTOOut> obtenerLocalidadesConPersonas() {

    List<PersonaVulnerable> personasVulnerables = personasVulnerablesRepository.findAll();
    Map<Localidad, List<String>> personasPorLocalidad = new HashMap<>();
    for (PersonaVulnerable personaVulnerable : personasVulnerables) {
      List<Localidad> localidadesDondeFuePersona = personaVulnerable.getLocalidadesDondeObtuvoViandas();
      agregarPersonaALocalidad(personasPorLocalidad, personaVulnerable, localidadesDondeFuePersona);
    }

    return this.mapToLocalidadDTOOut(personasPorLocalidad);
  }

  private void agregarPersonaALocalidad(Map<Localidad, List<String>> personasPorLocalidad, PersonaVulnerable personaVulnerable, List<Localidad> localidadesDondeFuePersona) {
    for (Localidad localidad : localidadesDondeFuePersona) {
      if (personasPorLocalidad.containsKey(localidad)) {
        personasPorLocalidad.get(localidad).add(personaVulnerable.getNombre());
      } else {
        List<String> personas = new ArrayList<>();
        personas.add(personaVulnerable.getNombre());
        personasPorLocalidad.put(localidad, personas);
      }
    }
  }

  public List<LocalidadDTOOut> mapToLocalidadDTOOut(Map<Localidad, List<String>> personasPorLocalidad) {
    return personasPorLocalidad.entrySet().stream()
        .map(entry -> {
          LocalidadDTOOut dto = MapperLocalidades.mapToLocalidadDTOOut(entry.getKey());
          dto.agregarPersonasVulnerables(entry.getValue());
          dto.calcularCantidadDePersonas();
          return dto;
        }).collect(Collectors.toList());
  }

  public void actualizarLocalidad(Localidad localidad) {
    localidadesRepository.save(localidad);
  }
}


package ar.edu.utn.frba.dds.personas_por_localidad.services;

import ar.edu.utn.frba.dds.personas_por_localidad.connectors.dtos.DireccionDTOIn;
import ar.edu.utn.frba.dds.personas_por_localidad.connectors.personaVulnerable.PersonaVulnerableConnector;
import ar.edu.utn.frba.dds.personas_por_localidad.connectors.dtos.PersonaVulnerableDTOIn;
import ar.edu.utn.frba.dds.personas_por_localidad.domain.Localidad;
import ar.edu.utn.frba.dds.personas_por_localidad.domain.PersonaVulnerable;
import ar.edu.utn.frba.dds.personas_por_localidad.repositorios.interfaces.IPersonasVulnerablesRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import ar.edu.utn.frba.dds.personas_por_localidad.utils.MapperPersonaVulnerable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaVulnerableService {

  private PersonaVulnerableConnector personaVulnerableConnector;
  private LocalidadService localidadService;
  private IPersonasVulnerablesRepository personasVulnerablesRepository;

  @Autowired
  public PersonaVulnerableService(PersonaVulnerableConnector personaVulnerableConnector,
                                  LocalidadService localidadService,
                                  @Autowired IPersonasVulnerablesRepository personasVulnerablesRepository) {
    this.personaVulnerableConnector = personaVulnerableConnector;
    this.localidadService = localidadService;
    this.personasVulnerablesRepository = personasVulnerablesRepository;
  }

  public void actualizarPersonasVulnerablesDeServicio() {
    List<PersonaVulnerableDTOIn> personasVulnerablesDTO = personaVulnerableConnector.getPersonasVulnerables();
    personasVulnerablesDTO.forEach(this::transformarPersonaVulnerableEHijos);
  }

  private void transformarPersonaVulnerableEHijos(PersonaVulnerableDTOIn personaVulnerableDTOIn){
    List<PersonaVulnerableDTOIn> hijos = personaVulnerableDTOIn.getMenoresACargo();
    Set<DireccionDTOIn> direccionesDTO = personaVulnerableDTOIn.obtenerDireccionesDondeUsoTarjeta();
    transformarPersonasVulnerablesDTO(personaVulnerableDTOIn,direccionesDTO);
      for (PersonaVulnerableDTOIn hijo : hijos) {
          transformarPersonasVulnerablesDTO(hijo,direccionesDTO);
      }
  }

  private void transformarPersonasVulnerablesDTO(PersonaVulnerableDTOIn personaVulnerableDTO, Set<DireccionDTOIn> direccionesDTO) {
    List<Localidad> localidades = localidadService.obtenerLocalidadesDondeObtuvoViandas(personaVulnerableDTO, direccionesDTO);
    personasVulnerablesRepository.findById(personaVulnerableDTO.getNumeroDeDocumento())
        .ifPresentOrElse(
            personaVulnerable -> {
              localidades.forEach(localidad -> verificarYAgregarLocalidad(personaVulnerable, localidad));
              personasVulnerablesRepository.save(personaVulnerable);

            },
            () -> {
              PersonaVulnerable personaVulnerable = MapperPersonaVulnerable.mapToPersonaVulnerable(personaVulnerableDTO);
              localidades.forEach(personaVulnerable::agregarLocalidad);
              localidades.forEach(Localidad::incrementarCantidadDePersonas);
              personasVulnerablesRepository.save(personaVulnerable);
            }
        );

  }

  private void verificarYAgregarLocalidad(PersonaVulnerable personaVulnerable, Localidad localidad) {
    if (!personaVulnerable.personaTieneLocalidad(localidad)) {
      personaVulnerable.agregarLocalidad(localidad);
      localidad.incrementarCantidadDePersonas();
    }
  }

  public List<PersonaVulnerable> buscarPersonasVulnerables(){
    return personasVulnerablesRepository.findAll();
  }


}

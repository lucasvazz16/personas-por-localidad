package ar.edu.utn.frba.dds.personas_por_localidad.services;

import ar.edu.utn.frba.dds.personas_por_localidad.connectors.dtos.UbicacionDTOIn;
import ar.edu.utn.frba.dds.personas_por_localidad.connectors.localidades.LocalidadConnector;
import ar.edu.utn.frba.dds.personas_por_localidad.connectors.personaVulnerable.PersonaVulnerableConnector;
import ar.edu.utn.frba.dds.personas_por_localidad.connectors.dtos.DireccionDTOIn;
import ar.edu.utn.frba.dds.personas_por_localidad.connectors.dtos.PersonaVulnerableDTOIn;
import ar.edu.utn.frba.dds.personas_por_localidad.domain.Direccion;
import ar.edu.utn.frba.dds.personas_por_localidad.domain.Localidad;
import ar.edu.utn.frba.dds.personas_por_localidad.domain.PersonaVulnerable;
import ar.edu.utn.frba.dds.personas_por_localidad.repositorios.interfaces.IPersonasVulnerablesRepository;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
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
                                  IPersonasVulnerablesRepository personasVulnerablesRepository) {
    this.personaVulnerableConnector = personaVulnerableConnector;
    this.localidadService = localidadService;
    this.personasVulnerablesRepository = personasVulnerablesRepository;
  }

  public List<PersonaVulnerable> obtenerPersonasVulnerablesDeServicio() {
    List<PersonaVulnerableDTOIn> personasVulnerablesDTO = personaVulnerableConnector.getPersonasVulnerables();
  }

  private PersonaVulnerable transformarDTO(PersonaVulnerableDTOIn personaVulnerableDTO) {
    List<UbicacionDTOIn> ubicacionesDTO = localidadService.obtenerLocalidadesDondeObtuvoViandas(personaVulnerableDTO);
    List<String> ciudades = localidadService.obtenerCiudades(ubicacionesDTO);
  }


}

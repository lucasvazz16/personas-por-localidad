package ar.edu.utn.frba.dds.personas_por_localidad.services;

import ar.edu.utn.frba.dds.personas_por_localidad.connectors.personaVulnerable.PersonaVulnerableConnector;
import ar.edu.utn.frba.dds.personas_por_localidad.connectors.dtos.PersonaVulnerableDTOIn;
import ar.edu.utn.frba.dds.personas_por_localidad.domain.Localidad;
import ar.edu.utn.frba.dds.personas_por_localidad.domain.PersonaVulnerable;
import ar.edu.utn.frba.dds.personas_por_localidad.repositorios.interfaces.IPersonasVulnerablesRepository;

import java.util.List;
import java.util.stream.Collectors;

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
                                    IPersonasVulnerablesRepository personasVulnerablesRepository) {
        this.personaVulnerableConnector = personaVulnerableConnector;
        this.localidadService = localidadService;
        this.personasVulnerablesRepository = personasVulnerablesRepository;
    }

    public List<PersonaVulnerable> obtenerPersonasVulnerablesDeServicio() {
        List<PersonaVulnerableDTOIn> personasVulnerablesDTO = personaVulnerableConnector.getPersonasVulnerables();
        return personasVulnerablesDTO.stream()
                .map(this::transformarPersonasVulnerablesDTO)
                .collect(Collectors.toList());
    }

    private PersonaVulnerable transformarPersonasVulnerablesDTO(PersonaVulnerableDTOIn personaVulnerableDTO) {
        List<Localidad> localidades = localidadService.obtenerLocalidadesDondeObtuvoViandas(personaVulnerableDTO);
        PersonaVulnerable[] personaVulnerableRetorno = new PersonaVulnerable[1];
        personasVulnerablesRepository.findById(personaVulnerableDTO.getNumeroDeDocumento())
                .ifPresentOrElse(
                        personaVulnerable -> {
                            localidades.forEach(localidad -> verificarYAgregarLocalidad(personaVulnerable, localidad));
                            personasVulnerablesRepository.save(personaVulnerable);
                            personaVulnerableRetorno[0] = personaVulnerable;
                        },
                        () -> {
                            PersonaVulnerable personaVulnerable = MapperPersonaVulnerable.mapToPersonaVulnerable(personaVulnerableDTO);
                            localidades.forEach(personaVulnerable::agregarLocalidad);
                            localidades.forEach(Localidad::incrementarCantidadDePersonas);
                            personasVulnerablesRepository.save(personaVulnerable);
                            personaVulnerableRetorno[0] = personaVulnerable;
                        }
                );

        return personaVulnerableRetorno[0];
    }

    private void verificarYAgregarLocalidad(PersonaVulnerable personaVulnerable, Localidad localidad) {
        if (!personaVulnerable.personaTieneLocalidad(localidad)) {
            personaVulnerable.agregarLocalidad(localidad);
            localidad.incrementarCantidadDePersonas();
        }
    }


}

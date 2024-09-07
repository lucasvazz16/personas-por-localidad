package ar.edu.utn.frba.dds.personas_por_localidad.utils;

import ar.edu.utn.frba.dds.personas_por_localidad.connectors.dtos.PersonaVulnerableDTOIn;
import ar.edu.utn.frba.dds.personas_por_localidad.domain.Direccion;
import ar.edu.utn.frba.dds.personas_por_localidad.domain.Localidad;
import ar.edu.utn.frba.dds.personas_por_localidad.domain.PersonaVulnerable;
import ar.edu.utn.frba.dds.personas_por_localidad.services.LocalidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

public class MapperPersonaVulnerable {


    public static PersonaVulnerable mapToPersonaVulnerable(PersonaVulnerableDTOIn personaVulnerableDTO) {
        Direccion direccion = new Direccion(
                personaVulnerableDTO.getDireccion().getLatitud()
                , personaVulnerableDTO.getDireccion().getLongitud()
        );

        return new PersonaVulnerable(
                personaVulnerableDTO.getNombre(),
                personaVulnerableDTO.getApellido(),
                direccion,
                personaVulnerableDTO.getTipoDocumento(),
                personaVulnerableDTO.getNumeroDeDocumento(),
                new ArrayList<>()
        );
    }
}

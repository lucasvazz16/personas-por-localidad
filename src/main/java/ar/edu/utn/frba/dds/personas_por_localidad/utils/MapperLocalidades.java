package ar.edu.utn.frba.dds.personas_por_localidad.utils;

import ar.edu.utn.frba.dds.personas_por_localidad.connectors.dtos.LocalidadDTOIn;
import ar.edu.utn.frba.dds.personas_por_localidad.connectors.dtos.UbicacionDTOIn;
import ar.edu.utn.frba.dds.personas_por_localidad.domain.Localidad;

public class MapperLocalidades {

    public static Localidad mapToLocalidad(UbicacionDTOIn ubicacionDTO) {
        LocalidadDTOIn localidadDTO = ubicacionDTO.getLocalidad();
        return new Localidad(
                ubicacionDTO.getNombre(),
                localidadDTO.getCiudad(),
                localidadDTO.getPartido(),
                localidadDTO.getProvincia(),
                localidadDTO.getPais()
        );
    }

}

package ar.edu.utn.frba.dds.personas_por_localidad.services;

import ar.edu.utn.frba.dds.personas_por_localidad.connectors.dtos.DireccionDTOIn;
import ar.edu.utn.frba.dds.personas_por_localidad.connectors.dtos.LocalidadDTOIn;
import ar.edu.utn.frba.dds.personas_por_localidad.connectors.dtos.PersonaVulnerableDTOIn;
import ar.edu.utn.frba.dds.personas_por_localidad.connectors.dtos.UbicacionDTOIn;
import ar.edu.utn.frba.dds.personas_por_localidad.connectors.localidades.LocalidadConnector;
import ar.edu.utn.frba.dds.personas_por_localidad.domain.Localidad;
import ar.edu.utn.frba.dds.personas_por_localidad.domain.PersonaVulnerable;
import ar.edu.utn.frba.dds.personas_por_localidad.repositorios.interfaces.ILocalidadesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import ar.edu.utn.frba.dds.personas_por_localidad.utils.MapperLocalidades;
import org.apache.catalina.mapper.Mapper;
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

    public List<Localidad> obtenerLocalidadesDondeObtuvoViandas(PersonaVulnerableDTOIn personaVulnerableDTO) {
        Set<DireccionDTOIn> direccionesDTO = personaVulnerableDTO.obtenerDireccionesDondeUsoTarjeta();
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
        if (localidadesRepository.existeLocalidad(localidad)) {
            localidadesRepository.modificar(localidad);
        } else {

            localidadesRepository.agregar(localidad);
        }
    }

    public List<Localidad> obtenerLocalidades() {
        return localidadesRepository.obtenerTodas();
    }

    public Optional<Localidad> obtenerLocalidadPorNombre(String nombre) {
        return localidadesRepository.obtenerPorNombre(nombre);
    }

    public Optional<Localidad> obtenerLocalidadPorId(Long id) {
        return localidadesRepository.obtenerPorId(id);
    }


}

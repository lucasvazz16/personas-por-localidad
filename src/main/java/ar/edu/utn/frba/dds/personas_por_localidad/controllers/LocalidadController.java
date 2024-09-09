package ar.edu.utn.frba.dds.personas_por_localidad.controllers;

import ar.edu.utn.frba.dds.personas_por_localidad.connectors.dtos.PersonaVulnerableDTOIn;
import ar.edu.utn.frba.dds.personas_por_localidad.controllers.dtos.LocalidadDTOOut;
import ar.edu.utn.frba.dds.personas_por_localidad.domain.Localidad;
import ar.edu.utn.frba.dds.personas_por_localidad.services.LocalidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/localidades")
public class LocalidadController {
    @Autowired
    private LocalidadService localidadService;

    @Autowired
    public LocalidadController(LocalidadService localidadService) {
        this.localidadService = localidadService;
    }


    @GetMapping("/obtenerLocalidades")
    public ResponseEntity<List<LocalidadDTOOut>> obtenerLocalidadesDondeObtuvoViandas() {
        List<LocalidadDTOOut> localidades = localidadService.obtenerLocalidadesConPersonas();
        return ResponseEntity.ok(localidades);
    }
}

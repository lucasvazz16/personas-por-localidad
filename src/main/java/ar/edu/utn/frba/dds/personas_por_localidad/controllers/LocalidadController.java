package ar.edu.utn.frba.dds.personas_por_localidad.controllers;

import ar.edu.utn.frba.dds.personas_por_localidad.connectors.dtos.PersonaVulnerableDTOIn;
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

    // Endpoint para obtener todas las localidades
    @GetMapping
    public ResponseEntity<List<Localidad>> obtenerTodasLasLocalidades() {
        List<Localidad> localidades = localidadService.obtenerLocalidades();
        return ResponseEntity.ok(localidades);
    }

    // Endpoint para obtener una localidad por su nombre
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Localidad> obtenerLocalidadPorNombre(@PathVariable String nombre) {
        Optional<Localidad> localidad = localidadService.obtenerLocalidadPorNombre(nombre);
        return localidad.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para obtener una localidad por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Localidad> obtenerLocalidadPorId(@PathVariable Long id) {
        Optional<Localidad> localidad = localidadService.obtenerLocalidadPorId(id);
        return localidad.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para obtener localidades basadas en un PersonaVulnerableDTOIn
    @GetMapping("/obtenerLocalidades")
    public ResponseEntity<List<Localidad>> obtenerLocalidadesDondeObtuvoViandas(@RequestBody PersonaVulnerableDTOIn personaVulnerableDTO) {
        List<Localidad> localidades = localidadService.obtenerLocalidadesDondeObtuvoViandas(personaVulnerableDTO);
        return ResponseEntity.ok(localidades);
    }
}

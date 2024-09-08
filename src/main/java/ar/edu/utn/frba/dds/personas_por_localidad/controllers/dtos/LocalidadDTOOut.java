package ar.edu.utn.frba.dds.personas_por_localidad.controllers.dtos;

import lombok.*;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class LocalidadDTOOut {

    private String nombre;
    private String ciudad;
    private String partido;
    private String provincia;
    private String pais;
    private Integer cantidadDePersonas;
    private List<String> nombresDePersonasVulnerables;


}

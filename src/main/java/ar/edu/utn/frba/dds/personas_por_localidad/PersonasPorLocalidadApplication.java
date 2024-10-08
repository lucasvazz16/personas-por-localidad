package ar.edu.utn.frba.dds.personas_por_localidad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PersonasPorLocalidadApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonasPorLocalidadApplication.class, args);
	}

}

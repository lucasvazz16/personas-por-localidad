package ar.edu.utn.frba.dds.personas_por_localidad.excepciones;

public class LocalidadNotFoundException extends RuntimeException {
    public LocalidadNotFoundException(String message) {
        super(message);
    }
}

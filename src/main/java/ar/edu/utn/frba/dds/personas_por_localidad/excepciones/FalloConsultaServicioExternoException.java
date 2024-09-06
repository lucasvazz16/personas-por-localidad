package ar.edu.utn.frba.dds.personas_por_localidad.excepciones;

public class FalloConsultaServicioExternoException extends RuntimeException {

  public FalloConsultaServicioExternoException(String message) {
    super(message);
  }

  public FalloConsultaServicioExternoException(String message, Throwable cause) {
    super(message, cause);
  }
}


# Personas por localidad

Servicio encargado de devolver una lista de localidades con la cantidad de personas que consumieron viandas en ellas, además de incluir los nombres de dichas personas.

## API Reference

#### Get all items

```http
  GET /api/localidades/obtenerLocalidades
```
#### Formato de devolución
```json
{
  "nombre": "string",
  "ciudad": "string",
  "partido": "string",
  "provincia": "string",
  "pais": "string",
  "cantidadDePersonas": 0,
  "nombresDePersonasVulnerables": [
    "string1",
    "string2"
  ]
}
```
#### Ejemplo de devolución
```json
{
  "nombre": "Lanús Oeste, Partido de Lanús, Buenos Aires, 1824, Argentina",
  "ciudad": "Lanús Oeste",
  "partido": "Partido de Lanús",
  "provincia": "Buenos Aires",
  "pais": "Argentina",
  "cantidadDePersonas": 2,
  "nombresDePersonasVulnerables": [
    "Juan Pérez",
    "María López"
  ]
}
```

## Importante

Este proyecto utiliza una base de datos MySQL, por lo que es necesario tener instalado el servidor de MySQL y crear una base de datos llamada `tp_servicio_externo`. Además, es necesario tener un usuario con permisos para acceder a la base de datos (Recordar cambiar las credenciales de acceso en `src/main/resources/application.properties`). 




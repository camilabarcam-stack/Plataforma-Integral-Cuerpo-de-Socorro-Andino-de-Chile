Sistema Socorro Andino - Backend Microservices

Este repositorio contiene el ecosistema de microservicios distribuidos para la gestión de emergencias, asignación de rescatistas en terreno y recepción de donaciones para la fundación Socorro Andino.

Arquitectura del Proyecto
El sistema está construido bajo un enfoque de arquitectura Limpia y Distribuida utilizando Java, Spring Boot, Spring Data JPA y comunicación síncrona inter-servicio mediante WebClient (Spring WebFlux).

Cada microservicio sigue estrictamente el patrón de diseño por capas:
Model: Mapeo relacional directo a la base de datos con Hibernate/JPA.
Repository: Capa de persistencia que hereda de JpaRepository.
Service: Lógica de negocio encapsulada y orquestación de llamadas externas (WebClient).
Controller: Endpoints REST expuestos para el consumo del cliente.

Mapeo de Puertos y Servicios
service-usuarios (Puerto 8081): Gestión de datos maestros de personal, rescatistas globales y aportantes.
service-rescates (Puerto 8082): Control de asignaciones locales y reportes de misiones en terreno.
service-donaciones (Puerto 8083): Módulo financiero para transacciones y emisión automática de comprobantes.
service-emergencias (Puerto 8084): Registro central de incidentes y coordenadas geográficas.


Preparación de las Bases de Datos (Pruebas)

Guía de Ejecución de QA (Flujo Postman)
Para probar la integración síncrona del sistema sin obtener errores de entidad no encontrada (Internal Server Error 500), se debe respetar estrictamente la siguiente secuencia de peticiones en Postman:

A. Crear el Usuario Base
Endpoint: POST http://localhost:8081/api/v1/usuarios


JSON
{
  "nombre": "Juan",
  "apellido": "Pérez",
  "correo": "juan.perez@email.com",
  "telefono": "+56912345678",
  "rol": "REPORTANTE"
}
Nota: Genera de forma automática el idUsuario: 1.

B. Crear el Rescatista Base
Endpoint: POST http://localhost:8082/api/v1/rescatistas


JSON
{
  "nombre": "Carlos Silva",
  "especialidad": "Alta Montaña",
  "estadoAvailability": "DISPONIBLE"
}
Nota: Genera el idRescatista 1

Casos de Uso
CU 01: Reportar Emergencia
Endpoint: POST http://localhost:8084/api/v1/emergencias


JSON
{
  "idUsuario": 1,
  "tipo": "Extravío",
  "descripcion": "Montañista perdido cerca del refugio Plantat, herido en un tobillo.",
  "ubicacion": "-33.7845, -70.1234"
}

CU 02: Asignar Misión de Rescate
Endpoint: POST http://localhost:8082/api/v1/asignaciones


JSON
{
  "idEmergencia": 1,
  "idRescatista": 1
}

CU 03: Registrar Donación
Endpoint: POST http://localhost:8083/api/v1/donaciones


JSON
{
  "idUsuario": 1,
  "monto": 25000.00,
  "metodoPago": "CREDITO",
  "estado": "COMPLETADA"
}

Integraciones Externas
Google Maps API: El atributo ubicacion (Latitud/Longitud) del servicio de emergencias está estructurado para ser consumido nativamente en el front-end por los marcadores del componente de Google Maps.

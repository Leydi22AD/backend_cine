# language: es
Característica: Certificación de Calidad y Cobertura del Microservicio de Asientos

  Antecedentes:
    Dado que la base de datos de pruebas está activa y limpia

  @FlujoFeliz
  Escenario: Registrar y recuperar un asiento correctamente mediante el API
    Cuando se envía una solicitud POST para crear un asiento en la fila 5 columna 12 con estado "LIBRE" y sala ID 1
    Entonces el sistema debe responder con un código de estado de éxito 201
    Y el JSON de respuesta debe contener un ID de asiento autogenerado
    Cuando se solicita el asiento creado mediante una petición GET
    Entonces el sistema debe retornar los datos de la fila 5 y columna 12 correctamente

  @FlujoValidacion
  Escenario: Intentar registrar un asiento con coordenadas inválidas
    Cuando se envía una solicitud POST para crear un asiento con la fila -1 y columna 999
    Entonces el sistema debe denegar el registro respondiendo con un código de error 400
    Y el mensaje de error debe indicar "Parámetros de asiento inválidos"

  @FlujoDuplicado
  Escenario: Impedir el registro de un asiento duplicado en la misma sala
    Cuando se envía una solicitud POST para crear un asiento en la fila 99 columna 99 con estado "LIBRE" y sala ID 1
    Y se intenta enviar otra solicitud POST exactamente con la misma fila 99 y columna 99 en la sala ID 1
    Entonces el sistema debe lanzar una excepción AsientoAlreadyExistsException
    Y responder con un código de estado de conflicto 409
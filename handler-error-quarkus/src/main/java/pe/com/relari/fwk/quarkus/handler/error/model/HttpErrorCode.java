package pe.com.relari.fwk.quarkus.handler.error.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * <b>Enum:</b> HttpErrorCode.<br>
 * Define los códigos HTTP estándar con sus descripciones genéricas.
 * Estos códigos son reutilizables en toda la arquitectura de microservicios.
 *
 * @author Relari
 */

@Getter
@RequiredArgsConstructor
public enum HttpErrorCode {

  // 4xx - Client Errors
  BAD_REQUEST(400, "Solicitud inválida o incorrecta."),
  UNAUTHORIZED(401, "Autenticación requerida."),
  FORBIDDEN(403, "Acceso denegado."),
  NOT_FOUND(404, "Recurso no encontrado."),
  METHOD_NOT_ALLOWED(405, "Método HTTP no permitido."),
  CONFLICT(409, "Conflicto con el estado actual del recurso."),
  UNPROCESSABLE_ENTITY(422, "Entidad no procesable."),
  TOO_MANY_REQUESTS(429, "Demasiadas solicitudes."),

  // 5xx - Server Errors
  INTERNAL_SERVER_ERROR(500, "Error interno del servidor."),
  NOT_IMPLEMENTED(501, "Funcionalidad no implementada."),
  BAD_GATEWAY(502, "Puerta de enlace incorrecta."),
  SERVICE_UNAVAILABLE(503, "Servicio no disponible."),
  GATEWAY_TIMEOUT(504, "Tiempo de espera agotado.");

  private final int code;
  private final String description;

  /**
   * Busca la constante HttpErrorCode por su código numérico.
   * Si no existe, retorna INTERNAL_SERVER_ERROR por defecto.
   *
   * @param status código HTTP
   * @return HttpErrorCode correspondiente
   */
  public static HttpErrorCode fromStatus(int status) {
    for (HttpErrorCode hec : HttpErrorCode.values()) {
      if (hec.code == status) {
        return hec;
      }
    }
    return INTERNAL_SERVER_ERROR;
  }

}


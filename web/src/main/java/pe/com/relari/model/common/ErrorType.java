package pe.com.relari.model.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
/**
 * <b>Class:</b> ErrorType.<br/>
 * <b>Description:</b> Enumeration of application error types with their corresponding HTTP
 * status codes. Used to construct standardized error responses.
 *
 * @author Relari
 */
public enum ErrorType {
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    NOT_FOUND(404),
    RESOURCE_NOT_FOUND(404),
    METHOD_NOT_ALLOWED(405),
    CONFLICT(409),
    INTERNAL_SERVER_ERROR(500),
    BAD_GATEWAY(502),
    SERVICE_UNAVAILABLE(503);

  private final int status;

}

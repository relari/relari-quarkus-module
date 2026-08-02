package pe.com.relari.model.common;

import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.com.relari.error.model.ErrorCategory;
import pe.com.relari.error.model.ErrorResponse;

/**
 * <b>Class:</b> ApiResponse.<br/>
 * <b>Description:</b> Generic wrapper used for all API responses. Encapsulates a high-level
 * status, HTTP status code and either data or an error payload. Provides helper methods to
 * build standardized success and error JAX-RS {@code Response} instances.
 *
 * @param <T> the type of the response data payload
 *
 * @author Relari
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

  private StatusType status;
  private int statusCode;
  private T data;

  /**
   * Build a successful JAX-RS response with the provided data wrapped in an {@code ApiResponse}.
   *
   * @param data payload to return
   * @return JAX-RS {@link Response} with status 200
   */
  public static Response okResponse(Object data) {
    return Response.ok(new ApiResponse<>(StatusType.OK, 200, data)).build();
  }

  /**
   * Build an error JAX-RS response based on the application's {@link ErrorType} and message.
   *
   * @param statusCode error type enumeration containing the HTTP status
   * @param data human-readable error message
   * @return JAX-RS {@link Response} with the corresponding error status and payload
   */
  public static Response errorResponse(ErrorCategory errorCategory) {
    return Response.status(errorCategory.status())
            .entity(new ApiResponse<>(
                    StatusType.ERROR, errorCategory.status(), new ErrorResponse(errorCategory)
            ))
            .build();
  }

}

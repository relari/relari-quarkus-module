package pe.com.relari.fwk.quarkus.support.util;

import jakarta.ws.rs.core.Response;
import pe.com.relari.commons.model.api.ApiResponse;
import pe.com.relari.commons.model.error.ErrorResponse;

/**
 * <b>Class:</b> ResponseUtils.<br>
 *
 * @author Relari.
 */

public class ResponseUtils extends ApiResponse<Object> {

  private ResponseUtils() {}

  public static Response toOkResponse(Object data) {
    if (data == null) {
      return Response.noContent().build();
    }
    var apiResponse = success(data);
    return Response.status(apiResponse.getStatus())
        .entity(apiResponse)
        .build();
  }

  public static Response toErrorResponse(ErrorResponse errorResponse) {
    return Response.status(errorResponse.getStatus())
        .entity(errorResponse)
        .build();
  }
}
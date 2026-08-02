package pe.com.relari.error.util;

import jakarta.ws.rs.core.Response;
import pe.com.relari.error.model.ErrorResponse;

public class ErrorUtility {

    private ErrorUtility() {}

    public static Response getErrorResponseEntity(ErrorResponse errorResponse) {
        return Response.status(errorResponse.getStatus()).entity(errorResponse).build();
    }

}
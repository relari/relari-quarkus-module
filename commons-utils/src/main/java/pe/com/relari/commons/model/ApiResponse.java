package pe.com.relari.commons.model;

import static pe.com.relari.commons.constant.Constants.SUCCESS_CODE;
import static pe.com.relari.commons.constant.Constants.SUCCESS_STATUS;

import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * Class: EmployeeDetailResponse.
 *
 * @author Relari
 */

public record ApiResponse<T> (
		@Schema(
				description = "Codigo HTTP.",
				name = "code",
				implementation = String.class,
				example = "OK")
		String code,
		@Schema(
				description = "Estatus HTTP.",
				name = "status",
				implementation = Integer.class,
				example = "200")
		Integer status,
		@Schema(
				description = "Data de respuesta.",
				name = "data")
		T data
) {
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(
                SUCCESS_CODE, SUCCESS_STATUS, data
        );
    }

	public Response toResponse() {
		var entity = success(this.data());
		return Response.status(entity.status()).entity(entity).build();
	}
}

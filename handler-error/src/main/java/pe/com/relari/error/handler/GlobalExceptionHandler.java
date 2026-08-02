package pe.com.relari.error.handler;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;
import pe.com.relari.error.config.ErrorConfig;
import pe.com.relari.error.exception.ApiException;
import pe.com.relari.error.model.ValidationError;

/**
 * <b>Class:</b> GlobalExceptionHandler.<br/>
 *
 * @author Relari
 */
@Provider
@RequiredArgsConstructor
public class GlobalExceptionHandler implements ExceptionMapper<Exception> {

    private static final Logger log = Logger.getLogger(GlobalExceptionHandler.class);

    @ConfigProperty(name = "quarkus.application.name")
    private String applicationName;
    private final ErrorConfig errorConfig;

    @Override
    public Response toResponse(Exception exception) {

        if (exception instanceof ApiException apiException) {
            return apiException(apiException);
        }

        if (exception instanceof ConstraintViolationException constraintViolationException) {
            return constraintViolationException(constraintViolationException);
        }

        if (exception instanceof BadRequestException badRequestException) {
            return badRequestException(badRequestException);
        }

        if (exception instanceof MismatchedInputException mismatchedInputException) {
            return mismatchedInputException(mismatchedInputException);
        }

        if (exception instanceof JsonParseException jsonParseException) {
            return jsonParseException(jsonParseException);
        }

        return exception(exception);
    }

    public Response apiException(ApiException apiException) {
        return errorConfig.getErrorByCategoryCode(apiException.getMessage(), apiException.getCause());
    }

    /**
     * Maneja cualquier excepción no controlada (Exception).
     * Se lanza ante errores inesperados del sistema (NullPointerException, fallos
     * de BD no controlados, etc.).
     * Actúa como un "catch-all" para evitar que el cliente reciba un stacktrace completo.
     * En desarrollo, incluye el stack trace para debugging.
     *
     * @param exception La excepción inesperada
     * @return Response con error 500 Internal Server Error
     */
    public Response exception(Exception exception) {
        log.error("Unexpected error occurred", exception);
        return errorConfig.getErrorByStatusCode("INTERNAL_SERVER_ERROR", exception.getCause());
    }

    /**
     * Maneja excepciones de validación de argumentos (@Valid).
     * Se lanza cuando el cuerpo de la solicitud (Body) falla las validaciones de
     * las anotaciones
     * (ej: @NotNull, @Email, @Size) en el DTO de entrada.
     *
     * @param exception La excepción con los resultados de la validación
     * @return ResponseEntity con error 400 y la lista detallada de campos inválidos
     */
    public Response constraintViolationException(
            ConstraintViolationException exception) {
        log.error("[ConstraintViolationException] occurred", exception);
        List<ValidationError> errorDetails = exception.getConstraintViolations().stream()
                .map(violation -> {
                    String field = violation.getPropertyPath().toString();
                    return new ValidationError(
                            field.substring(field.lastIndexOf('.') + 1),
                            violation.getMessage()
                    );
                })
                .toList();

        return errorConfig.getErrorByStatusCode("BAD_REQUEST", errorDetails, exception.getCause());
    }

    /**
     * Maneja errores de tipo de dato en argumentos (Type Mismatch).
     * Se lanza cuando se intenta convertir un valor de la URL (Path o Query
     * Variable)
     * a un tipo de Java incompatible (ej: enviar texto "abc" donde se espera un
     * Integer).
     *
     * @param exception La excepción de desajuste de tipos
     * @return ResponseEntity con error 400 indicando qué parámetro tiene el tipo
     * incorrecto
     */
    public Response badRequestException(BadRequestException exception) {
        log.error("[BadRequestException] occurred", exception);
        return errorConfig.getErrorByStatusCode("BAD_REQUEST", exception.getCause());
    }

    /**
     * Maneja errores de lectura del cuerpo de la solicitud (JSON mal formado).
     * Se lanza cuando Jackson no puede parsear el JSON de entrada (sintaxis
     * inválida,
     * comas faltantes, tipos de datos incompatibles en el JSON, etc.).
     *
     * @param exception La excepción de mensaje no legible
     * @return ResponseEntity con error 400 indicando JSON mal formado
     */
    public Response mismatchedInputException(MismatchedInputException exception) {
        log.error("[MismatchedInputException] occurred", exception);
        return errorConfig.getErrorByStatusCode("BAD_REQUEST", exception.getCause());
    }

    /**
     * Maneja errores de lectura del cuerpo de la solicitud (JSON mal formado).
     * Se lanza cuando Jackson no puede parsear el JSON de entrada (sintaxis
     * inválida,
     * comas faltantes, tipos de datos incompatibles en el JSON, etc.).
     *
     * @param exception La excepción de mensaje no legible
     * @return ResponseEntity con error 400 indicando JSON mal formado
     */
    public Response jsonParseException(JsonParseException exception) {
        log.error("[JsonParseException] occurred", exception);
        return errorConfig.getErrorByStatusCode("BAD_REQUEST", exception.getCause());
    }
}

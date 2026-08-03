package pe.com.relari.error.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import pe.com.relari.error.model.ErrorResponse;
import pe.com.relari.error.util.ErrorUtility;

import java.time.Clock;
import java.time.LocalDateTime;

@Getter
@Setter
@ApplicationScoped
@RequiredArgsConstructor
public class ErrorConfig {

    @ConfigProperty(name = "quarkus.application.name")
    private String applicationName;

    @ConfigProperty(name = "application.env")
    private String activeProfile;

    private final ErrorProperties errorProperties;

    /**
     * Verifica si está en ambiente de desarrollo para incluir stack traces.
     *
     * @return true si debe incluir stack trace (dev, test), false si es producción
     */
    private boolean shouldIncludeStackTrace() {
        return activeProfile.contains("dev") || activeProfile.contains("test") || activeProfile.contains("local");
    }

    public Response getErrorByCategoryCode(String categoryCode, Throwable throwable) {
        ErrorResponse errorResponse = errorProperties.getErrorByCategoryCode(categoryCode);
        errorResponse.setTimestamp(LocalDateTime.now(Clock.systemDefaultZone()).toString());
        if (shouldIncludeStackTrace()) {
            errorResponse.setThrowable(throwable);
        }
        return ErrorUtility.getErrorResponseEntity(errorResponse);
    }

    public Response getErrorByStatusCode(String statusCode, Throwable throwable) {
        ErrorResponse errorResponse = errorProperties.getErrorByStatusCode(statusCode);
        errorResponse.setTimestamp(LocalDateTime.now(Clock.systemDefaultZone()).toString());
        if (shouldIncludeStackTrace()) {
            errorResponse.setThrowable(throwable);
        }
        return ErrorUtility.getErrorResponseEntity(errorResponse);
    }

    public Response getErrorByStatusCode(String statusCode, Object details, Throwable throwable) {
        ErrorResponse errorResponse = errorProperties.getErrorByStatusCode(statusCode);
        errorResponse.setTimestamp(LocalDateTime.now(Clock.systemDefaultZone()).toString());
        if (shouldIncludeStackTrace()) {
            errorResponse.setThrowable(throwable);
        }
        if (details != null) {
            errorResponse.setDetails(details);
        }
        return ErrorUtility.getErrorResponseEntity(errorResponse);
    }

}

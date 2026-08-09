package pe.com.relari.fwk.quarkus.handler.error.config;

import io.smallrye.config.ConfigMapping;
import jakarta.validation.constraints.NotNull;
import pe.com.relari.commons.model.error.ErrorCategory;
import pe.com.relari.commons.model.error.ErrorResponse;
import pe.com.relari.commons.model.error.ErrorStatus;

import java.util.Map;

import static pe.com.relari.commons.constant.Constants.EMPTY;

@ConfigMapping(prefix = "application.errors")
public interface ErrorProperties {

    String code();
    String defaultCode();
    @NotNull(message = "Status cannot be null")
    Map<String, ErrorStatus> status();
    @NotNull(message = "Categories cannot be null")
    Map<String, ErrorCategory> categories();

    default ErrorCategory getErrorCategory(String categoryCode) {
        return this.categories().get(categoryCode);
    }

    default ErrorStatus getErrorStatus(String statusCode) {
        return this.status().get(statusCode);
    }

    default ErrorResponse getErrorByCategoryCode(String categoryCode) {
        ErrorCategory category = getErrorCategory(categoryCode);
        ErrorStatus errorStatus = getErrorStatus(category.getStatusCode());
        return ErrorResponse.builder()
                .code(category.getCode())
                .status(errorStatus.getStatus())
                .description(getDefaultErrorDescription(
                        errorStatus.getDescription(), category.getDescription()
                ))
                .build();
    }

    default ErrorResponse getErrorByStatusCode(String statusCode) {
        ErrorStatus errorStatus = getErrorStatus(statusCode);
        return ErrorResponse.builder()
                .code(String.format("%s%s", code(), errorStatus.getStatus()))
                .status(errorStatus.getStatus())
                .description(getDefaultErrorDescription(
                        errorStatus.getDescription(), EMPTY
                ))
                .build();
    }

    default String getDefaultErrorDescription(String description, String customDescription) {
        return (customDescription != null && !customDescription.isEmpty()) ? customDescription : description;
    }
}

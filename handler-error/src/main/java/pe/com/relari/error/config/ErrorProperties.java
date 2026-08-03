package pe.com.relari.error.config;

import io.smallrye.config.ConfigMapping;
import jakarta.validation.constraints.NotNull;
import pe.com.relari.error.model.ErrorCategory;
import pe.com.relari.error.model.ErrorResponse;
import pe.com.relari.error.model.ErrorStatus;

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

    default ErrorResponse getErrorByCategoryCode(String categoryCode) {
        ErrorCategory category = this.categories().get(categoryCode);
        ErrorStatus errorStatus = this.status().get(category.statusCode());
        return ErrorResponse.builder()
                .code(category.code())
                .status(errorStatus.status())
                .description(getDefaultErrorDescription(
                        errorStatus.description(), category.description()
                ))
                .build();
    }

    default ErrorResponse getErrorByStatusCode(String statusCode) {
        ErrorStatus errorStatus = this.status().get(statusCode);
        return ErrorResponse.builder()
                .code(String.format("%s%s", code(), errorStatus.status()))
                .status(errorStatus.status())
                .description(getDefaultErrorDescription(
                        errorStatus.description(), EMPTY
                ))
                .build();
    }

    default String getDefaultErrorDescription(String description, String customDescription) {
        return (customDescription != null && !customDescription.isEmpty()) ? customDescription : description;
    }
}

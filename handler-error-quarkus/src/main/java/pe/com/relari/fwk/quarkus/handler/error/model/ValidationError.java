package pe.com.relari.fwk.quarkus.handler.error.model;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * <b>Record:</b> ValidationErrorDetail.<br>
 *
 * @param field Nombre del campo validado
 * @param message Mensaje de validación
 */

public record ValidationError(

    @Schema(
        description = "Nombre del campo validado",
        name = "field",
        implementation = String.class,
        example = "email")
    String field,

    @Schema(
        description = "Mensaje de error de validación",
        name = "message",
        implementation = String.class,
        example = "El email debe ser válido")
    String message

) {}


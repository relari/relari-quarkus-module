package pe.com.relari.error.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * <b>Class:</b> ErrorResponse.<br>
 *
 * @author Relari
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

  @Schema(
          description = "Representa el codigo del error.",
          name = "code",
          implementation = String.class,
          example = "API-400")
  private String code;

  @Schema(
          description = "Estatus HTTP.",
          name = "status",
          implementation = Integer.class,
          example = "400")
  private Integer status;

  @Schema(
          description = "Representa la descripcion del error.",
          name = "description",
          implementation = String.class,
          example = "Solicitud incorrecta o inválida.")
  private String description;

  @Schema(
          description = "Marca temporal del error.",
          name = "timestamp",
          implementation = String.class,
          example = "2024-01-01T12:00:00Z")
  private String timestamp;

  @Schema(
          description = "Lista de detalles del error.",
          name = "metadata",
          implementation = Map.class)
  private Map<String, String> metadata;

  @Schema(
          description = "Stack trace de la excepción (solo en desarrollo).",
          name = "throwable",
          implementation = String.class)
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private Throwable throwable;

  private Object details;

}

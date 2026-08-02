package pe.com.relari.model.api;

import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.HeaderParam;
import lombok.Getter;
import lombok.Setter;

/**
 * <b>Class:</b> ApiHeaders.<br/>
 * <b>Description:</b> Bean that represents HTTP headers expected by API endpoints. Used as
 * a {@code @BeanParam} to extract headers like the request id from incoming requests.
 *
 * @author Relari
 */
@Getter
@Setter
public class ApiHeaders {

  @NotNull
  @HeaderParam("X-Request-ID")
  private String requestId;

}

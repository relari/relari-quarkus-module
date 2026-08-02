package pe.com.relari.config;

import io.smallrye.config.ConfigMapping;
import pe.com.relari.error.model.ErrorCategory;

import java.util.Map;

/**
 * <b>Class:</b> ErrorProperties.<br/>
 * <b>Description:</b> Configuration properties for error handling in the application.
 *
 * @author Relari
 */

@ConfigMapping(prefix = "application.errors")
public interface ErrorProperties {

  String defaultMessage();
  Integer status();
  String code();
  Map<String, ErrorCategory> categories();

}

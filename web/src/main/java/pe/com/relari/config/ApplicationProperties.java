package pe.com.relari.config;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.Data;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 * <b>Class:</b> ApplicationProperties.<br/>
 * <b>Description:</b> Holds application-level configuration properties injected via
 * MicroProfile Config (e.g. greeting message).
 *
 * @author Relari
 */
@Data
@ApplicationScoped
public class ApplicationProperties {

  @ConfigProperty(name = "greeting.message")
  private String message;

}

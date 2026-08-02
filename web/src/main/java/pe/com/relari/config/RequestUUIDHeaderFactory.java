package pe.com.relari.config;

import io.vertx.core.http.HttpServerRequest;
import org.eclipse.microprofile.rest.client.ext.ClientHeadersFactory;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MultivaluedHashMap;
import jakarta.ws.rs.core.MultivaluedMap;
import java.util.UUID;

/**
 * <b>Class:</b> RequestUUIDHeaderFactory.<br/>
 * <b>Description:</b> REST client headers factory that injects a request UUID and propagates
 * the incoming Authorization header to outbound REST client calls. Used by MicroProfile
 * Rest Client via {@code @RegisterClientHeaders}.
 *
 * @author Relari
 */
@ApplicationScoped
public class RequestUUIDHeaderFactory implements ClientHeadersFactory {

    @Context
    HttpServerRequest request;

    @Override
    public MultivaluedMap<String, String> update(
            MultivaluedMap<String, String> incomingHeaders,
            MultivaluedMap<String, String> clientOutgoingHeaders) {
        MultivaluedMap<String, String> result = new MultivaluedHashMap<>();
        result.add("X-request-uuid", UUID.randomUUID().toString());
        result.add("Authorization", request.getHeader("Authorization"));
        return result;
    }
}
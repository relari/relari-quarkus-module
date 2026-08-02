package pe.com.relari.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import pe.com.relari.dao.ws.JsonPlaceHolderApi;
import pe.com.relari.dao.ws.model.UserResponse;

import java.util.List;

/**
 * <b>Class:</b> UserResource.<br/>
 * <b>Description:</b> REST resource that exposes user-related endpoints and delegates calls to a
 * remote JSON placeholder API client {@code JsonPlaceHolderApi}.
 *
 * Provides methods to fetch a single user and a list of users.
 *
 * @author Relari
 */
@Path("/users")
@RequiredArgsConstructor
public class UserResource {

  @RestClient
  private final JsonPlaceHolderApi jsonPlaceHolderApi;

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public UserResponse getUser(@PathParam("id") Integer id) {
    return jsonPlaceHolderApi.getUser(id);
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<UserResponse> users() {
    return jsonPlaceHolderApi.users();
  }
}

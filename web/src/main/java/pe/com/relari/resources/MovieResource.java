package pe.com.relari.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pe.com.relari.config.ApplicationProperties;
import pe.com.relari.model.api.ApiHeaders;
import pe.com.relari.model.api.Movie;
import pe.com.relari.model.common.ApiResponse;
import pe.com.relari.service.MovieService;

/**
 * <b>Class:</b> MovieResource.<br/>
 * <b>Description:</b> REST resource that exposes movie-related endpoints (GET list, GET by id, POST to add).
 * Delegates business logic to the {@code MovieService} and maps requests/responses to API models.
 *
 * @author Relari
 */
@Slf4j
@Path("/movies")
@RequiredArgsConstructor
public class MovieResource {

  private final MovieService service;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getMovies(
          @Context HttpHeaders headers) {
    log.info(headers.getHeaderString("Authorization"));
    return ApiResponse.okResponse(service.getMovies());
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getMovie(
      @BeanParam ApiHeaders headers,
      @PathParam("id") Integer id) {
    return ApiResponse.okResponse(service.getMovie(id));
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response addMovie(Movie movie) {
    service.saveMovie(movie);
    return Response.status(Response.Status.CREATED).build();
  }

}
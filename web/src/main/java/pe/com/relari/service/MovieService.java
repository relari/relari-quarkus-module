package pe.com.relari.service;

import java.util.List;
import pe.com.relari.model.api.Movie;

/**
 * <b>Class:</b> MovieService.<br/>
 * <b>Description:</b> Service interface that defines business operations related to movies.
 * Implementations are responsible for coordinating DAO calls and mapping entities to API
 * models.
 *
 * @author Relari
 */
public interface MovieService {

  List<Movie> getMovies();

  Movie getMovie(Integer id);

  void saveMovie(Movie movie);

}

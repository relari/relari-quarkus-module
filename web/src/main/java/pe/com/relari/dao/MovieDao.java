package pe.com.relari.dao;

import pe.com.relari.dao.repository.MovieEntity;

import java.util.List;
import java.util.Optional;

/**
 * <b>Class:</b> MovieDao.<br/>
 * <b>Description:</b> Data access interface that declares persistence operations for
 * {@code MovieEntity}. Implementations handle storage and retrieval from the database.
 *
 * @author Relari
 */
public interface MovieDao {

  List<MovieEntity> getMovies();

  Optional<MovieEntity> getMovie(Integer id);

  void saveMovie(MovieEntity movie);

}

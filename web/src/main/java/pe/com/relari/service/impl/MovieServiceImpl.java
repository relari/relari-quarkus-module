package pe.com.relari.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.jboss.logging.Logger;
import pe.com.relari.dao.MovieDao;
import pe.com.relari.dao.repository.MovieEntity;
import pe.com.relari.error.exception.ApiException;
import pe.com.relari.model.api.Movie;
import pe.com.relari.service.MovieService;

/**
 * <b>Class:</b> MovieServiceImpl.<br/>
 * <b>Description:</b> Default implementation of {@code MovieService}. It retrieves movie
 * entities through {@code MovieDao}, maps them to API DTOs and enforces business rules
 * such as throwing {@link pe.com.relari.error.exception.ApiException} when resources are not found.
 *
 * @author Relari
 */
@ApplicationScoped
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

  private static final Logger log = Logger.getLogger(MovieServiceImpl.class);

  private final MovieDao movieDao;

  @Override
  public List<Movie> getMovies() {
    log.info("Get movies");
    return movieDao.getMovies()
        .stream()
        .map(movieEntity -> {
          Movie movie = new Movie();
          movie.setId(movieEntity.id.intValue());
          movie.setTitle(movieEntity.getTitle());
          movie.setYear(movieEntity.getReleaseYear());
          movie.setDirector(movieEntity.getDirector());
          return movie;
        })
        .toList();
  }

  @Override
  public Movie getMovie(Integer id) {
    log.infof("Find movie with id = %s", id);
    return movieDao.getMovie(id)
            .filter(Objects::nonNull)
        .map(movieEntity -> {
          Movie movie = new Movie();
          movie.setId(movieEntity.id.intValue());
          movie.setTitle(movieEntity.getTitle());
          movie.setYear(movieEntity.getReleaseYear());
          movie.setDirector(movieEntity.getDirector());
          return movie;
        })
        .orElseThrow(() -> new ApiException("MOVIE_NOT_FOUND"));
  }

  @Override
  public void saveMovie(Movie movie) {
    log.infof("Save movie with title = %s", movie.getTitle());
    MovieEntity movieEntity = new MovieEntity();
    movieEntity.setTitle(movie.getTitle());
    movieEntity.setReleaseYear(movie.getYear());
    movieEntity.setDirector(movie.getDirector());
    movieDao.saveMovie(movieEntity);
  }

}

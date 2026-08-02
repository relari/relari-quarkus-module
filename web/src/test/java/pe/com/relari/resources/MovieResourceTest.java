//package pe.com.relari.resources;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ValueSource;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import pe.com.relari.error.ApiException;
//import pe.com.relari.model.api.ApiHeaders;
//import pe.com.relari.model.api.Movie;
//import pe.com.relari.model.common.ApiResponse;
//import pe.com.relari.model.common.ErrorType;
//import pe.com.relari.model.common.StatusType;
//import pe.com.relari.service.MovieService;
//
//import jakarta.ws.rs.core.Response;
//import java.util.Collections;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//@DisplayName("MovieResource Tests")
//class MovieResourceTest {
//
//    @Mock
//    private MovieService service;
//
//    @InjectMocks
//    private MovieResource resource;
//
//    @BeforeEach
//    void setUp() {
//        // MockitoExtension handles initialization automatically
//    }
//
//    @Test
//    @DisplayName("Should return all movies when service returns movie list")
//    void shouldReturnAllMoviesWhenServiceReturnsMovieList() {
//        // Arrange
//        Movie movie = createTestMovie(1, "Inception", "Christopher Nolan", 2010);
//        List<Movie> movies = Collections.singletonList(movie);
//        when(service.getMovies()).thenReturn(movies);
//
//        // Act
//        Response response = resource.getMovies(new ApiHeaders());
//
//        // Assert
//        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
//        assertNotNull(response.getEntity());
//
//        ApiResponse<?> apiResponse = (ApiResponse<?>) response.getEntity();
//        assertEquals(StatusType.OK, apiResponse.getStatus());
//        assertEquals(movies, apiResponse.getData());
//
//        verify(service, times(1)).getMovies();
//    }
//
//    @Test
//    @DisplayName("Should return empty list when service returns empty list")
//    void shouldReturnEmptyListWhenServiceReturnsEmptyList() {
//        // Arrange
//        when(service.getMovies()).thenReturn(Collections.emptyList());
//
//        // Act
//        Response response = resource.getMovies(new ApiHeaders());
//
//        // Assert
//        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
//
//        ApiResponse<?> apiResponse = (ApiResponse<?>) response.getEntity();
//        assertEquals(StatusType.OK, apiResponse.getStatus());
//        assertEquals(Collections.emptyList(), apiResponse.getData());
//
//        verify(service, times(1)).getMovies();
//    }
//
//    @Test
//    @DisplayName("Should return specific movie when found by ID")
//    void shouldReturnSpecificMovieWhenFoundById() {
//        // Arrange
//        Movie expectedMovie = createTestMovie(1, "The Matrix", "Lana Wachowski", 1999);
//        when(service.getMovie(1)).thenReturn(expectedMovie);
//
//        // Act
//        Response response = resource.getMovie(new ApiHeaders(), 1);
//
//        // Assert
//        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
//
//        ApiResponse<?> apiResponse = (ApiResponse<?>) response.getEntity();
//        assertEquals(StatusType.OK, apiResponse.getStatus());
//        assertEquals(expectedMovie, apiResponse.getData());
//
//        verify(service, times(1)).getMovie(1);
//    }
//
//    @ParameterizedTest
//    @ValueSource(ints = {1, 2, 3, 100, 999})
//    @DisplayName("Should return movie for valid IDs")
//    void shouldReturnMovieForValidIds(int movieId) {
//        // Arrange
//        Movie movie = createTestMovie(movieId, "Test Movie " + movieId, "Test Director", 2024);
//        when(service.getMovie(movieId)).thenReturn(movie);
//
//        // Act
//        Response response = resource.getMovie(new ApiHeaders(), movieId);
//
//        // Assert
//        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
//
//        ApiResponse<?> apiResponse = (ApiResponse<?>) response.getEntity();
//        assertEquals(movie, apiResponse.getData());
//
//        verify(service, times(1)).getMovie(movieId);
//    }
//
//    @Test
//    @DisplayName("Should throw exception when movie not found")
//    void shouldThrowExceptionWhenMovieNotFound() {
//        // Arrange
//        when(service.getMovie(anyInt()))
//                .thenThrow(new ApiException(ErrorType.NOT_FOUND, "Movie not found with ID: 999"));
//
//        // Act & Assert
//        ApiException exception = assertThrows(ApiException.class, () -> resource.getMovie(new ApiHeaders(), 999));
//
//        assertEquals(ErrorType.NOT_FOUND, exception.getErrorType());
//        assertEquals("Movie not found with ID: 999", exception.getMessage());
//
//        verify(service, times(1)).getMovie(999);
//    }
//
//    @Test
//    @DisplayName("Should create movie successfully when valid movie is provided")
//    void shouldCreateMovieSuccessfullyWhenValidMovieIsProvided() {
//        // Arrange
//        Movie newMovie = createTestMovie(null, "New Movie", "New Director", 2024);
//        doNothing().when(service).saveMovie(any(Movie.class));
//
//        // Act
//        Response response = resource.addMovie(newMovie);
//
//        // Assert
//        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
//
//        verify(service, times(1)).saveMovie(newMovie);
//    }
//
//    @Test
//    @DisplayName("Should handle service exception during movie creation")
//    void shouldHandleServiceExceptionDuringMovieCreation() {
//        // Arrange
//        Movie movie = createTestMovie(null, "Invalid Movie", "", 2024);
//        doThrow(new ApiException(ErrorType.BAD_REQUEST, "Invalid movie data"))
//                .when(service).saveMovie(any(Movie.class));
//
//        // Act & Assert
//        ApiException exception = assertThrows(ApiException.class, () -> resource.addMovie(movie));
//
//        assertEquals(ErrorType.BAD_REQUEST, exception.getErrorType());
//        assertEquals("Invalid movie data", exception.getMessage());
//
//        verify(service, times(1)).saveMovie(movie);
//    }
//
//    /**
//     * Factory method to create test Movie objects
//     */
//    private Movie createTestMovie(Integer id, String title, String director, Integer year) {
//        Movie movie = new Movie();
//        movie.setId(id);
//        movie.setTitle(title);
//        movie.setDirector(director);
//        movie.setYear(year);
//        return movie;
//    }
//}
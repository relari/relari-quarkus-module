package pe.com.relari;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import pe.com.relari.model.api.Movie;
import pe.com.relari.model.common.ApiResponse;
import pe.com.relari.service.MovieService;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.Mockito.when;

@QuarkusTest
class MovieResourceTest {

    @InjectMock
    MovieService movieService;

    @Test
    void testGetMoviesReturnsOkStatus() {

        given()
                .when().get("/v1/movies")
                .then()
                .statusCode(200)
                .body("status", is("OK"))
                .body("statusCode", is(200))
                .body("data", instanceOf(java.util.List.class));
    }

    @Test
    void testGetMoviesEmptyListInitially() {
        given()
                .when().get("/v1/movies")
                .then()
                .statusCode(200)
                .body("status", equalTo("OK"))
                .body("statusCode", equalTo(200))
                .body("data.size()", equalTo(0));
    }

//    @Test
//    void testGetMovieByIdNotFound() {
//        given()
//                .when().get("/v1/movies/999")
//                .then()
//                .statusCode(404);
//    }

    @Test
    void testAddMovieReturnsCreated() {
        String movieJson = """
            {
              "id": 1,
              "title": "Inception",
              "director": "Christopher Nolan",
              "releaseYear": 2010
            }
            """;

        given()
                .contentType("application/json")
                .body(movieJson)
                .when().post("/v1/movies")
                .then()
                .statusCode(201);
    }

    @Test
    void testGetMoviesWithMockedData() {
        // Arrange
        Movie movie = new Movie();
        movie.setId(1);
        movie.setTitle("Inception");
        movie.setDirector("Christopher Nolan");
        movie.setYear(2010);

        when(movieService.getMovies()).thenReturn(List.of(movie));

        // Act & Assert
        given()
                .when().get("/v1/movies")
                .then()
                .statusCode(200)
                .body("status", equalTo("OK"))
                .body("statusCode", equalTo(200))
                .body("data.size()", equalTo(1))
                .body("data.size()", is(greaterThan(0)))
                .body("data[0].title", equalTo("Inception"))
                .body("data[0].director", equalTo("Christopher Nolan"));
    }

    @Test
    void testGetMovieByIdWithMockedData() {
        // Arrange
        Movie movie = new Movie();
        movie.setId(1);
        movie.setTitle("The Matrix");
        movie.setDirector("Wachowski");
        movie.setYear(1999);

        when(movieService.getMovie(1)).thenReturn(movie);

        // Act & Assert
        given()
                .when().get("/v1/movies/1")
                .then()
                .statusCode(200)
                .body("status", equalTo("OK"))
                .body("data.title", equalTo("The Matrix"));
    }

}
//package pe.com.relari.dao.impl;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import io.quarkus.panache.mock.PanacheMock;
//import io.quarkus.test.junit.QuarkusTest;
//import jakarta.inject.Inject;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import pe.com.relari.dao.repository.MovieEntity;
//
//import java.util.List;
//import java.util.Optional;
//
//// Anotación para indicar que es una prueba de Quarkus
//@QuarkusTest
//class MovieDaoImplTest {
//
//
//    MovieEntity movieEntity = Mockito.mock(MovieEntity.class);
//
//    // Inyecta la clase que vamos a probar
//    @Inject
//    MovieDaoImpl movieDao;
//
//    // Se ejecuta antes de todas las pruebas para configurar el mock
////    @BeforeAll
////    void setup() {
////        // Mockear la clase Panache para simular su comportamiento estático
//////        PanacheMock.mock(MovieEntity.class);
////    }
//
//    @Test
//    void testGetMovies() {
//        // Configurar el comportamiento del mock para el método estático listAll()
//        Mockito.when(MovieEntity.listAll()).thenReturn(List.of(
//                new MovieEntity("Pelicula A", 2020, "Director 1"),
//                new MovieEntity("Pelicula B", 2021, "Director 2")
//        ));
//
//        // Llamar al método del DAO
//        List<MovieEntity> movies = movieDao.getMovies();
//
//
//
////        // Verificar que el método estático fue llamado
////        Mockito.verify(MovieEntity.class, Mockito.times(1)).listAll();
//
//        // Verificar el resultado
//        assertEquals(2, movies.size());
////        Assertions.assertEquals("Pelicula A", movies.get(0).title);
//    }
//
////    @Test
////    void testGetMovieById() {
////        // Configurar el comportamiento del mock para el método estático findById()
////        MovieEntity mockMovie = new MovieEntity("Mocked Movie", "Mock Director", 2022);
////        Mockito.when(MovieEntity.findById(1)).thenReturn(mockMovie);
////
////        // Llamar al método del DAO
////        Optional<MovieEntity> movie = movieDao.getMovie(1);
////
////        // Verificar que el método estático fue llamado
////        Mockito.verify(MovieEntity.class, Mockito.times(1)).findById(1);
////
////        // Verificar el resultado
////        Assertions.assertTrue(movie.isPresent());
////        Assertions.assertEquals("Mocked Movie", movie.get().title);
////    }
////
////    @Test
////    public void testSaveMovie() {
////        // Preparar el objeto para guardar
////        MovieEntity movieToSave = new MovieEntity("Pelicula C", "Director 3", 2023);
////
////        // Llamar al método del DAO
////        movieDao.saveMovie(movieToSave);
////
////        // Verificar que el método persist fue llamado con el objeto correcto
////        // Esto confirma que la lógica de tu método saveMovie funciona como se espera
////        Mockito.verify(PanacheMock.get
////                (MovieEntity.class)).persist(movieToSave);
////
////        // Opcional: Usar Mockito.when para simular una excepción si lo deseas
////    }
//}

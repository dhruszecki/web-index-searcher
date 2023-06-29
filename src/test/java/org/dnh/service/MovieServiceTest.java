package org.dnh.service;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;

@MicronautTest
public class MovieServiceTest {

    @Inject
    MovieService movieService;
    private static final Logger LOG = LoggerFactory.getLogger(MovieServiceTest.class);

    //@Test
    // void testSaveDocument() {
    //     Movie movie1 = new Movie("1", "Un Oso verde");
    //     Movie movie2 = new Movie("1", "Un Oso amarillo");
    //     Movie movie3 = new Movie("1", "Un Oso violeta");
    //     Movie movie4 = new Movie("1", "Un Pez violeta");
    //     Movie movie5 = new Movie("1", "Un Perro violeta");
    //     Movie movie6 = new Movie("1", "Un Gato Rojo");
        
    //     List.of(movie1, movie2, movie3, movie4, movie5, movie6).forEach(m -> movieService.saveMovie(m));

    //     Assertions.assertTrue(true);
    // }


    @Test
    void testSearchDocuments() {
        List<Movie> movies = movieService.searchMovies("Un O");
        LOG.info(Arrays.toString(movies.toArray()));

        Assertions.assertTrue(!movies.isEmpty());
    }
    
}

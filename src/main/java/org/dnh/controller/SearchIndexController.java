package org.dnh.controller;

import java.util.List;

import org.dnh.service.Movie;
import org.dnh.service.MovieService;

import io.micronaut.http.HttpResponse;
import static io.micronaut.http.HttpResponse.ok;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.validation.Validated;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;

@Controller("/movies")
@RequiredArgsConstructor(onConstructor_ = {@Inject})
@Validated
public class SearchIndexController {

    private final MovieService movieService;

    @Get(uri = "/", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<List<Movie>> get(@QueryValue String query) {
        return ok(movieService.searchMovies(query));
    }

}
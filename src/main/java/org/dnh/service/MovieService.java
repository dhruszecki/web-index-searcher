package org.dnh.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.dnh.exception.MovieServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import io.micronaut.context.annotation.Value;
import jakarta.inject.Singleton;

@Singleton
public class MovieService {

    private static final Logger LOG = LoggerFactory.getLogger(MovieService.class);

    @Value("${elasticsearch.indexes.movies}")
    String moviesIndex;

    private final ElasticsearchClient client;

    public MovieService(ElasticsearchClient client) {
        this.client = client;
    }

    public String saveMovie(Movie movie) {
        try {
            IndexRequest<Movie> indexRequest = new IndexRequest.Builder<Movie>()
                .index(moviesIndex)
                .document(movie)
                .build();

            IndexResponse indexResponse = client.index(indexRequest);
            String id = indexResponse.id();

            LOG.info("Document for '{}' {} successfully in ES. The id is: {}", movie, indexResponse.result(), id);
            return id;
        } catch (ElasticsearchException | IOException e) {
            String errorMessage = String.format("An exception occurred while indexing '%s'", movie);
            LOG.error(errorMessage);
            throw new MovieServiceException(errorMessage, e);
        }
    }
    public List<Movie> searchMovies(String title) {
        try {
            SearchResponse<Movie> searchResponse = client.search((s) ->
                s.index(moviesIndex)
                    .query(q -> q.match(m ->
                        m.field("title")
                         .query(title)
                    )), Movie.class
            );
            LOG.info("Searching for '{}' took {} and found {}", title, searchResponse.took(), searchResponse.hits().total().value());

            return searchResponse.hits().hits().stream()
                .map(m -> m.source())
                .collect(Collectors.toList());
            

        } catch (ElasticsearchException | IOException e) {
            String errorMessage = String.format("An exception occurred while searching for title '%s'", title);
            LOG.error(errorMessage);
            throw new MovieServiceException(errorMessage, e);
        }
    }

}
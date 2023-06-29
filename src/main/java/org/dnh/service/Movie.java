package org.dnh.service;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class Movie {

    private String id;
    private String title;

    public Movie(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "imdb='" + id + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}

package com.example.themoviedb.data.domain;

import com.example.themoviedb.data.model.Genre;

import java.util.List;

public class ApiGenresResponseSchema {

    /**
     * The data payload of the genres response
     */
    public List<Genre> genres;

    /**
     * Getter for the payload data for simpler {@code .map}s
     */
    public List<Genre> getGenres() {
        return genres;
    }

    @Override
    public String toString() {
        return "ApiGenresResponseSchema{" +
            "genres=" + genres +
            '}';
    }
}

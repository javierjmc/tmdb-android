package com.example.themoviedb.data.domain;

import com.example.themoviedb.data.model.Genre;

import java.util.List;

import io.reactivex.Observable;

/**
 * Interface representing the genres' api operations
 */
public interface GenresRepo {

    /**
     * Gets the full list of genres from the api.
     */
    Observable<List<Genre>> getGenres();

}

package com.example.themoviedb.data.domain;

import com.example.themoviedb.data.model.Genre;

import java.util.List;

import io.reactivex.Observable;

/**
 * Interface representing the genres' local storage operations
 */
public interface GenresDataRepo {

    /**
     * Gets the full list of genres from the local storage.
     */
    Observable<List<Genre>> getGenresLocal();

    /**
     * Gets a list of genre names from the local storage.
     *
     * @param genreIds List of genre ids to fetch the names from.
     */
    Observable<List<String>> getGenreNames(List<Integer> genreIds);

    /**
     * Stores a list of genres in the local storage.
     *
     * @param genres List of genres to store locally.
     */
    void storeGenresLocal(List<Genre> genres);

}

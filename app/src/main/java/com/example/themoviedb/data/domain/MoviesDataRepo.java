package com.example.themoviedb.data.domain;

import com.example.themoviedb.data.model.Movie;

import java.util.List;

import io.reactivex.Observable;

/**
 * Interface representing the movies' local storage operations
 */
public interface MoviesDataRepo {

    Observable<List<Movie>> getMostPopularMoviesLocal();

    void storeMoviesLocal(List<Movie> movies);

}

package com.example.themoviedb.data.domain;

import com.example.themoviedb.data.model.Movie;

import java.util.List;

import io.reactivex.Observable;

/**
 * Interface representing the movies' api operations
 */
public interface MoviesRepo {

    Observable<List<Movie>> getMostPopularMovies(final int page);

}

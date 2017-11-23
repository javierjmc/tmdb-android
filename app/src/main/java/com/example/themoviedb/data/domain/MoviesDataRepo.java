package com.example.themoviedb.data.domain;

import com.example.themoviedb.data.model.Movie;

import java.util.List;

import io.reactivex.Observable;

/**
 * Interface representing the movies' local storage operations
 */
public interface MoviesDataRepo {

    /**
     * Gets most popular {@link Movie}s from the local storage.
     *
     * @param page Page to fetch from the paginated.
     */
    Observable<List<Movie>> getMostPopularMoviesLocal(int page);

    /**
     * Gets {@link Movie} from the local storage.
     *
     * @param movieId Id of the movie.
     */
    Observable<Movie> getMovie(int movieId);

    /**
     * Stores a {@link List} of {@link Movie}s locally.
     *
     * @param movies Movies to be stored.
     */
    void storeMoviesLocal(List<Movie> movies);

    /**
     * Updates a {@link Movie} locally.
     *
     * @param movie Movie to be stored.
     */
    void updateMovieDetailsLocal(Movie movie);

    /**
     * Marks a {@link Movie} as watched locally.
     *
     * @param movieId Id of the movie to be updated
     * @param watched True if the movie has been watched, false otherwise.
     */
    Observable<Boolean> markMovieAsWatched(int movieId, boolean watched);
}

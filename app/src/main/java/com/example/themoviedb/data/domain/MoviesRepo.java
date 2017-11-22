package com.example.themoviedb.data.domain;

import android.util.Pair;

import com.example.themoviedb.data.model.FeedItem;
import com.example.themoviedb.data.model.Movie;

import java.util.List;

import io.reactivex.Observable;

/**
 * Interface representing the movies' api operations
 */
public interface MoviesRepo {

    /**
     * Gets most popular {@link Movie}s.
     *
     * @param page Page to fetch from the paginated api.
     */
    Observable<Pair<Integer, List<FeedItem>>> getMostPopularMovies(final int page);

    /**
     * Gets {@link Movie} details.
     *
     * @param movieId Id of the movie to get details of.
     */
    Observable<Movie> getMovieDetails(final int movieId);

    /**
     * Gets similar movies given based on a {@link Movie}.
     *
     * @param movieId Id of the movie to get similar movies from.
     */
    Observable<List<Movie>> getSimilarMovies(final int movieId);
}

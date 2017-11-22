package com.example.themoviedb.moviedetails;

import android.util.Pair;

import com.hannesdorfmann.mosby3.mvp.MvpView;

import java.util.List;

import io.reactivex.Observable;

public interface MovieDetailsView extends MvpView {

    /**
     * The intent to load movie details
     *
     * @return Observable with the id of the movie to be displayed.
     */
    Observable<Integer> loadMovieDetailsIntent();

    /**
     * The intent to load similar movies to the one being displayed
     *
     * @return Observable with the id of the movie to load similar movies from.
     */
    Observable<Integer> loadSimilarMoviesIntent();

    /**
     * The intent to load genres for a given movie
     *
     * @return Observable with the ids of the genres.
     */
    Observable<List<Integer>> loadGenresForMovieIntent();

    /**
     * The intent to mark the movie as watched
     *
     * @return Pair, where the first element is the movie id to be flagged as watched,
     * while the second element is a boolean representing whether the movie has been watched or not
     */
    Observable<Pair<Integer, Boolean>> markAsWatchedIntent();

    /**
     * Renders the View
     *
     * @param viewState The current viewState state that should be displayed
     */
    void render(MovieDetailsViewState viewState);
}

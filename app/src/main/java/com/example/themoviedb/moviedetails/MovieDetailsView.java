package com.example.themoviedb.moviedetails;

import com.hannesdorfmann.mosby3.mvp.MvpView;

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
     * The intent to mark the movie as watched
     *
     * @return Boolean representing whether the movie has been watched or not
     */
    Observable<Boolean> markAsWatchedIntent();

    /**
     * Renders the View
     *
     * @param viewState The current viewState state that should be displayed
     */
    void render(MovieDetailsViewState viewState);
}

package com.example.themoviedb.movielist;

import com.hannesdorfmann.mosby3.mvp.MvpView;

import io.reactivex.Observable;

public interface MovieListView extends MvpView {

    /**
     * The intent to load movies
     *
     * @return The value of the emitted item (boolean) can be ignored. true or false has no different meaning.
     */
    Observable<Boolean> loadMoviesIntent();

    /**
     * The intent to load movie details
     *
     * @return Observable with the id of the movie tp be displayed.
     */
    Observable<Long> loadMovieDetailsIntent();

    /**
     * Renders the View
     *
     * @param viewState The current viewState state that should be displayed
     */
    void render(MovieListViewState viewState);
}

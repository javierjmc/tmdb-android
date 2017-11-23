package com.example.themoviedb.movielist;

import com.hannesdorfmann.mosby3.mvp.MvpView;

import io.reactivex.Observable;

public interface MovieListView extends MvpView {

    /**
     * The intent to load movies' first page
     *
     * @return The value of the emitted item (boolean) can be ignored. true or false has no different meaning.
     */
    Observable<Boolean> loadMoviesFirstPageIntent();

    /**
     * The intent to load movies' next page
     *
     * @return The value of the next page to be loaded.
     */
    Observable<Integer> loadMoviesNextPageIntent();

    /**
     * The intent to load movie genres
     *
     * @return The value of the emitted item (boolean) can be ignored. true or false has no different meaning.
     */
    Observable<Boolean> loadGenresIntent();

    /**
     * Renders the View
     *
     * @param viewState The current viewState state that should be displayed
     */
    void render(MovieListViewState viewState);
}

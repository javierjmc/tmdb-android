package com.example.themoviedb.movielist;

import com.example.themoviedb.data.Movie;
import com.hannesdorfmann.mosby3.mvi.MviBasePresenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;

public class MovieListPresenter extends MviBasePresenter<MovieListView, MovieListViewState> {

    private final List emptyList = Collections.EMPTY_LIST;
    private final List dummy = new ArrayList<Movie>() {{
        add(new Movie());
        add(new Movie());
        add(new Movie());
        add(new Movie());
    }};

    @Override
    protected void bindIntents() {

        Observable<MovieListViewState> loadMovies = intent(MovieListView::loadMoviesIntent)
            .flatMap(ignored -> Observable.just(dummy)
                .map(items -> new MovieListViewState(true, null, items, false, null))
                .startWith(new MovieListViewState(false, null, emptyList, false, null))
                .onErrorReturn(error -> new MovieListViewState(false, error, emptyList, false, null)));

        subscribeViewState(loadMovies, MovieListView::render);
    }
}

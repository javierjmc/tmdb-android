package com.example.themoviedb.movielist;

import com.example.themoviedb.data.domain.MoviesRepo;
import com.hannesdorfmann.mosby3.mvi.MviBasePresenter;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class MovieListPresenter extends MviBasePresenter<MovieListView, MovieListViewState> {

    private final MoviesRepo moviesRepo;

    @Inject
    public MovieListPresenter(final MoviesRepo moviesRepo) {
        this.moviesRepo = moviesRepo;
    }

    private final List emptyList = Collections.EMPTY_LIST;

    @Override
    protected void bindIntents() {

        Observable<MovieListViewState> loadMovies = intent(MovieListView::loadMoviesFirstPageIntent)
            .flatMap(ignored -> moviesRepo.getMostPopularMovies(1)
                .map(movies -> MovieListViewState.builder().loadingFirstPage(false).firstPageError(null).data(movies).build())
                .startWith(MovieListViewState.builder().loadingFirstPage(true).firstPageError(null).build())
                .onErrorReturn(error -> MovieListViewState.builder().loadingFirstPage(false).firstPageError(error).build()));

        subscribeViewState(loadMovies, MovieListView::render);
    }
}


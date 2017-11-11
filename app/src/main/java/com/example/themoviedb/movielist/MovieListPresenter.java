package com.example.themoviedb.movielist;

import com.example.themoviedb.data.model.Movie;
import com.hannesdorfmann.mosby3.mvi.MviBasePresenter;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;

public class MovieListPresenter extends MviBasePresenter<MovieListView, MovieListViewState> {

    private final List emptyList = Collections.EMPTY_LIST;
    private final List dummy = new ArrayList<Movie>() {{
        add(Movie.builder()
            .id(0)
            .voteAverage(7.5f)
            .voteCount(10)
            .title("It")
            .posterPath(null)
            .originalLanguage(null)
            .originalTitle(null)
            .genreIds(new ArrayList<Long>() {{
                add(1L);
                add(2L);
            }})
            .adult(false)
            .overview("Movie's overview")
            .releaseDate(DateTime.now())
            .build());

        add(Movie.builder()
            .id(1)
            .voteAverage(8f)
            .voteCount(10)
            .title("It 2")
            .posterPath(null)
            .originalLanguage(null)
            .originalTitle(null)
            .genreIds(new ArrayList<Long>() {{
                add(1L);
                add(2L);
            }})
            .adult(false)
            .overview("Movie's overview 2")
            .releaseDate(DateTime.now())
            .build());

    }};

    @Override
    protected void bindIntents() {

        Observable<MovieListViewState> loadMovies = intent(MovieListView::loadMoviesFirstPageIntent)
            .flatMap(ignored -> Observable.just(dummy)
                .map(items -> new MovieListViewState(false, null, items, false, null))
                .startWith(new MovieListViewState(true, null, emptyList, false, null))
                .onErrorReturn(error -> new MovieListViewState(false, (Throwable) error, emptyList, false, null)));

        subscribeViewState(loadMovies, MovieListView::render);
    }
}

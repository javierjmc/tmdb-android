package com.example.themoviedb.movielist;

import com.example.themoviedb.data.Movie;
import com.hannesdorfmann.mosby3.mvi.MviBasePresenter;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;

public class MovieListPresenter extends MviBasePresenter<MovieListView, MovieListViewState> {

    private final List emptyList = Collections.EMPTY_LIST;
    private final List dummy = new ArrayList<Movie>() {{
        add(new Movie(0, 7.5f, 499, "It", null, null, null, new ArrayList<Long>(){{add(1l); add(2l);}}, false, "overview of the movie", DateTime.now()));
        add(new Movie(1, 5.5f, 499, "Movie 2", null, null, null, new ArrayList<Long>(){{add(1l); add(2l);}}, false, "overview of the movie 2", DateTime.now()));
        add(new Movie(2, 3f, 499, "It 3", null, null, null, new ArrayList<Long>(){{add(1l); add(2l);}}, false, "overview of the movie 3", DateTime.now()));
        add(new Movie(3, 1f, 499, "It 5", null, null, null, new ArrayList<Long>(){{add(1l); add(2l);}}, false, "overview of the movie 4", DateTime.now()));
    }};



//    public Movie(long id, float voteAverage, long voteCount, String title, String posterPath, String originalLanguage, String originalTitle, List<Long> genres, boolean adult, String overview, DateTime releaseDate) {

    @Override
    protected void bindIntents() {

        Observable<MovieListViewState> loadMovies = intent(MovieListView::loadMoviesIntent)
            .flatMap(ignored -> Observable.just(dummy)
                .map(items -> new MovieListViewState(false, null, items, false, null))
                .startWith(new MovieListViewState(true, null, emptyList, false, null))
                .onErrorReturn(error -> new MovieListViewState(false, null/*error*/, emptyList, false, null)));

        subscribeViewState(loadMovies, MovieListView::render);
    }
}

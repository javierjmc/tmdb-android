package com.example.themoviedb.movielist;

import android.util.Pair;

import com.example.themoviedb.data.domain.ApiSchedulers;
import com.example.themoviedb.data.domain.MoviesDataRepo;
import com.example.themoviedb.data.domain.MoviesRepo;
import com.example.themoviedb.data.domain.TestTheMovieDbApi;
import com.example.themoviedb.data.model.FeedItem;
import com.example.themoviedb.data.model.Movie;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class TestMoviesRepoImpl implements MoviesRepo {

    private final MoviesDataRepo moviesDataRepo;
    private final TestTheMovieDbApi theMovieDbApi;
    private final ApiSchedulers apiSchedulers;

    @Inject
    public TestMoviesRepoImpl(final MoviesDataRepo moviesDataRepo, final TestTheMovieDbApi theMovieDbApi, final ApiSchedulers apiSchedulers) {
        this.moviesDataRepo = moviesDataRepo;
        this.theMovieDbApi = theMovieDbApi;
        this.apiSchedulers = apiSchedulers;
    }

    @Override
    public Observable<Pair<Integer, List<FeedItem>>> getMostPopularMovies(int page) {
        return null;
    }

    @Override
    public Observable<Movie> getMovieDetails(int movieId) {
        return null;
    }

    @Override
    public Observable<List<Movie>> getSimilarMovies(int movieId) {
        return null;
    }

    /*@Override
    public Observable<List<FeedItem>> getMostPopularMovies(final int page) {
        return theMovieDbApi.getMovies(null, page, null, null)
            .map(response -> response.getResults())
            .toObservable()
            .compose(apiSchedulers.forObservable());
    }*/
}

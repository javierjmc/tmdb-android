package com.example.themoviedb.movielist;

import com.example.themoviedb.data.domain.ApiSchedulers;
import com.example.themoviedb.data.domain.MoviesDataRepo;
import com.example.themoviedb.data.domain.MoviesRepo;
import com.example.themoviedb.data.domain.TheMovieDbApi;
import com.example.themoviedb.data.model.Movie;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class MoviesRepoImpl implements MoviesRepo {

    private final MoviesDataRepo moviesDataRepo;
    private final TheMovieDbApi theMovieDbApi;
    private final ApiSchedulers apiSchedulers;

    @Inject
    public MoviesRepoImpl(final MoviesDataRepo moviesDataRepo, final TheMovieDbApi theMovieDbApi, final ApiSchedulers apiSchedulers) {
        this.moviesDataRepo = moviesDataRepo;
        this.theMovieDbApi = theMovieDbApi;
        this.apiSchedulers = apiSchedulers;
    }

    @Override
    public Observable<List<Movie>> getMostPopularMovies(final int page) {
        return theMovieDbApi.getMovies(null, page, null, null)
            .map(response -> response.getResults())
            .toObservable()
            .compose(apiSchedulers.forObservable());
    }
}

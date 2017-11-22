package com.example.themoviedb.movielist;

import android.util.Pair;

import com.example.themoviedb.data.domain.ApiMovieListResponseSchema;
import com.example.themoviedb.data.domain.ApiSchedulers;
import com.example.themoviedb.data.domain.MoviesDataRepo;
import com.example.themoviedb.data.domain.MoviesRepo;
import com.example.themoviedb.data.domain.TheMovieDbApi;
import com.example.themoviedb.data.model.FeedItem;
import com.example.themoviedb.data.model.Movie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import timber.log.Timber;

public class MoviesRepoImpl implements MoviesRepo {

    private final MoviesDataRepo moviesDataRepo;
    private final TheMovieDbApi theMovieDbApi;
    private final ApiSchedulers apiSchedulers;

    private LinkedHashMap<Integer, FeedItem> movies = new LinkedHashMap<>();

    @Inject
    public MoviesRepoImpl(final MoviesDataRepo moviesDataRepo, final TheMovieDbApi theMovieDbApi, final ApiSchedulers apiSchedulers) {
        this.moviesDataRepo = moviesDataRepo;
        this.theMovieDbApi = theMovieDbApi;
        this.apiSchedulers = apiSchedulers;
    }

    @Override
    public Observable<Pair<Integer, List<FeedItem>>> getMostPopularMovies(final int page) {
        return Observable.combineLatest(moviesDataRepo.getMostPopularMoviesLocal(page), getMoviesFromApi(page), (localMovies, apiMovies) -> {
            for (FeedItem apiMovie : apiMovies) {
                movies.put(((Movie) apiMovie).id(), apiMovie);
            }

            for (FeedItem localMovie : localMovies) {
                movies.put(((Movie) localMovie).id(), localMovie);
            }

            return new ArrayList<>(movies.values());
        })
            .map(movies -> new Pair<Integer, List<FeedItem>>(page, movies))
            .compose(apiSchedulers.forObservable());
    }

    @Override
    public Observable<Movie> getMovieDetails(int movieId) {
        return theMovieDbApi.getMovieDetails(movieId, null)
            .toObservable()
            .doOnNext(movie -> {
                Timber.d("Dispatching movie with id %d from API...", movie.id());
                moviesDataRepo.updateMovieDetailsLocal(movie);
            })
            .onErrorReturn(t -> {
                Timber.e(t.getMessage());
                return Movie.builder().build();
            })
            .compose(apiSchedulers.forObservable());
    }

    @Override
    public Observable<List<Movie>> getSimilarMovies(int movieId) {
        return theMovieDbApi.getSimilarMovies(movieId, null, 1)
            .map(ApiMovieListResponseSchema::getResults)
            .toObservable()
            .doOnNext(movies -> {
                Timber.d("Dispatching %d movies from API...", movies.size());
                moviesDataRepo.storeMoviesLocal(movies);
            })
            .onErrorReturn(t -> {
                Timber.e(t.getMessage());
                return Collections.emptyList();
            })
            .compose(apiSchedulers.forObservable());
    }

    private Observable<List<Movie>> getMoviesFromApi(final int page) {
        return theMovieDbApi.getMovies(null, page, null, null)
            .map(ApiMovieListResponseSchema::getResults)
            .toObservable()
            .doOnNext(movies -> {
                Timber.d("Dispatching %d movies from page %d from API...", movies.size(), page);
                moviesDataRepo.storeMoviesLocal(movies);
            })
            .onErrorReturn(t -> {
                Timber.e(t.getMessage());
                return Collections.emptyList();
            })
            .compose(apiSchedulers.forObservable());
    }

}

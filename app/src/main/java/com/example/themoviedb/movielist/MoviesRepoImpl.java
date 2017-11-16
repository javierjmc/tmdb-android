package com.example.themoviedb.movielist;

import android.util.Pair;

import com.example.themoviedb.data.domain.ApiSchedulers;
import com.example.themoviedb.data.domain.MoviesDataRepo;
import com.example.themoviedb.data.domain.MoviesRepo;
import com.example.themoviedb.data.domain.TheMovieDbApi;
import com.example.themoviedb.data.model.FeedItem;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import timber.log.Timber;

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
    public Observable<Pair<Integer, List<FeedItem>>> getMostPopularMovies(final int page) {
        /*return getMoviesFromApi(page)
            .map(movies -> new Pair(page, movies))
            .compose(apiSchedulers.forObservable());*/


            /*Observable.concatArray(moviesDataRepo.getMostPopularMoviesLocal(),
            getMoviesFromApi(page))
            .map(movies -> new Pair(page, movies))
            .compose(apiSchedulers.forObservable());*/


        return theMovieDbApi.getMovies(null, page, null, null)
            .map(response -> new Pair(response.getPage(), response.getResults()))
            .toObservable()
            .doOnNext(resultPair -> {
                Timber.d("Dispatching %d movies from page %d from API...", ((List) resultPair.second).size(), resultPair.first);
                moviesDataRepo.storeMoviesLocal((List) resultPair.second);
            })
            .onErrorReturn(t -> {
                Timber.e((Throwable) t);
                return new Pair(page, moviesDataRepo.getMostPopularMoviesLocal().blockingFirst());
            })
            .compose(apiSchedulers.forObservable());
    }

    /*private Observable<List<FeedItem>> getMoviesFromApi(final int page) {
        return theMovieDbApi.getMovies(null, page, null, null)
            .map(response -> response.getResults())
            .toObservable()
            .doOnNext(movies -> {
                Timber.d("Dispatching %d movies from page %d from API...", movies.size(), page);
                moviesDataRepo.storeMoviesLocal(movies);
            })
            .onErrorReturn(t -> {
                Timber.e(t.getMessage());
                return Collections.EMPTY_LIST;
            })
            .compose(apiSchedulers.forObservable());
    }
*/
}

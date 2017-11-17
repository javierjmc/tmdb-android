package com.example.themoviedb.movielist;

import com.example.themoviedb.data.db.daos.MovieDao;
import com.example.themoviedb.data.domain.MoviesDataRepo;
import com.example.themoviedb.data.model.Movie;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class MoviesDataRepoImpl implements MoviesDataRepo {

    private final MovieDao movieDao;

    @Inject
    public MoviesDataRepoImpl(final MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    public Observable<List<Movie>> getMostPopularMoviesLocal() {
        return movieDao.getMovies()
            .toObservable()
            .doOnNext(movies -> Timber.d("Dispatching %d movies from DB...", movies.size()));
    }

    @Override
    public void storeMoviesLocal(List<Movie> movies) {
        Observable.fromCallable(() -> movieDao.insertAll(movies))
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe(t -> Timber.d("Inserted %d movies from API in DB...", movies.size()));
    }
}

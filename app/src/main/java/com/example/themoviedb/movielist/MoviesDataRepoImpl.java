package com.example.themoviedb.movielist;

import com.example.themoviedb.data.db.daos.MovieDao;
import com.example.themoviedb.data.domain.MoviesDataRepo;
import com.example.themoviedb.data.model.Movie;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class MoviesDataRepoImpl implements MoviesDataRepo {
    private static final int PAGE_LIMIT = 20;

    private final MovieDao movieDao;

    @Inject
    public MoviesDataRepoImpl(final MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    public Observable<List<Movie>> getMostPopularMoviesLocal(int page) {
        return movieDao.getMovies()
            .map(movies -> {
                if (movies.size() >= page * PAGE_LIMIT) {
                    return movies.subList((page - 1) * PAGE_LIMIT, (PAGE_LIMIT * page) - 1);
                } else {
                    return new ArrayList<Movie>();
                }
            })
            .toObservable()
            .doOnNext(movies -> Timber.d("Dispatching %d movies from DB...", movies.size()));
    }

    @Override
    public Observable<Movie> getMovie(int movieId) {
        return movieDao.getMovie(movieId)
            .toObservable()
            .doOnNext(movies -> Timber.d("Dispatching movie with id %d from DB...", movieId));
    }

    @Override
    public void storeMoviesLocal(List<Movie> movies) {
        Observable.fromCallable(() -> movieDao.insertAll(movies))
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe(t -> Timber.d("Inserted %d movies from API in DB...", movies.size()));
    }

    @Override
    public void updateMovieDetailsLocal(Movie movie) {
        Observable.fromCallable(() -> movieDao.updateMovieDetails(movie.id(), movie.tagline(), movie.runtime()))
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe(t -> Timber.d("Updated movie details with id %d from API in DB...", movie.id()));
    }

    @Override
    public Observable<Boolean> markMovieAsWatched(int movieId, boolean watched) {
        return Observable.fromCallable(() -> movieDao.updateMovieWatched(movieId, watched))
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .doOnNext(t -> Timber.d("Marked movie with id %d as watched %b...", movieId, watched))
            .map(ignored -> watched);
    }
}

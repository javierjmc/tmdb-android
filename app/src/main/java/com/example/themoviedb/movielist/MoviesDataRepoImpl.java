package com.example.themoviedb.movielist

import com.example.themoviedb.data.db.daos.GenreDao
import com.example.themoviedb.data.db.daos.MovieDao
import com.example.themoviedb.data.domain.MoviesDataRepo
import com.example.themoviedb.data.model.Movie

import javax.inject.Inject

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MoviesDataRepoImpl @Inject
constructor(private val movieDao: MovieDao, private val genreDao: GenreDao) : MoviesDataRepo {

    override fun getMostPopularMoviesLocal(): Observable<List<Movie>> {
        return movieDao.movies
                .toObservable()
                .doOnNext { movies -> Timber.d("Dispatching %d movies from DB...", movies.size) }
    }

    override fun storeMoviesLocal(movies: List<Movie>) {
        Observable.fromCallable { movieDao.insertAll(movies) }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe { t -> Timber.d("Inserted %d movies from API in DB...", movies.size) }
    }
}

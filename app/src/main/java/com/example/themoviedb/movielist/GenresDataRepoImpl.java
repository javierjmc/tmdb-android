package com.example.themoviedb.movielist;

import com.example.themoviedb.data.db.daos.GenreDao;
import com.example.themoviedb.data.domain.GenresDataRepo;
import com.example.themoviedb.data.model.Genre;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;


public class GenresDataRepoImpl implements GenresDataRepo {
    private final GenreDao genreDao;

    @Inject
    public GenresDataRepoImpl(final GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public Observable<List<Genre>> getGenresLocal() {
        return genreDao.getGenres()
            .toObservable()
            .doOnNext(genres -> Timber.d("Dispatching %d genres from DB...", genres.size()));
    }

    @Override
    public Observable<List<String>> getGenreNames(List<Integer> genreIds) {
        return genreDao.getGenres()
            .toObservable()
            .flatMapIterable(genres -> genres)
            .filter(genre -> genreIds.contains(genre.id()))
            .map(Genre::name)
            .toList()
            .toObservable()
            .doOnNext(genres -> Timber.d("Dispatching %d genres from DB...", genres.size()));
    }

    @Override
    public void storeGenresLocal(List<Genre> genres) {
        Observable.fromCallable(() -> genreDao.insertAll(genres))
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe(t -> Timber.d("Inserted %d genres from API in DB...", genres.size()));
    }
}

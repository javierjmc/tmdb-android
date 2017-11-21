package com.example.themoviedb.movielist;

import com.example.themoviedb.data.domain.ApiGenresResponseSchema;
import com.example.themoviedb.data.domain.ApiSchedulers;
import com.example.themoviedb.data.domain.GenresDataRepo;
import com.example.themoviedb.data.domain.GenresRepo;
import com.example.themoviedb.data.domain.TheMovieDbApi;
import com.example.themoviedb.data.model.Genre;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import timber.log.Timber;

public class GenresRepoImpl implements GenresRepo {

    private final GenresDataRepo genresDataRepo;
    private final TheMovieDbApi theMovieDbApi;
    private final ApiSchedulers apiSchedulers;

    @Inject
    public GenresRepoImpl(final GenresDataRepo genresDataRepo, final TheMovieDbApi theMovieDbApi, final ApiSchedulers apiSchedulers) {
        this.genresDataRepo = genresDataRepo;
        this.theMovieDbApi = theMovieDbApi;
        this.apiSchedulers = apiSchedulers;
    }

    @Override
    public Observable<List<Genre>> getGenres() {
        return theMovieDbApi.getGenres()
            .map(ApiGenresResponseSchema::getGenres)
            .toObservable()
            .doOnNext(genres -> {
                Timber.d("Dispatching %d genres from API...", genres.size());
                genresDataRepo.storeGenresLocal(genres);
            })
            .onErrorReturn(t -> {
                Timber.e(t);
                return Collections.emptyList();
            })
            .compose(apiSchedulers.forObservable());
    }
}

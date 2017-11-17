package com.example.themoviedb.data.domain;

import com.example.themoviedb.data.model.Genre;

import java.util.List;

import io.reactivex.Observable;

/**
 * Interface representing the genres' local storage operations
 */
public interface GenresDataRepo {

    Observable<List<Genre>> getGenresLocal();

    void storeGenresLocal(List<Genre> genres);

}

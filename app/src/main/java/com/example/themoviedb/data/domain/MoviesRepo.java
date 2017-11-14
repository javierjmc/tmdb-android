package com.example.themoviedb.data.domain;

import com.example.themoviedb.data.model.FeedItem;

import java.util.List;

import io.reactivex.Observable;

/**
 * Interface representing the movies' api operations
 */
public interface MoviesRepo {

    Observable<List<FeedItem>> getMostPopularMovies(final int page);

}

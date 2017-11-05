package com.example.themoviedb.movielist;

import com.example.themoviedb.data.Movie;
import com.hannesdorfmann.mosby3.mvp.MvpView;

import java.util.List;

public interface MovieListView extends MvpView {

    void setProgressIndicator(boolean active);

    void showMovies(List<Movie> notes);

    void showMovieDetails(Long movieId);
}

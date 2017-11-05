package com.example.themoviedb.movielist;

import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby3.mvi.MviFragment;

public class MovieListFragment extends MviFragment<MovieListView, MovieListPresenter> {

    @NonNull
    @Override
    public MovieListPresenter createPresenter() {
        return new MovieListPresenter();
    }
}

package com.example.themoviedb.movielist;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.example.themoviedb.BaseFragment;
import com.example.themoviedb.R;

public class MovieListFragment extends BaseFragment<MovieListView, MovieListPresenter> implements MovieListView {

    @Override
    public int layoutRes() {
        return R.layout.fragment_movielist;
    }

    @NonNull
    @Override
    public MovieListPresenter createPresenter() {
        return new MovieListPresenter();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}

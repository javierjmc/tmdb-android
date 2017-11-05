package com.example.themoviedb.movielist;

import android.support.annotation.Nullable;

import com.example.themoviedb.data.Movie;

import java.util.List;

public final class MovieListViewState {

    private final boolean loadingMovies; // Show the loading indicator instead of recyclerView
    @Nullable
    private final Throwable moviesError; // Show an error view if != null
    private final List<Movie> data;   // The items displayed in the recyclerview
    private final boolean loadingPullToRefresh; // Shows the pull-to-refresh indicator
    @Nullable
    private final Throwable pullToRefreshError; // if != null, shows error toast that pull-to-refresh failed

    public MovieListViewState(boolean loadingMovies, Throwable moviesError, List<Movie> data, boolean loadingPullToRefresh, Throwable pullToRefreshError) {
        this.loadingMovies = loadingMovies;
        this.moviesError = moviesError;
        this.data = data;
        this.loadingPullToRefresh = loadingPullToRefresh;
        this.pullToRefreshError = pullToRefreshError;
    }

    public boolean isLoadingMovies() {
        return loadingMovies;
    }

    public Throwable getMoviesError() {
        return moviesError;
    }

    public List<Movie> getData() {
        return data;
    }

    public boolean isLoadingPullToRefresh() {
        return loadingPullToRefresh;
    }

    public Throwable getPullToRefreshError() {
        return pullToRefreshError;
    }
}

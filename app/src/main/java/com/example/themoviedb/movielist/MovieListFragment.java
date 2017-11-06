package com.example.themoviedb.movielist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.themoviedb.BaseFragment;
import com.example.themoviedb.R;
import com.example.themoviedb.movielist.list.MovieListAdapter;

import butterknife.BindView;
import io.reactivex.Observable;
import timber.log.Timber;

public class MovieListFragment extends BaseFragment<MovieListView, MovieListPresenter> implements MovieListView {

    @BindView(R.id.movie_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.progress)
    ProgressBar mProgressBar;

    private MovieListAdapter mMovieListAdapter = new MovieListAdapter();

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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView.setAdapter(mMovieListAdapter);
    }

    @Override
    public Observable<Boolean> loadMoviesIntent() {
        return Observable.just(true).doOnComplete(() -> Timber.d("loadMovies completed"));
    }

    @Override
    public Observable<Long> loadMovieDetailsIntent() {
        return null;
    }

    @Override
    public void render(MovieListViewState viewState) {
        mRecyclerView.setAdapter(mMovieListAdapter);
        mMovieListAdapter.setMovieList(viewState.getData());
        mProgressBar.setVisibility(viewState.isLoadingMovies() || viewState.isLoadingPullToRefresh() ? View.VISIBLE : View.GONE);
    }
}

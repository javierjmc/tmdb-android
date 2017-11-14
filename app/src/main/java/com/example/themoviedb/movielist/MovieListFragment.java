package com.example.themoviedb.movielist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.themoviedb.BaseFragment;
import com.example.themoviedb.R;
import com.example.themoviedb.movielist.list.MovieListAdapter;

import javax.inject.Inject;
import javax.inject.Provider;

import butterknife.BindView;
import io.reactivex.Observable;
import timber.log.Timber;

public class MovieListFragment extends BaseFragment<MovieListView, MovieListPresenter> implements MovieListView {

    @BindView(R.id.movie_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.progress)
    ProgressBar mProgressBar;

    @Inject
    Provider<MovieListPresenter> mPresenterProvider;

    private MovieListAdapter mMovieListAdapter = new MovieListAdapter();

    @Override
    public int layoutRes() {
        return R.layout.fragment_movielist;
    }

    @NonNull
    @Override
    public MovieListPresenter createPresenter() {
        return mPresenterProvider.get();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView.setAdapter(mMovieListAdapter);
    }

    @Override
    public Observable<Boolean> loadMoviesFirstPageIntent() {
        return Observable.just(true).doOnComplete(() -> Timber.d("loadMovies completed"));
    }

    @Override
    public Observable<Long> loadMovieDetailsIntent() {
        return null;
    }

    @Override
    public void render(MovieListViewState viewState) {
        mMovieListAdapter.setMovieList(viewState.data());
        mProgressBar.setVisibility(viewState.isLoading() ? View.VISIBLE : View.GONE);

        final Throwable error = viewState.getError();

        if (error != null) {
            Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}

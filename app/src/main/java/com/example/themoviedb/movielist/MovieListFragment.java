package com.example.themoviedb.movielist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.themoviedb.BaseFragment;
import com.example.themoviedb.R;
import com.example.themoviedb.data.model.Movie;
import com.example.themoviedb.moviedetails.MovieDetailsActivity;
import com.example.themoviedb.movielist.list.MovieListAdapter;
import com.jakewharton.rxbinding2.support.v7.widget.RxRecyclerView;

import javax.inject.Inject;
import javax.inject.Provider;

import butterknife.BindView;
import io.reactivex.Observable;
import timber.log.Timber;

import static com.example.themoviedb.moviedetails.MovieDetailsFragment.MOVIE_ID;
import static com.example.themoviedb.moviedetails.MovieDetailsFragment.MOVIE_TITLE;

public class MovieListFragment extends BaseFragment<MovieListView, MovieListPresenter> implements MovieListView, MovieListAdapter.OnMovieItemClickListener {

    @BindView(R.id.movie_list)
    RecyclerView mRecyclerView;

    @Inject
    Provider<MovieListPresenter> mPresenterProvider;

    private MovieListAdapter mMovieListAdapter = new MovieListAdapter();

    private Integer mPage;

    @Override
    public int layoutRes() {
        return R.layout.fragment_movielist;
    }

    @Override
    public int toolbarTitle() {
        return R.string.discover_popular_movies_screen;
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
        mMovieListAdapter.setFeedItemListener(this);
    }

    @Override
    public Observable<Boolean> loadMoviesFirstPageIntent() {
        return Observable.just(true).doOnComplete(() -> Timber.d("loadMovies completed"));
    }

    @Override
    public Observable<Integer> loadMoviesNextPageIntent() {
        return RxRecyclerView.scrollStateChanges(mRecyclerView)
            .filter(event -> !mMovieListAdapter.isLoadingNextPage())
            .filter(event -> event == RecyclerView.SCROLL_STATE_IDLE)
            .filter(event -> ((LinearLayoutManager) mRecyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition()
                == mMovieListAdapter.getFeedItems().size() - 1)
            .map(integer -> mPage + 1);
    }

    @Override
    public Observable<Boolean> loadGenresIntent() {
        return Observable.just(true).doOnComplete(() -> Timber.d("loadGenres completed"));
    }

    @Override
    public void render(MovieListViewState viewState) {
        mPage = viewState.page();
        mMovieListAdapter.setItems(viewState.data());
        mMovieListAdapter.setGenres(viewState.genres());

        boolean changed = mMovieListAdapter.setLoadingNextPage(viewState.loadingNextPage());

        if (changed && viewState.loadingNextPage() != null && viewState.loadingNextPage()) {
            mRecyclerView.smoothScrollToPosition(mMovieListAdapter.getItemCount());
        }

        mProgressBar.setVisibility(viewState.isLoading() ? View.VISIBLE : View.GONE);

        final Throwable error = viewState.getError();

        if (error != null) {
            Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onMovieItemClick(Movie movie) {
        final Intent intent = new Intent(getContext(), MovieDetailsActivity.class);
        intent.putExtra(MOVIE_ID, movie.id());
        intent.putExtra(MOVIE_TITLE, movie.title());
        startActivity(intent);
    }
}

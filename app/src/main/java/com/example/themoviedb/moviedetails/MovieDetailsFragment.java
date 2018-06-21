package com.example.themoviedb.moviedetails;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.themoviedb.BaseFragment;
import com.example.themoviedb.R;
import com.example.themoviedb.data.model.Movie;
import com.example.themoviedb.moviedetails.similar.SimilarMoviesAdapter;
import com.example.themoviedb.utils.GlideApp;
import com.hannesdorfmann.mosby3.mvi.MviPresenter;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Provider;

import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public class MovieDetailsFragment extends BaseFragment<MovieDetailsView, MovieDetailsPresenter> implements MovieDetailsView, SimilarMoviesAdapter.OnSimilarMovieItemClickListener {

    public static final String MOVIE_ID = "movieId";
    public static final String MOVIE_TITLE = "movieTitle";

    @BindView(R.id.collapsingToolbar)
    CollapsingToolbarLayout mCollapsingToolbar;
    @BindView(R.id.movie_image)
    ImageView mMoviePoster;
    @BindView(R.id.movie_year)
    TextView mMovieYear;
    @BindView(R.id.movie_runtime)
    TextView mMovieRuntime;
    @BindView(R.id.movie_genres)
    TextView mMovieGenres;
    @BindView(R.id.movie_rating)
    TextView mMovieRating;
    @BindView(R.id.movie_overview)
    TextView mMovieOverview;
    @BindView(R.id.movie_tagline)
    TextView mMovieTagline;
    @BindView(R.id.similar_movies)
    RecyclerView mSimilarMovies;
    @BindView(R.id.movie_fab)
    FloatingActionButton mMovieFab;
    @BindView(R.id.label_overview)
    TextView mLabelOverview;
    @BindView(R.id.label_similar)
    TextView mLabelSimilar;
    @BindView(R.id.label_tagline)
    TextView mLabelTagline;

    @BindString(R.string.movie_image_big_url_endppoint)
    String mImageUrlBase;
    @BindDrawable(R.drawable.ic_movie)
    Drawable mMovieIcon;

    @Inject
    Provider<MovieDetailsPresenter> mPresenterProvider;

    private int mMovieId = 0;
    private String mMovieTitle = "";
    private Boolean mIsMovieWatched = false;
    private BehaviorSubject<List<Integer>> genresIdsSubject = BehaviorSubject.createDefault(Collections.emptyList());

    private SimilarMoviesAdapter mSimilarMoviesAdapter = new SimilarMoviesAdapter();

    @Override
    public int layoutRes() {
        return R.layout.fragment_moviedetails;
    }

    @Override
    public int toolbarTitle() {
        return 0;
    }

    @Override
    public void setupToolbar(Toolbar toolbar) {
        toolbar.setTitle(mMovieTitle);
        mCollapsingToolbar.setTitle(mMovieTitle);
    }

    @NonNull
    @Override
    public MviPresenter createPresenter() {
        return mPresenterProvider.get();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Bundle arguments = getArguments();
        if (arguments != null) {
            mMovieId = arguments.getInt(MOVIE_ID, 0);
            mMovieTitle = arguments.getString(MOVIE_TITLE);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSimilarMovies.setAdapter(mSimilarMoviesAdapter);
        mSimilarMoviesAdapter.setFeedItemListener(this);
    }

    @Override
    public Observable<Integer> loadMovieDetailsIntent() {
        return Observable.just(mMovieId);
    }

    @Override
    public Observable<Integer> loadSimilarMoviesIntent() {
        return Observable.just(mMovieId);
    }

    @Override
    public Observable<List<Integer>> loadGenresForMovieIntent() {
        return genresIdsSubject;
    }

    @Override
    public Observable<Pair<Integer, Boolean>> markAsWatchedIntent() {
        return RxView.clicks(mMovieFab).debounce(300, TimeUnit.MILLISECONDS).map(ignored -> new Pair<>(mMovieId, !mIsMovieWatched));
    }

    /**
     * Toggles the movie {@link FloatingActionButton} properties, i.e. background color and icon.
     */
    private void toggleFab(FloatingActionButton movieFab, Boolean isMovieWatched) {
        movieFab.setBackgroundTintList(isMovieWatched ? ContextCompat.getColorStateList(getContext(), R.color.green) : ContextCompat.getColorStateList(getContext(), R.color.blue));
        movieFab.setImageDrawable(isMovieWatched ? ContextCompat.getDrawable(getContext(), R.drawable.ic_check) : ContextCompat.getDrawable(getContext(), R.drawable.ic_seen));
    }

    @Override
    public void render(MovieDetailsViewState viewState) {

        final Movie movie = viewState.movie();
        if (movie != null) {
            showLabels(true);

            mMovieYear.setText(Integer.toString(movie.releaseDate().getYear()));
            mMovieRuntime.setText(getString(R.string.details_runtime, movie.runtime()));
            mMovieGenres.setText(viewState.genreNames().toString().replaceAll("[\\[.\\].\\s+]", " "));
            mMovieRating.setText(String.valueOf(movie.voteAverage()));
            mMovieOverview.setText(movie.overview());
            mMovieTagline.setText(movie.tagline());

            GlideApp
                .with(getContext())
                .load(mImageUrlBase.concat(movie.posterPath()))
                .placeholder(mMovieIcon)
                .centerCrop()
                .into(mMoviePoster);

            genresIdsSubject.onNext(movie.genreIds());

            if (movie.watched() != null) {
                mIsMovieWatched = movie.watched();
                toggleFab(mMovieFab, movie.watched());
            }
        }

        final List<Movie> similarMovies = viewState.similarMovies();

        if (similarMovies != null) {
            mSimilarMoviesAdapter.setSimilarMovies(similarMovies);
        }

        mProgressBar.setVisibility(viewState.isLoading() ? View.VISIBLE : View.GONE);

        final Throwable error = viewState.getError();

        if (error != null) {
            Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Controls the visibility of the labels and the FAB.
     *
     * @param show True to show the views, false to hide them.
     */
    private void showLabels(boolean show) {
        mMovieFab.setVisibility(show ? View.VISIBLE : View.GONE);
        mMovieRating.setVisibility(show ? View.VISIBLE : View.GONE);
        mLabelOverview.setVisibility(show ? View.VISIBLE : View.GONE);
        mLabelSimilar.setVisibility(show ? View.VISIBLE : View.GONE);
        mLabelTagline.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    /**
     * Creates a new instance of the [MovieDetailsFragment] given a movie id.
     *
     * @param movieId Id of the {@link com.example.themoviedb.data.model.Movie} to create the fragment of.
     * @return Instance of MovieDetailsFragment for the given movie.
     */
    public static MovieDetailsFragment newInstance(int movieId, String movieTitle) {
        final Bundle args = new Bundle();
        args.putInt(MOVIE_ID, movieId);
        args.putString(MOVIE_TITLE, movieTitle);

        final MovieDetailsFragment fragment = new MovieDetailsFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onMovieItemClick(Movie movie) {
        final FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out, R.anim.abc_popup_enter, R.anim.abc_popup_exit);
        fragmentTransaction.replace(R.id.content, MovieDetailsFragment.newInstance(movie.id(), movie.title()));
        fragmentTransaction.addToBackStack(mMovieTitle);
        fragmentTransaction.commit();
    }
}

package com.example.themoviedb.moviedetails;

import com.example.themoviedb.data.domain.GenresDataRepo;
import com.example.themoviedb.data.domain.MoviesDataRepo;
import com.example.themoviedb.data.domain.MoviesRepo;
import com.example.themoviedb.data.model.PartialStateChanges;
import com.hannesdorfmann.mosby3.mvi.MviBasePresenter;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Presenter which binds the intents defined on the {@link MovieDetailsView}, interacting with a {@link MoviesRepo}.
 */
public class MovieDetailsPresenter extends MviBasePresenter<MovieDetailsView, MovieDetailsViewState> {

    private final MoviesRepo moviesRepo;
    private final MoviesDataRepo moviesDataRepo;
    private final GenresDataRepo genresDataRepo;

    @Inject
    public MovieDetailsPresenter(final MoviesRepo moviesRepo, final MoviesDataRepo moviesDataRepo, final GenresDataRepo genresDataRepo) {
        this.moviesRepo = moviesRepo;
        this.moviesDataRepo = moviesDataRepo;
        this.genresDataRepo = genresDataRepo;
    }

    @Override
    protected void bindIntents() {

        Observable<PartialStateChanges> loadMovieDetails = intent(MovieDetailsView::loadMovieDetailsIntent)
            .doOnNext(ignored -> Timber.d("intent: loadMovieDetailsIntent"))
            .flatMap(movieId -> moviesRepo.getMovieDetails(movieId)
                .map(movie -> (PartialStateChanges) new PartialStateChanges.MovieDetailsLoaded(movie))
                .startWith(new PartialStateChanges.MovieDetailsLoading())
                .onErrorReturn(PartialStateChanges.MovieDetailsError::new)
                .subscribeOn(Schedulers.io()));

        Observable<PartialStateChanges> loadGenresForMovie = intent(MovieDetailsView::loadGenresForMovieIntent)
            .doOnNext(ignored -> Timber.d("intent: loadGenresForMovieIntent"))
            .flatMap(genreIds -> genresDataRepo.getGenreNames(genreIds)
                .map(genreNames -> (PartialStateChanges) new PartialStateChanges.GenreNamesLoaded(genreNames))
                .startWith(new PartialStateChanges.GenresLoading())
                .onErrorReturn(PartialStateChanges.GenresError::new)
                .subscribeOn(Schedulers.io()));

        Observable<PartialStateChanges> loadSimilarMovies = intent(MovieDetailsView::loadSimilarMoviesIntent)
            .doOnNext(ignored -> Timber.d("intent: loadSimilarMoviesIntent"))
            .flatMap(movieId -> moviesRepo.getSimilarMovies(movieId)
                .map(movies -> (PartialStateChanges) new PartialStateChanges.SimilarMoviesLoaded(movies))
                .startWith(new PartialStateChanges.SimilarMoviesLoading())
                .onErrorReturn(PartialStateChanges.SimilarMoviesError::new)
                .subscribeOn(Schedulers.io()));

        Observable<PartialStateChanges> markMovieAsWatched = intent(MovieDetailsView::markAsWatchedIntent)
            .doOnNext(ignored -> Timber.d("intent: markAsWatchedIntent"))
            .flatMap(movieSeenPair -> moviesDataRepo.markMovieAsWatched(movieSeenPair.first, movieSeenPair.second)
                .map(watched -> (PartialStateChanges) new PartialStateChanges.MarkingMovieAsWatchedDone(watched))
                .startWith(new PartialStateChanges.MarkingMovieAsWatched())
                .onErrorReturn(PartialStateChanges.MarkingMovieAsWatchedError::new)
                .subscribeOn(Schedulers.io()));

        Observable<PartialStateChanges> allIntentsObservable =
            Observable.merge(loadMovieDetails, loadSimilarMovies, markMovieAsWatched, loadGenresForMovie)
                .observeOn(AndroidSchedulers.mainThread());

        MovieDetailsViewState initialState = MovieDetailsViewState.builder().loadingMovieDetails(true).build();

        subscribeViewState(allIntentsObservable.scan(initialState, this::viewStateReducer).distinctUntilChanged(),
            MovieDetailsView::render);
    }

    private MovieDetailsViewState viewStateReducer(final MovieDetailsViewState previousState, final PartialStateChanges partialChanges) {
        if (partialChanges instanceof PartialStateChanges.MovieDetailsLoading) {
            return previousState;
        }

        if (partialChanges instanceof PartialStateChanges.MovieDetailsError) {
            return previousState.toBuilder()
                .loadingMovieDetails(false)
                .loadingMovieDetailsError(((PartialStateChanges.MovieDetailsError) partialChanges).getError())
                .build();
        }

        if (partialChanges instanceof PartialStateChanges.MovieDetailsLoaded) {
            return previousState.toBuilder()
                .loadingMovieDetails(false)
                .movie(((PartialStateChanges.MovieDetailsLoaded) partialChanges).getData())
                .loadingMovieDetailsError(null)
                .build();
        }

        if (partialChanges instanceof PartialStateChanges.SimilarMoviesLoading) {
            return previousState.toBuilder()
                .loadingSimilarMovies(true)
                .loadingSimilarMoviesError(null)
                .similarMovies(null)
                .build();
        }

        if (partialChanges instanceof PartialStateChanges.SimilarMoviesError) {
            return previousState.toBuilder()
                .loadingSimilarMovies(false)
                .similarMovies(null)
                .loadingSimilarMoviesError(((PartialStateChanges.SimilarMoviesError) partialChanges).getError())
                .build();
        }

        if (partialChanges instanceof PartialStateChanges.SimilarMoviesLoaded) {
            return previousState.toBuilder()
                .loadingSimilarMovies(false)
                .similarMovies(((PartialStateChanges.SimilarMoviesLoaded) partialChanges).getData())
                .loadingSimilarMoviesError(null)
                .build();
        }

        if (partialChanges instanceof PartialStateChanges.MarkingMovieAsWatched) {
            return previousState.toBuilder().markingMovieAsWatched(true).build();
        }

        if (partialChanges instanceof PartialStateChanges.MarkingMovieAsWatchedError) {
            return previousState.toBuilder()
                .markingMovieAsWatched(false)
                .markingMovieAsWatchedError(((PartialStateChanges.MarkingMovieAsWatchedError) partialChanges).getError())
                .build();
        }

        if (partialChanges instanceof PartialStateChanges.MarkingMovieAsWatchedDone) {
            return previousState.toBuilder()
                .movie(previousState.movie().toBuilder().watched(((PartialStateChanges.MarkingMovieAsWatchedDone) partialChanges).getData()).build())
                .markingMovieAsWatched(false)
                .markingMovieAsWatchedError(null)
                .build();
        }

        if (partialChanges instanceof PartialStateChanges.GenresLoading) {
            return previousState;
        }

        if (partialChanges instanceof PartialStateChanges.GenresError) {
            return previousState.toBuilder().loadingMovieDetailsError(((PartialStateChanges.GenresError) partialChanges).getError()).build();
        }

        if (partialChanges instanceof PartialStateChanges.GenreNamesLoaded) {
            return previousState.toBuilder().genreNames(((PartialStateChanges.GenreNamesLoaded) partialChanges).getData()).build();
        }

        throw new IllegalStateException("Don't know how to reduce the partial state " + partialChanges);
    }

}


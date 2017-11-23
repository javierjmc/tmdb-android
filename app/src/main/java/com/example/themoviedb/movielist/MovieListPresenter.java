package com.example.themoviedb.movielist;

import android.util.Pair;

import com.example.themoviedb.data.domain.GenresRepo;
import com.example.themoviedb.data.domain.MoviesRepo;
import com.example.themoviedb.data.model.FeedItem;
import com.example.themoviedb.data.model.Movie;
import com.example.themoviedb.data.model.PartialStateChanges;
import com.hannesdorfmann.mosby3.mvi.MviBasePresenter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Presenter which binds the intents defined on the {@link MovieListView}, interacting with a {@link MoviesRepo}.
 */
public class MovieListPresenter extends MviBasePresenter<MovieListView, MovieListViewState> {

    private final MoviesRepo moviesRepo;
    private final GenresRepo genresRepo;

    private LinkedHashMap<Integer, FeedItem> movies = new LinkedHashMap<>();

    @Inject
    public MovieListPresenter(final MoviesRepo moviesRepo, final GenresRepo genresRepo) {
        this.moviesRepo = moviesRepo;
        this.genresRepo = genresRepo;
    }

    @Override
    protected void bindIntents() {

        Observable<PartialStateChanges> loadGenres = intent(MovieListView::loadGenresIntent)
            .doOnNext(ignored -> Timber.d("intent: loadGenresIntent"))
            .flatMap(ignored -> genresRepo.getGenres()
                .map(genres -> (PartialStateChanges) new PartialStateChanges.GenresLoaded(genres))
                .startWith(new PartialStateChanges.GenresLoading())
                .onErrorReturn(PartialStateChanges.GenresError::new)
                .subscribeOn(Schedulers.io()));

        Observable<PartialStateChanges> loadMoviesFirstPage = intent(MovieListView::loadMoviesFirstPageIntent)
            .doOnNext(ignored -> Timber.d("intent: loadMoviesFirstPageIntent"))
            .flatMap(ignored -> moviesRepo.getMostPopularMovies(1)
                .map(items -> (PartialStateChanges) new PartialStateChanges.FirstPageLoaded(items))
                .startWith(new PartialStateChanges.FirstPageLoading())
                .onErrorReturn(error -> new PartialStateChanges.FirstPageError(new Pair<Integer, Throwable>(1, error)))
                .subscribeOn(Schedulers.io()));

        Observable<PartialStateChanges> loadMoviesNextPage = intent(MovieListView::loadMoviesNextPageIntent)
            .doOnNext(ignored -> Timber.d("intent: loadMoviesNextPageIntent"))
            .flatMap(nextPage -> moviesRepo.getMostPopularMovies(nextPage)
                .map(items -> (PartialStateChanges) new PartialStateChanges.NextPageLoaded(items))
                .startWith(new PartialStateChanges.NextPageLoading())
                .onErrorReturn(error -> new PartialStateChanges.NextPageError(new Pair<Integer, Throwable>(nextPage, error)))
                .subscribeOn(Schedulers.io()));

        Observable<PartialStateChanges> allIntentsObservable =
            Observable.merge(loadGenres, loadMoviesFirstPage, loadMoviesNextPage)
                .observeOn(AndroidSchedulers.mainThread());

        MovieListViewState initialState = MovieListViewState.builder().page(1).loadingFirstPage(true).loadingNextPage(false).build();

        subscribeViewState(allIntentsObservable.scan(initialState, this::viewStateReducer).distinctUntilChanged(),
            MovieListView::render);
    }

    /**
     * Reduces different states into one, which is rendered by the view.
     * */
    private MovieListViewState viewStateReducer(final MovieListViewState previousState, final PartialStateChanges partialChanges) {

        if (partialChanges instanceof PartialStateChanges.GenresLoading) {
            return MovieListViewState.builder()
                .page(previousState.page())
                .loadingGenres(true)
                .loadingGenresError(null)
                .loadingNextPage(previousState.loadingNextPage())
                .build();
        }

        if (partialChanges instanceof PartialStateChanges.GenresError) {
            return MovieListViewState.builder()
                .page(previousState.page())
                .loadingNextPage(previousState.loadingNextPage())
                .loadingGenres(false)
                .loadingGenresError(((PartialStateChanges.GenresError) partialChanges).getError())
                .build();
        }

        if (partialChanges instanceof PartialStateChanges.GenresLoaded) {
            return MovieListViewState.builder()
                .page(previousState.page())
                .loadingNextPage(previousState.loadingNextPage())
                .loadingGenres(false)
                .loadingGenresError(null)
                .genres(((PartialStateChanges.GenresLoaded) partialChanges).getData())
                .build();
        }

        if (partialChanges instanceof PartialStateChanges.FirstPageLoading) {
            return MovieListViewState.builder().page(1).loadingFirstPage(true).loadingNextPage(false).build();
        }

        if (partialChanges instanceof PartialStateChanges.FirstPageError) {
            return previousState.toBuilder()
                .loadingFirstPage(false)
                .firstPageError(((PartialStateChanges.FirstPageError) partialChanges).getError())
                .build();
        }

        if (partialChanges instanceof PartialStateChanges.FirstPageLoaded) {
            return previousState.toBuilder()
                .loadingFirstPage(false)
                .loadingNextPage(false)
                .firstPageError(null)
                .data(((PartialStateChanges.FirstPageLoaded) partialChanges).getData())
                .page(1)
                .build();
        }

        if (partialChanges instanceof PartialStateChanges.NextPageLoading) {
            return previousState.toBuilder().loadingNextPage(true).nextPageError(null).build();
        }

        if (partialChanges instanceof PartialStateChanges.NextPageError) {
            return previousState.toBuilder()
                .loadingNextPage(false)
                .nextPageError(((PartialStateChanges.NextPageError) partialChanges).getError())
                .build();
        }

        if (partialChanges instanceof PartialStateChanges.NextPageLoaded) {
            for (FeedItem feedItem : previousState.data()) {
                movies.put(((Movie) feedItem).id(), feedItem);
            }

            for (FeedItem feedItem : ((PartialStateChanges.NextPageLoaded) partialChanges).getData()) {
                movies.put(((Movie) feedItem).id(), feedItem);
            }

            final List<FeedItem> data = new ArrayList<>(movies.values());

            return previousState.toBuilder().page(((PartialStateChanges.NextPageLoaded) partialChanges).getPage()).loadingNextPage(false).nextPageError(null).data(data).build();
        }

        throw new IllegalStateException("Don't know how to reduce the partial state " + partialChanges);
    }

}


package com.example.themoviedb.movielist;

import android.util.Pair;

import com.example.themoviedb.data.domain.MoviesRepo;
import com.example.themoviedb.data.model.FeedItem;
import com.example.themoviedb.data.model.PartialStateChanges;
import com.hannesdorfmann.mosby3.mvi.MviBasePresenter;

import java.util.ArrayList;
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

    @Inject
    public MovieListPresenter(final MoviesRepo moviesRepo) {
        this.moviesRepo = moviesRepo;
    }

    @Override
    protected void bindIntents() {

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
            Observable.merge(loadMoviesFirstPage, loadMoviesNextPage)
                .observeOn(AndroidSchedulers.mainThread());

        MovieListViewState initialState = MovieListViewState.builder().page(1).loadingFirstPage(true).loadingNextPage(false).build();

        subscribeViewState(allIntentsObservable.scan(initialState, this::viewStateReducer).distinctUntilChanged(),
            MovieListView::render);
    }

    private MovieListViewState viewStateReducer(final MovieListViewState previousState, final PartialStateChanges partialChanges) {

        if (partialChanges instanceof PartialStateChanges.FirstPageLoading) {
            return previousState.toBuilder().loadingFirstPage(true).loadingNextPage(false).page(1).firstPageError(null).build();
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
            final List<FeedItem> data = new ArrayList<>(previousState.data().size() + ((PartialStateChanges.NextPageLoaded) partialChanges).getData().size());
            data.addAll(previousState.data());
            data.addAll(((PartialStateChanges.NextPageLoaded) partialChanges).getData());

            return previousState.toBuilder().page(((PartialStateChanges.NextPageLoaded) partialChanges).getPage()).loadingNextPage(false).nextPageError(null).data(data).build();
        }

        throw new IllegalStateException("Don't know how to reduce the partial state " + partialChanges);
    }

}


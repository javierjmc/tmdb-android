/*
 * Copyright 2017 Hannes Dorfmann.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.themoviedb.movielist;

import android.util.Pair;

import com.example.themoviedb.moviedetails.MovieDetailsPresenter;
import com.example.themoviedb.moviedetails.MovieDetailsView;
import com.example.themoviedb.moviedetails.MovieDetailsViewState;

import org.junit.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;

/**
 * This class is responsible to drive the {@link MovieDetailsView}.
 * Internally this creates a {@link MovieDetailsView} and attaches it to the {@link MovieDetailsPresenter}
 * and offers public API to fire view intents and to check for expected view.render() events.
 */
public class MovieDetailsViewRobot {

    private final MovieDetailsPresenter presenter;
    private final PublishSubject<Integer> loadMovieDetailsIntent = PublishSubject.create();
    private final PublishSubject<Integer> loadSimilarMoviesIntent = PublishSubject.create();
    private final PublishSubject<List<Integer>> loadGenresForMovieIntent = PublishSubject.create();
    private final PublishSubject<Pair<Integer, Boolean>> markAsWatchedIntent = PublishSubject.create();

    private final List<MovieDetailsViewState> renderEvents = new CopyOnWriteArrayList<>();
    private final ReplaySubject<MovieDetailsViewState> renderEventSubject = ReplaySubject.create();

    private MovieDetailsView view = new MovieDetailsView() {
        @Override
        public Observable<Integer> loadMovieDetailsIntent() {
            return loadMovieDetailsIntent;
        }

        @Override
        public Observable<Integer> loadSimilarMoviesIntent() {
            return loadSimilarMoviesIntent;
        }

        @Override
        public Observable<List<Integer>> loadGenresForMovieIntent() {
            return loadGenresForMovieIntent;
        }

        @Override
        public Observable<Pair<Integer, Boolean>> markAsWatchedIntent() {
            return markAsWatchedIntent;
        }

        @Override
        public void render(MovieDetailsViewState viewState) {

        }
    };

    public MovieDetailsViewRobot(MovieDetailsPresenter presenter) {
        this.presenter = presenter;
        presenter.attachView(view);
    }

    public void fireLoadMovieDetailsIntent(int movieId) {
        loadMovieDetailsIntent.onNext(movieId);
    }

    public void fireLoadGenresForMovieIntent(List<Integer> genreIds) {
        loadGenresForMovieIntent.onNext(genreIds);
    }

    public void fireLoadSimilarMovieIntent(int movieId) {
        loadSimilarMoviesIntent.onNext(movieId);
    }

    public void fireMarkAsWatchedIntent(Pair<Integer, Boolean> movieSeenPair) {
        markAsWatchedIntent.onNext(movieSeenPair);
    }

    /**
     * Blocking waits for view.render() calls and
     *
     * @param expectedMovieDetailsViewState The expected  MovieListViewStates that will be passed to
     *                                      view.render()
     */
    public void assertViewStateRendered(MovieDetailsViewState... expectedMovieDetailsViewState) {

        if (expectedMovieDetailsViewState == null) {
            throw new NullPointerException("expectedMovieDetailsViewState == null");
        }

        int eventsCount = expectedMovieDetailsViewState.length;
        renderEventSubject.take(eventsCount)
            .timeout(10, TimeUnit.SECONDS)
            .blockingSubscribe();


        // Wait for few milli seconds to ensure that no more render events have occurred
        // before finishing the test and checking expectations (asserts)
        /*try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        if (renderEventSubject.getValues().length > eventsCount) {
            Assert.fail("Expected to wait for "
                + eventsCount
                + ", but there were "
                + renderEventSubject.getValues().length
                + " Events in total, which is more than expected: "
                + arrayToString(renderEventSubject.getValues()));
        }

        Assert.assertEquals(Arrays.asList(expectedMovieDetailsViewState), renderEvents);
    }

    /**
     * Simple helper function to print the content of an array as a string
     */
    private String arrayToString(Object[] array) {
        StringBuffer buffer = new StringBuffer();
        for (Object o : array) {
            buffer.append(o.toString());
            buffer.append("\n");
        }

        return buffer.toString();
    }
}

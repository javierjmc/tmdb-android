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

import org.junit.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;

/**
 * This class is responsible to drive the {@link MovieListView}.
 * Internally this creates a {@link MovieListView} and attaches it to the {@link MovieListPresenter}
 * and offers public API to fire view intents and to check for expected view.render() events.
 */
public class MovieListViewRobot {

    private final MovieListPresenter presenter;
    private final PublishSubject<Boolean> loadMoviesFirstPageSubject = PublishSubject.create();
    private final PublishSubject<Integer> loadNextPageSubject = PublishSubject.create();
    private final PublishSubject<Boolean> loadGenresSubject = PublishSubject.create();
    private final PublishSubject<Boolean> pullToRefreshSubject = PublishSubject.create();

    private final List<MovieListViewState> renderEvents = new CopyOnWriteArrayList<>();
    private final ReplaySubject<MovieListViewState> renderEventSubject = ReplaySubject.create();

    private MovieListView view = new MovieListView() {
        @Override
        public Observable<Boolean> loadMoviesFirstPageIntent() {
            return loadMoviesFirstPageSubject;
        }

        @Override
        public Observable<Integer> loadMoviesNextPageIntent() {
            return loadNextPageSubject;
        }

        @Override
        public Observable<Boolean> loadGenresIntent() {
            return loadGenresSubject;
        }

        @Override
        public void render(MovieListViewState viewState) {
            renderEvents.add(viewState);
            renderEventSubject.onNext(viewState);
        }
    };

    public MovieListViewRobot(MovieListPresenter presenter) {
        this.presenter = presenter;
        presenter.attachView(view);
    }

    public void fireLoadMoviesFirstPageIntent() {
        loadMoviesFirstPageSubject.onNext(true);
    }

    public void fireLoadGenresIntent() {
        loadGenresSubject.onNext(true);
    }

    /**
     * Blocking waits for view.render() calls and
     *
     * @param expectedMovieListViewState The expected  MovieListViewStates that will be passed to
     *                                   view.render()
     */
    public void assertViewStateRendered(MovieListViewState... expectedMovieListViewState) {

        if (expectedMovieListViewState == null) {
            throw new NullPointerException("expectedMovieListViewState == null");
        }

        int eventsCount = expectedMovieListViewState.length;
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

        Assert.assertEquals(Arrays.asList(expectedMovieListViewState), renderEvents);
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

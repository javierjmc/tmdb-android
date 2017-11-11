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

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;

/**
 * This class is responsible to drive the MovieListView.
 * Internally this creates a {@link MovieListView} and attaches it to the {@link MovieListPresenter}
 * and offers public API to fire view intents and to check for expected view.render() events.
 */
public class MovieListViewRobot {

    private final MovieListPresenter presenter;
    private final PublishSubject<Boolean> loadMoviesFirstPageSubject = PublishSubject.create();
    private final PublishSubject<Long> loadDetailsSubject = PublishSubject.create();
    private final PublishSubject<Boolean> loadNextPageSubject = PublishSubject.create();
    private final PublishSubject<Boolean> pullToRefreshSubject = PublishSubject.create();
    private final List<MovieListViewState> renderEvents = new CopyOnWriteArrayList<>();
    private final ReplaySubject<MovieListViewState> renderEventSubject = ReplaySubject.create();

    private MovieListView view = new MovieListView() {
        @Override
        public Observable<Boolean> loadMoviesFirstPageIntent() {
            return loadMoviesFirstPageSubject;
        }

        @Override
        public Observable<Long> loadMovieDetailsIntent() {
            return loadDetailsSubject;
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


}

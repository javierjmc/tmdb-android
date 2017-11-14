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

import com.example.themoviedb.data.domain.MoviesRepo;
import com.example.themoviedb.data.model.FeedItem;
import com.example.themoviedb.movielist.utils.MockObjects;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;

/**
 * Unit tests for the MovieListPresenter.
 */
public class MovieListPresenterTest {

    private final List<FeedItem> mockMovies = MockObjects.getMockMovies();

    private class DummyMoviesRepo implements MoviesRepo {

        @Override
        public Observable<List<FeedItem>> getMostPopularMovies(int page) {
            return Observable.just(mockMovies);
        }
    }

    @BeforeClass
    public static void init() throws Exception {
        // Tell RxAndroid to not use android main ui thread scheduler
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> Schedulers.trampoline());
    }

    @AfterClass
    public static void tearDown() throws Exception {
        RxAndroidPlugins.reset();
    }

    @Test
    public void loadingMoviesFirstPage() {
        final MovieListPresenter presenter = new MovieListPresenter(new DummyMoviesRepo());
        final MovieListViewRobot robot = new MovieListViewRobot(presenter);

        robot.fireLoadMoviesFirstPageIntent();

        final List<FeedItem> expectedData = new ArrayList<>(mockMovies.size());
        Collections.copy(expectedData, mockMovies);

        final MovieListViewState loadingMoviesFirstPage = MovieListViewState.builder().loadingFirstPage(true).build();
        final MovieListViewState successMoviesFirstPage = MovieListViewState.builder().loadingFirstPage(false).data(expectedData).build();

        robot.assertViewStateRendered(loadingMoviesFirstPage, successMoviesFirstPage);
    }
}

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

import com.example.themoviedb.data.model.FeedItem;
import com.example.themoviedb.data.model.Genre;
import com.example.themoviedb.utils.MockObjects;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.io.IOException;
import java.util.List;

import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;

/**
 * JUnit tests for the {@link MovieListPresenter}.
 */
@RunWith(RobolectricTestRunner.class)
public class MovieListPresenterTest {

    private final List<FeedItem> mockMovies = (List<FeedItem>) MockObjects.getMockMovies();
    private final List<Genre> mockGenres = MockObjects.getMockGenres();
    private MovieListPresenter presenter;

    @BeforeClass
    public static void init() throws Exception {
        // Tell RxAndroid to not use android main ui thread scheduler
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> Schedulers.trampoline());
    }

    @AfterClass
    public static void tearDown() throws Exception {
        RxAndroidPlugins.reset();
    }

    @Before
    public void setUp() throws Exception {
        presenter = new MovieListPresenter(new MockObjects.DummyMoviesRepo(), new MockObjects.DummyGenresRepo());
    }

    @Test
    public void loadingMoviesFirstPageSuccessTest() {
        final MovieListViewRobot robot = new MovieListViewRobot(presenter);

        robot.fireLoadMoviesFirstPageIntent();

        final MovieListViewState loadingMoviesFirstPage = MovieListViewState.builder().loadingFirstPage(true).loadingNextPage(false).page(1).firstPageError(null).build();
        final MovieListViewState successMoviesFirstPage = MovieListViewState.builder().loadingFirstPage(false).page(1).loadingNextPage(false).firstPageError(null).data(mockMovies).build();

        robot.assertViewStateRendered(loadingMoviesFirstPage, successMoviesFirstPage);
    }

    @Test
    public void loadingGenresSuccessTest() throws IOException {
        final MovieListViewRobot robot = new MovieListViewRobot(presenter);

        robot.fireLoadGenresIntent();

        final MovieListViewState loadingMoviesFirstPage = MovieListViewState.builder().loadingFirstPage(true).loadingNextPage(false).page(1).firstPageError(null).build();
        final MovieListViewState loadingGenres = MovieListViewState.builder().loadingGenres(true).loadingGenresError(null).page(1).loadingNextPage(false).build();
        final MovieListViewState successGenres = MovieListViewState.builder().loadingGenres(false).loadingGenresError(null).genres(mockGenres).page(1).loadingNextPage(false).build();

        robot.assertViewStateRendered(loadingMoviesFirstPage, loadingGenres, successGenres);
    }
}

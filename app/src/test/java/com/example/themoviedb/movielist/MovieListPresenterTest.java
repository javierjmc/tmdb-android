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

import com.example.themoviedb.BuildConfig;
import com.example.themoviedb.data.model.FeedItem;
import com.example.themoviedb.data.model.Genre;
import com.example.themoviedb.data.model.Movie;
import com.example.themoviedb.movielist.utils.MockObjects;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

/**
 * Unit tests for the MovieListPresenter.
 */
public class MovieListPresenterTest {

    private final Moshi moshi = new Moshi.Builder().build();
    private final Type type = Types.newParameterizedType(List.class, Movie.class, Genre.class);
    private final JsonAdapter<List<FeedItem>> adapter = moshi.adapter(type);

    private MockWebServer mockWebServer;

    @BeforeClass
    public static void init() throws Exception {
        // Tell RxAndroid to not use android main ui thread scheduler
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> Schedulers.trampoline());
    }

    @Before
    public void beforeEachTest() throws Exception {
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        // Set the apps url to the local mock server
        BuildConfig.SERVER_URL = mockWebServer.url("").toString();
    }

    @After
    public void afterEachTest() throws Exception {
        mockWebServer.shutdown();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        RxAndroidPlugins.reset();
    }

    @Test
    public void loadingMoviesFirstPage() {
        //
        // Prepare mock server to deliver mock response on incoming http request
        //
        final List<FeedItem> mockMovies = MockObjects.getMockMovies();

        mockWebServer.enqueue(new MockResponse().setBody(adapter.toJson(mockMovies)));

        //
        // init the robot to drive to View which triggers intents on the presenter
        //
        final MovieListPresenter presenter = new DependencyInjection().newHomePresenter();   // In a real app you could use dagger or instantiate the Presenter manually like new HomePresenter(...)
        HomeViewRobot robot = new HomeViewRobot(presenter);

        //
        // We are ready, so let's start: fire an intent
        //
        robot.fireLoadFirstPageIntent();

        //
        // we expect that 2 view.render() events happened with the following HomeViewState:
        // 1. show loading indicator
        // 2. show the items with the first page
        //
        List<FeedItem> expectedData = Arrays.asList(
            new SectionHeader("category1"),
            mockProducts.get(0),
            mockProducts.get(1),
            mockProducts.get(2),
            new AdditionalItemsLoadable(2, "category1", false, null)
        );

        HomeViewState loadingFirstPage = new HomeViewState.Builder().firstPageLoading(true).build();
        HomeViewState firstPage = new HomeViewState.Builder().data(expectedData).build();

        // Check if as expected
        robot.assertViewStateRendered(loadingFirstPage, firstPage);
    }

}

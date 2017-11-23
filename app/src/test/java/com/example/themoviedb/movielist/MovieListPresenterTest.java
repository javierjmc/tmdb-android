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

import com.example.themoviedb.data.db.daos.GenreDao;
import com.example.themoviedb.data.db.daos.MovieDao;
import com.example.themoviedb.data.domain.ApiMovieListResponseSchema;
import com.example.themoviedb.data.domain.ApiSchedulers;
import com.example.themoviedb.data.domain.GenresRepo;
import com.example.themoviedb.data.domain.MoviesRepo;
import com.example.themoviedb.data.domain.TheMovieDbApi;
import com.example.themoviedb.data.domain.moshi.JodaTimeMoshiAdapter;
import com.example.themoviedb.data.model.FeedItem;
import com.example.themoviedb.data.model.Genre;
import com.example.themoviedb.data.model.Movie;
import com.example.themoviedb.data.model.PartialStateChanges;
import com.example.themoviedb.utils.MockObjects;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;
import okhttp3.mockwebserver.MockWebServer;

/**
 * Unit tests for the {@link MovieListPresenter}.
 */
public class MovieListPresenterTest {

    /**************************************************/

    // Json serializer for mock server
    private Moshi moshi = new Moshi.Builder()
        .add(new JodaTimeMoshiAdapter())
//        .add(MoshiAdapterFactory.create())
        .build();

    private Type type = Types.newParameterizedType(ApiMovieListResponseSchema.class, List.class, Movie.class, FeedItem.class, ApiMovieListResponseSchema.class, Genre.class);
    private JsonAdapter<ApiMovieListResponseSchema<List<FeedItem>>> adapter = moshi.adapter(type);

    private MockWebServer mockWebServer;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    TheMovieDbApi theMovieDbApi;
    @Mock
    ApiSchedulers apiSchedulers;
    @Mock
    MovieDao movieDao;
    @Mock
    GenreDao genreDao;

    @Before
    public void beforeEachTest() throws Exception {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        // Set the apps url to the local mock server
//        DependencyInjection.BASE_URL = mockWebServer.url("").toString();
    }

    @After
    public void afterEachTest() throws Exception {
        mockWebServer.shutdown();
    }

    /**************************************************/


    private final List<FeedItem> mockMovies = MockObjects.getMockMovies();
    private final List<Genre> mockGenres = MockObjects.getMockGenres();
    private final ApiMovieListResponseSchema<List<FeedItem>> mockApiResponseForMovies = MockObjects.getMockApiResponseForMovies();
    private MovieListPresenter presenter;

    private class DummyMoviesRepo implements MoviesRepo {
        @Override
        public Observable<Pair<Integer, List<FeedItem>>> getMostPopularMovies(int page) {
            return Observable.just(new Pair<>(1, mockMovies));
        }

        @Override
        public Observable<Movie> getMovieDetails(int movieId) {
            return null;
        }

        @Override
        public Observable<List<Movie>> getSimilarMovies(int movieId) {
            return null;
        }

    }

    private class DummyGenresRepo implements GenresRepo {
        @Override
        public Observable<List<Genre>> getGenres() {
            return Observable.just(mockGenres);
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

    @Before
    public void setUp() throws Exception {
        presenter = new MovieListPresenter(new DummyMoviesRepo(), new DummyGenresRepo());
    }

    @Test
    public void loadingMoviesFirstPageTest() {

        //
        // Prepare mock server to deliver mock response on incoming http request
        //

        //final String fileName = "quote_200_popularmovies.json";
        //mockWebServer.enqueue(new MockResponse().setBody(RestServiceTestHelper.getStringFromFile(getInstrumentation().getContext(), fileName)));
        /*mockWebServer.enqueue(new MockResponse().setBody(adapter.toJson(mockApiResponseForMovies)));

        final MovieListViewRobot robot = new MovieListViewRobot(presenter);

        robot.fireLoadMoviesFirstPageIntent();

        final List<FeedItem> expectedData = new ArrayList<>(Collections.nCopies(mockMovies.size(), null));
        Collections.copy(expectedData, mockMovies);

        final MovieListViewState loadingMoviesFirstPage = MovieListViewState.builder().loadingFirstPage(true).build();
        final MovieListViewState successMoviesFirstPage = MovieListViewState.builder().loadingFirstPage(false).data(expectedData).build();

        robot.assertViewStateRendered(loadingMoviesFirstPage, successMoviesFirstPage);*/

        final MovieListViewRobot robot = new MovieListViewRobot(presenter);

        robot.fireLoadMoviesFirstPageIntent();

        final List<FeedItem> expectedData = new ArrayList<>(Collections.nCopies(mockMovies.size(), null));
        Collections.copy(expectedData, mockMovies);

        final MovieListViewState loadingMoviesFirstPage = MovieListViewState.builder().loadingFirstPage(true).page(1).firstPageError(null).build();
        final MovieListViewState successMoviesFirstPage = MovieListViewState.builder().loadingFirstPage(false).loadingNextPage(false).firstPageError(null).data(expectedData).build();

        robot.assertViewStateRendered(loadingMoviesFirstPage, successMoviesFirstPage);
    }

    @Test
    public void loadingMoviesFirstPageNoConnectionErrorTest() throws IOException {

        mockWebServer.shutdown(); // Simulate no internet connection to the server

        final MovieListViewRobot robot = new MovieListViewRobot(presenter);

        robot.fireLoadMoviesFirstPageIntent();

        final MovieListViewState loadingMoviesFirstPage = MovieListViewState.builder().loadingFirstPage(true).build();
        final MovieListViewState errorLoadingMoviesFirstPage = MovieListViewState.builder().loadingFirstPage(false).firstPageError(new ConnectException()).build();

        robot.assertViewStateRendered(loadingMoviesFirstPage, errorLoadingMoviesFirstPage);
    }

    @Test
    public void loadingGenresTest() throws IOException {

        final MovieListViewRobot robot = new MovieListViewRobot(presenter);

        robot.fireLoadGenresIntent();

        final List<Genre> expectedData = new ArrayList<>(Collections.nCopies(mockGenres.size(), null));
        Collections.copy(expectedData, mockGenres);

        final MovieListViewState loadingMoviesFirstPage = MovieListViewState.builder().loadingFirstPage(true).loadingNextPage(false).page(1).firstPageError(null).build();
        final MovieListViewState loadingGenres = MovieListViewState.builder().loadingGenres(true).loadingGenresError(null).page(1).loadingNextPage(false).build();
        final MovieListViewState successGenres = MovieListViewState.builder().loadingGenres(false).loadingGenresError(null).genres(expectedData).page(1).loadingNextPage(false).build();

        robot.assertViewStateRendered(loadingMoviesFirstPage, loadingGenres, successGenres);
    }
}

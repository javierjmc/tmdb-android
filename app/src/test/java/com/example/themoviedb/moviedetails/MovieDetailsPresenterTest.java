package com.example.themoviedb.moviedetails;

import com.example.themoviedb.data.model.Movie;
import com.example.themoviedb.movielist.MovieDetailsViewRobot;
import com.example.themoviedb.utils.MockObjects;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;

/**
 * JUnit tests for the {@link MovieDetailsPresenter}.
 */
public class MovieDetailsPresenterTest {

    private final Movie mockMovie = (Movie) MockObjects.getMockMovies().get(0);
    private MovieDetailsPresenter presenter;


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
        presenter = new MovieDetailsPresenter(new MockObjects.DummyMoviesRepo(), new MockObjects.DummyMoviesDataRepo(), new MockObjects.DummyGenresDataRepo());
    }

    @Test
    public void showMovieDetailsSuccessTest() throws IOException {
        final MovieDetailsViewRobot robot = new MovieDetailsViewRobot(presenter);

        robot.fireLoadMovieDetailsIntent(mockMovie.id());

        final MovieDetailsViewState loadingMovieDetails = MovieDetailsViewState.builder().loadingMovieDetails(true).build();
        final MovieDetailsViewState successMovieDetails = MovieDetailsViewState.builder().loadingMovieDetails(false).movie(mockMovie).loadingMovieDetailsError(null).build();

        robot.assertViewStateRendered(loadingMovieDetails, successMovieDetails);
    }

}

package com.example.themoviedb.data.db;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.themoviedb.data.db.daos.MovieDao;
import com.example.themoviedb.data.model.Movie;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class MovieDaoTest {
    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    private TheMovieDbAppDatabase database;
    private MovieDao movieDao;

    @Before
    public void initDb() throws Exception {
        database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), TheMovieDbAppDatabase.class).allowMainThreadQueries().build();
        movieDao = database.movieDao();
    }

    @After
    public void closeDb() throws Exception {
        database.close();
    }

    @Test
    public void createDatabaseTest() {
        assert (database != null);
    }

    @Test
    public void createMovieDaoTest() {
        assert (movieDao != null);
    }

    @Test
    public void insertMovieTest() throws InterruptedException {
        final Movie movie = Movie.builder()
            .id(0)
            .voteAverage(7.5f)
            .voteCount(10)
            .title("Movie 0")
            .posterPath(null)
            .originalLanguage(null)
            .originalTitle(null)
            .genreIds(new ArrayList<Integer>() {{
                add(1);
                add(2);
            }})
            .adult(false)
            .overview("Movie0's overview")
            .releaseDate(DateTime.now())
            .build();

        movieDao.insert(movie);

        final List<Movie> movies = movieDao.getMovies().blockingGet();
        assert (movies.size() == 1);

        final Movie insertedMovie = movies.get(0);
        assert (insertedMovie).equals(movie);
    }

    @Test
    public void insertMoviesTest() throws InterruptedException {
        final List<Movie> movies = Arrays.asList(
            Movie.builder()
                .id(0)
                .voteAverage(7.5f)
                .voteCount(10)
                .title("Movie 0")
                .posterPath(null)
                .originalLanguage(null)
                .originalTitle(null)
                .genreIds(new ArrayList<Integer>() {{
                    add(1);
                    add(2);
                }})
                .adult(false)
                .overview("Movie0's overview")
                .releaseDate(DateTime.now())
                .build(),

            Movie.builder()
                .id(1)
                .voteAverage(7.5f)
                .voteCount(10)
                .title("Movie 1")
                .posterPath(null)
                .originalLanguage(null)
                .originalTitle(null)
                .genreIds(new ArrayList<Integer>() {{
                    add(1);
                    add(2);
                }})
                .adult(false)
                .overview("Movie1's overview")
                .releaseDate(DateTime.now())
                .build());

        movieDao.insertAll(movies);

        final List<Movie> insertedMovies = movieDao.getMovies().blockingGet();

        assert (insertedMovies.size() == 2);
        assert (movies.get(0)).equals(insertedMovies.get(0));
        assert (movies.get(1)).equals(insertedMovies.get(1));
    }

    @Test
    public void updateMovieTest() throws InterruptedException {
        Movie movie = Movie.builder()
            .id(0)
            .voteAverage(7.5f)
            .voteCount(10)
            .title("Movie 0")
            .posterPath(null)
            .originalLanguage(null)
            .originalTitle(null)
            .genreIds(new ArrayList<Integer>() {{
                add(1);
                add(2);
            }})
            .adult(false)
            .overview("Movie0's overview")
            .releaseDate(DateTime.now())
            .build();

        movieDao.insert(movie);

        movie.toBuilder().title("The best movie ever").build();

        movieDao.update(movie);

        final List<Movie> movies = movieDao.getMovies().blockingGet();
        assert (movies.size() == 1);

        final Movie insertedMovie = movies.get(0);
        assert !(insertedMovie.title()).equals("Movie 0");
        assert (insertedMovie.title()).equals("The best movie ever");
    }

    @Test
    public void deleteMovieTest() throws InterruptedException {
        final Movie movie = Movie.builder()
            .id(0)
            .voteAverage(7.5f)
            .voteCount(10)
            .title("Movie 0")
            .posterPath(null)
            .originalLanguage(null)
            .originalTitle(null)
            .genreIds(new ArrayList<Integer>() {{
                add(1);
                add(2);
            }})
            .adult(false)
            .overview("Movie0's overview")
            .releaseDate(DateTime.now())
            .build();

        movieDao.insert(movie);

        List<Movie> movies = movieDao.getMovies().blockingGet();
        assert (movies.size() == 1);

        movieDao.delete(movie);
        movies = movieDao.getMovies().blockingGet();
        assert (movies.size() == 0);
    }

    @Test
    public void deleteMoviesTest() throws InterruptedException {
        final List<Movie> movies = Arrays.asList(
            Movie.builder()
                .id(0)
                .voteAverage(7.5f)
                .voteCount(10)
                .title("Movie 0")
                .posterPath(null)
                .originalLanguage(null)
                .originalTitle(null)
                .genreIds(new ArrayList<Integer>() {{
                    add(1);
                    add(2);
                }})
                .adult(false)
                .overview("Movie0's overview")
                .releaseDate(DateTime.now())
                .build(),

            Movie.builder()
                .id(1)
                .voteAverage(7.5f)
                .voteCount(10)
                .title("Movie 1")
                .posterPath(null)
                .originalLanguage(null)
                .originalTitle(null)
                .genreIds(new ArrayList<Integer>() {{
                    add(1);
                    add(2);
                }})
                .adult(false)
                .overview("Movie1's overview")
                .releaseDate(DateTime.now())
                .build());

        movieDao.insertAll(movies);

        List<Movie> insertedMovies = movieDao.getMovies().blockingGet();
        assert (insertedMovies.size() == 2);

        movieDao.deleteAll(movies);

        final List<Movie> deletedMovies = movieDao.getMovies().blockingGet();
        assert (deletedMovies.size() == 0);
    }
}

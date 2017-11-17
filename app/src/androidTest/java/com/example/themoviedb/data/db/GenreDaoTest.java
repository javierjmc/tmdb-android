package com.example.themoviedb.data.db;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.themoviedb.data.db.daos.GenreDao;
import com.example.themoviedb.data.model.Genre;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class GenreDaoTest {
    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    private TheMovieDbAppDatabase database;
    private GenreDao genreDao;

    @Before
    public void initDb() throws Exception {
        database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), TheMovieDbAppDatabase.class).allowMainThreadQueries().build();
        genreDao = database.genreDao();
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
    public void createGenreDaoTest() {
        assert (genreDao != null);
    }

    @Test
    public void insertGenreTest() throws InterruptedException {
        final Genre genre = new Genre(0, "Horror");

        genreDao.insert(genre);

        final List<Genre> genres = genreDao.getGenres().blockingGet();
        assert (genres.size() == 1);
        assert (genres.get(0)).equals(genre);
    }

    @Test
    public void insertGenresTest() throws InterruptedException {
        final List<Genre> genres = Arrays.asList(new Genre(0, "Horror"),
            new Genre(1, "Comedy"));

        genreDao.insertAll(genres);

        final List<Genre> insertedGenres = genreDao.getGenres().blockingGet();

        assert (insertedGenres.size() == 2);
        assert (insertedGenres.get(0)).equals(genres.get(0));
        assert (insertedGenres.get(1)).equals(genres.get(1));
    }

    @Test
    public void updateGenreTest() throws InterruptedException {
        Genre genre = new Genre(0, "Horror");

        genreDao.insert(genre);

        genre.toBuilder().name("Thriller").build();

        genreDao.update(genre);

        final List<Genre> genres = genreDao.getGenres().blockingGet();
        assert (genres.size() == 1);
        assert (genres.get(0).name()).equals("Thriller");
    }

    @Test
    public void deleteGenreTest() throws InterruptedException {
        final Genre genre = new Genre(0, "Horror");

        genreDao.insert(genre);

        List<Genre> genres = genreDao.getGenres().blockingGet();
        assert (genres.size() == 1);

        genreDao.delete(genre);

        genres = genreDao.getGenres().blockingGet();
        assert (genres.size() == 0);
    }

    @Test
    public void deleteGenresTest() throws InterruptedException {
        final List<Genre> genres = Arrays.asList(new Genre(0, "Horror"),
            new Genre(1, "Comedy"));

        genreDao.insertAll(genres);

        List<Genre> insertedGenres = genreDao.getGenres().blockingGet();
        assert (insertedGenres.size() == 2);

        genreDao.deleteAll(genres);

        final List<Genre> deletedGenres = genreDao.getGenres().blockingGet();
        assert (deletedGenres.size() == 0);
    }

    @Test
    public void getGenreByIdTest() throws InterruptedException {
        final List<Genre> genres = Arrays.asList(new Genre(0, "Horror"),
            new Genre(1, "Comedy"));

        genreDao.insertAll(genres);

        final Genre firstGenre = genreDao.getGenreById(0).blockingGet();
        assert (firstGenre).equals(genres.get(0));

        final Genre secondGenre = genreDao.getGenreById(1).blockingGet();
        assert (secondGenre).equals(genres.get(1));
    }
}

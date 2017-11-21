package com.example.themoviedb.utils;

import com.example.themoviedb.data.model.Genre;
import com.example.themoviedb.data.model.Movie;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Tests for the {@link GenreUtilTest}.
 */
public class GenreUtilTest {

    private final List emptyGenres = new ArrayList<Genre>();
    private final List nullGenres = null;
    private final List genres = new ArrayList<Genre>() {{
        add(new Genre(0, "Genre 0"));
        add(new Genre(1, "Genre 1"));
        add(new Genre(2, "Genre 2"));
        add(new Genre(3, "Genre 3"));
    }};

    private final Movie movie = (Movie) MockObjects.getMockMovies().get(0);
    private final Movie nullMovie = null;

    @Test
    public void filterGenres() throws Exception {
        final List<String> genresAsString = GenreUtil.filterGenres(genres, movie);

        assert (genresAsString != null);
        assert (!genresAsString.isEmpty());
        assert (genresAsString.get(0).equals("Genre 1"));
        assert (genresAsString.get(1).equals("Genre 2"));
    }

    @Test
    public void filterNullGenres() throws Exception {
        final List<String> genresAsString = GenreUtil.filterGenres(nullGenres, movie);

        assert (genresAsString != null);
        assert (genresAsString.isEmpty());
    }

    @Test
    public void filterEmptyGenres() throws Exception {
        final List<String> genresAsString = GenreUtil.filterGenres(emptyGenres, movie);

        assert (genresAsString != null);
        assert (genresAsString.isEmpty());
    }

    @Test
    public void filterGenresNullMovie() throws Exception {
        final List<String> genresAsString = GenreUtil.filterGenres(genres, nullMovie);

        assert (genresAsString != null);
        assert (genresAsString.isEmpty());
    }

    @Test
    public void filterNullGenresNullMovie() throws Exception {
        final List<String> genresAsString = GenreUtil.filterGenres(nullGenres, nullMovie);

        assert (genresAsString != null);
        assert (genresAsString.isEmpty());
    }

    @Test
    public void filterEmptyGenresNullMovie() throws Exception {
        final List<String> genresAsString = GenreUtil.filterGenres(emptyGenres, nullMovie);

        assert (genresAsString != null);
        assert (genresAsString.isEmpty());
    }

}

package com.example.themoviedb.utils;

import com.example.themoviedb.data.model.Genre;
import com.example.themoviedb.data.model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Useful methods to handling genres.
 */
public class GenreUtil {

    /**
     * Checks whether a list of {@link Genre}s is contained in a movie and returns a list with
     * the titles of these genres.
     *
     * @param genres The list of genres to look up
     * @param movie  the movie containing a subset of genres
     * @return List of strings containing the names of the movie genres
     */
    public static List<String> filterGenres(final List<Genre> genres, final Movie movie) {
        final List<String> genresAsStrings = new ArrayList<>();
        if (genres != null && movie != null) {
            for (Genre genre : genres) {
                if (movie.genreIds().contains(genre.id())) {
                    genresAsStrings.add(genre.name());
                }
            }
        }
        return genresAsStrings;
    }
}

package com.example.themoviedb.utils;

import com.example.themoviedb.data.domain.ApiMovieListResponseSchema;
import com.example.themoviedb.data.model.FeedItem;
import com.example.themoviedb.data.model.Genre;
import com.example.themoviedb.data.model.Movie;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MockObjects {

    public static ApiMovieListResponseSchema<List<FeedItem>> getMockApiResponseForMovies() {

        final ApiMovieListResponseSchema response = new ApiMovieListResponseSchema();
        response.setPage(1);
        response.setTotalResults(1);
        response.setTotalPages(1);
        response.setResults(getMockMovies());
        return response;
    }


    public static List<FeedItem> getMockMovies() {
        return Arrays.asList(
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
                .build(),

            Movie.builder()
                .id(2)
                .voteAverage(7.5f)
                .voteCount(10)
                .title("Movie 2")
                .posterPath(null)
                .originalLanguage(null)
                .originalTitle(null)
                .genreIds(new ArrayList<Integer>() {{
                    add(1);
                    add(2);
                }})
                .adult(false)
                .overview("Movie's overview")
                .releaseDate(DateTime.now())
                .build(),

            Movie.builder()
                .id(3)
                .voteAverage(7.5f)
                .voteCount(10)
                .title("Movie 3")
                .posterPath(null)
                .originalLanguage(null)
                .originalTitle(null)
                .genreIds(new ArrayList<Integer>() {{
                    add(1);
                    add(2);
                }})
                .adult(false)
                .overview("Movie3's overview")
                .releaseDate(DateTime.now())
                .build()
        );
    }

    public static List<Genre> getMockGenres() {
        return Arrays.asList(
            new Genre(0, "Genre 0"),
            new Genre(1, "Genre 1"),
            new Genre(2, "Genre 2"));
    }
}

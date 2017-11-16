package com.example.themoviedb.utils;

import com.example.themoviedb.data.domain.ApiResponseSchema;
import com.example.themoviedb.data.model.FeedItem;
import com.example.themoviedb.data.model.Movie;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MockObjects {

    public static ApiResponseSchema<List<FeedItem>> getMockApiResponseForMovies() {

        final ApiResponseSchema response = new ApiResponseSchema();
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
                .genreIds(new ArrayList<Long>() {{
                    add(1L);
                    add(2L);
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
                .genreIds(new ArrayList<Long>() {{
                    add(1L);
                    add(2L);
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
                .genreIds(new ArrayList<Long>() {{
                    add(1L);
                    add(2L);
                }})
                .adult(false)
                .overview("Movie2's overview")
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
                .genreIds(new ArrayList<Long>() {{
                    add(1L);
                    add(2L);
                }})
                .adult(false)
                .overview("Movie3's overview")
                .releaseDate(DateTime.now())
                .build()
        );
    }

}

package com.example.themoviedb.utils;

import android.util.Pair;

import com.example.themoviedb.data.domain.ApiMovieListResponseSchema;
import com.example.themoviedb.data.domain.GenresDataRepo;
import com.example.themoviedb.data.domain.GenresRepo;
import com.example.themoviedb.data.domain.MoviesDataRepo;
import com.example.themoviedb.data.domain.MoviesRepo;
import com.example.themoviedb.data.model.FeedItem;
import com.example.themoviedb.data.model.Genre;
import com.example.themoviedb.data.model.Movie;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;

public class MockObjects {

    private static List<String> mockGenresNames;

    public static ApiMovieListResponseSchema<List<FeedItem>> getMockApiResponseForMovies() {

        final ApiMovieListResponseSchema response = new ApiMovieListResponseSchema();
        response.setPage(1);
        response.setTotalResults(1);
        response.setTotalPages(1);
        response.setResults(getMockMovies());
        return response;
    }


    public static List<? extends FeedItem> getMockMovies() {
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

    public static List<String> getMockGenresNames() {
        return Arrays.asList("Genre 0", "Genre 1", "Genre 2");
    }

    /**
     * MOCK REPOS
     */

    public static class DummyMoviesRepo implements MoviesRepo {
        @Override
        public Observable<Pair<Integer, List<FeedItem>>> getMostPopularMovies(int page) {
            return null;
        }

        @Override
        public Observable<Movie> getMovieDetails(int movieId) {
            return Observable.just((Movie) getMockMovies().get(0));
        }

        @Override
        public Observable<List<Movie>> getSimilarMovies(int movieId) {
            return Observable.just((List<Movie>) getMockMovies());
        }
    }

    public static class DummyMoviesDataRepo implements MoviesDataRepo {
        @Override
        public Observable<List<Movie>> getMostPopularMoviesLocal(int page) {
            return Observable.just((List<Movie>) getMockMovies());
        }

        @Override
        public Observable<Movie> getMovie(int movieId) {
            return Observable.just((Movie) getMockMovies().get(0));
        }

        @Override
        public void storeMoviesLocal(List<Movie> movies) {

        }

        @Override
        public void updateMovieDetailsLocal(Movie movie) {

        }

        @Override
        public Observable<Boolean> markMovieAsWatched(int movieId, boolean watched) {
            return null;
        }
    }

    public static class DummyGenresRepo implements GenresRepo {
        @Override
        public Observable<List<Genre>> getGenres() {
            return Observable.just(getMockGenres());
        }
    }

    public static class DummyGenresDataRepo implements GenresDataRepo {
        @Override
        public Observable<List<Genre>> getGenresLocal() {
            return Observable.just(getMockGenres());
        }

        @Override
        public Observable<List<String>> getGenreNames(List<Integer> genreIds) {
            return Observable.just(getMockGenresNames());
        }

        @Override
        public void storeGenresLocal(List<Genre> genres) {

        }
    }

}

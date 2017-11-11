package com.example.themoviedb.data.repository;

import android.support.annotation.Nullable;

import com.example.themoviedb.data.model.Genre;
import com.example.themoviedb.data.model.Movie;
import com.example.themoviedb.data.model.MovieDetails;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * Retrofit interface to fetch data from the themoviedb api.
 */
public interface TheMovieDbApi {

    /**
     * Discover movies by different types of data like average rating, number of votes, genreIds and certifications.
     * See: https://developers.themoviedb.org/3/discover/movie-discover
     *
     * @param sortBy     The sorting option.<br/>
     *                   Allowed Values: , popularity.asc, popularity.desc, release_date.asc, release_date.desc,
     *                   revenue.asc, revenue.desc, primary_release_date.asc, primary_release_date.desc,
     *                   original_title.asc, original_title.desc, vote_average.asc, vote_average.desc,
     *                   vote_count.asc, vote_count.desc<br/>
     *                   default: popularity.desc
     *                   <p>
     * @param page       Specify the page of results to query.<br/>
     *                   minimum: 1
     *                   maximum: 1000
     *                   default: 1
     *                   <p>
     * @param withGenres Filter and only include movies that have a rating that is greater or equal to the specified value.
     *                   minimum: 0
     */
    @GET("/3/discover/movie")
    Observable<Movie> getMovies(@Nullable @Query("sort_by") String sortBy,
                                @Nullable @Query("page") String page,
                                @Nullable @Query("with_genres") String withGenres,
                                @Nullable @Query("vote_average.gte") String minRating);

    /**
     * Get the primary information about a movie
     * See: https://developers.themoviedb.org/3/movies/get-movie-details
     *
     * @param movieId The id of the movie to get details of.
     *                <p>
     * @param sortBy  String representing the sorting option.<br/>
     *                Allowed Values: , popularity.asc, popularity.desc, release_date.asc, release_date.desc,
     *                revenue.asc, revenue.desc, primary_release_date.asc, primary_release_date.desc,
     *                original_title.asc, original_title.desc, vote_average.asc, vote_average.desc,
     *                vote_count.asc, vote_count.desc<br/>
     *                default: popularity.desc
     */
    @GET("/3/movie/{movieId}")
    Observable<MovieDetails> getMovieDetails(@Path("movieId") long movieId,
                                             @Nullable @Query("sort_by") String sortBy);

    /**
     * Get a list of similar movies. This is not the same as the "Recommendation" system you see on the website.
     * These items are assembled by looking at keywords and genreIds.
     * See: https://developers.themoviedb.org/3/movies/get-similar-movies
     *
     * @param movieId The id of the movie to get similar movies of.
     *                <p>
     * @param sortBy  String representing the sorting option.<br/>
     *                Allowed Values: , popularity.asc, popularity.desc, release_date.asc, release_date.desc,
     *                revenue.asc, revenue.desc, primary_release_date.asc, primary_release_date.desc,
     *                original_title.asc, original_title.desc, vote_average.asc, vote_average.desc,
     *                vote_count.asc, vote_count.desc<br/>
     *                default: popularity.desc
     *                <p>
     * @param page    Specify the page of results to query.<br/>
     *                minimum: 1
     *                maximum: 1000
     *                default: 1
     */
    @GET("/3/movie/{movieId}/similar")
    Observable<List<Movie>> getSimilarMovies(@Path("movieId") long movieId,
                                             @Nullable @Query("sort_by") String sortBy,
                                             @Nullable @Query("page") String page);

    /**
     * Get the list of official genreIds for movies.
     * See: https://developers.themoviedb.org/3/genres/get-movie-list
     */
    @GET("/3/genre/movie/list")
    Observable<List<Genre>> getGenres();
}

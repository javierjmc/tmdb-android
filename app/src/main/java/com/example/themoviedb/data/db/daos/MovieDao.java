package com.example.themoviedb.data.db.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.themoviedb.data.model.Movie;

import java.util.List;

import io.reactivex.Single;

/**
 * Contains methods used for accessing movie objects on the database
 */
@Dao
public interface MovieDao {

    /**
     * Gets all {@link Movie}s in the database
     */
    @Query("SELECT * FROM movies ORDER BY movies.popularity DESC")
    Single<List<Movie>> getMovies();

    /**
     * Inserts a {@link Movie} object in the database
     *
     * @param movie Movie to be inserted
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Movie movie);

    /**
     * Inserts a {@link List} of {@link Movie}s in the database
     *
     * @param movies List of movies to be inserted
     * @return number of movies inserted
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertAll(List<Movie> movies);

    /**
     * Deletes the given {@link Movie}s from the database
     *
     * @param movies List of movies to be deleted
     * @return number of movies deleted
     */
    @Delete
    int deleteAll(List<Movie> movies);

    /**
     * Deletes a given {@link Movie} from the database
     *
     * @param movie Movie to be deleted
     */
    @Delete
    void delete(Movie movie);

    /**
     * Updates the given {@link Movie} in the database
     *
     * @param movie The movie to be updated
     * @return number of movies updated
     */
    @Update(onConflict = OnConflictStrategy.REPLACE)
    int update(Movie movie);

    /**
     * Updates {@link Movie} details in the database
     *
     * @param movieId Id of the movie to be updated
     * @param tagline Tagline of the movie
     * @param runtime Runtime of the movie
     * @return number of movies matching the query
     */
    @Query("UPDATE movies SET tagline = :tagline, runtime = :runtime WHERE movie_id = :movieId")
    int updateMovieDetails(int movieId, String tagline, int runtime);
}

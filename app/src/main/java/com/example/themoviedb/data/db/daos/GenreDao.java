package com.example.themoviedb.data.db.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.themoviedb.data.model.Genre;

import java.util.List;

import io.reactivex.Single;

/**
 * Contains methods used for accessing genre objects on the database
 */
@Dao
public interface GenreDao {

    /**
     * Gets all {@link Genre}s in the database
     */
    @Query("SELECT * FROM genres")
    Single<List<Genre>> getGenres();

    /**
     * Gets a {@link Genre} given an id.
     *
     * @param id Genre id
     * @return Genre with the given id, null if nothing is found
     */
    @Query("SELECT * FROM genres WHERE genre_id = :id")
    Single<Genre> getGenreById(int id);

    /**
     * Inserts a {@link Genre} object in the database
     *
     * @param genre Genre to be inserted
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Genre genre);

    /**
     * Inserts a {@link List} of {@link Genre}s in the database
     *
     * @param genres List of genres to be inserted
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Genre> genres);

    /**
     * Deletes the given {@link Genre}s from the database
     *
     * @param genres List of genres to be deleted
     * @return number of genres deleted
     */
    @Delete
    int deleteAll(List<Genre> genres);

    /**
     * Updates the given {@link Genre}s in the database
     *
     * @param genre The genre to be updated
     * @return number of genres updated
     */
    @Update(onConflict = OnConflictStrategy.REPLACE)
    int update(Genre genre);

}

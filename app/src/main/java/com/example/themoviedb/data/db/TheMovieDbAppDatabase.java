package com.example.themoviedb.data.db;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.example.themoviedb.data.db.daos.GenreDao;
import com.example.themoviedb.data.db.daos.MovieDao;
import com.example.themoviedb.data.model.Genre;
import com.example.themoviedb.data.model.Movie;

/**
 * Defines the {@link android.arch.persistence.room.Dao}s for the database.
 */
@Database(entities = {Movie.class, Genre.class}, version = 1)
@TypeConverters(Converters.class)
public abstract class TheMovieDbAppDatabase extends RoomDatabase {

    public abstract MovieDao movieDao();

    public abstract GenreDao genreDao();
}


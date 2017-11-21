package com.example.themoviedb.dagger2.modules;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.themoviedb.dagger2.scopes.ApplicationScope;
import com.example.themoviedb.data.db.TheMovieDbAppDatabase;
import com.example.themoviedb.data.db.daos.GenreDao;
import com.example.themoviedb.data.db.daos.MovieDao;

import dagger.Module;
import dagger.Provides;

/**
 * Provides instances of database related objects, {@link TheMovieDbAppDatabase} and its {@link android.arch.persistence.room.Dao}s.
 */
@Module
public class PersistenceModule {

    @Provides
    @ApplicationScope
    public TheMovieDbAppDatabase provideDataBase(@ApplicationScope Context context) {
        return Room.databaseBuilder(context, TheMovieDbAppDatabase.class, "tmdbapp-db").fallbackToDestructiveMigration().build();
    }

    @Provides
    @ApplicationScope
    public MovieDao provideMovieDao(TheMovieDbAppDatabase database) {
        return database.movieDao();
    }

    @Provides
    @ApplicationScope
    public GenreDao provideGenreDao(TheMovieDbAppDatabase database) {
        return database.genreDao();
    }
}

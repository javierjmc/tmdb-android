package com.example.themoviedb.dagger2.modules;

import com.example.themoviedb.data.domain.MoviesDataRepo;
import com.example.themoviedb.data.domain.MoviesRepo;
import com.example.themoviedb.movielist.MoviesDataRepoImpl;
import com.example.themoviedb.movielist.MoviesRepoImpl;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class TheMovieDbRepoBindsModule {

    @Binds
    abstract MoviesRepo bindMoviesRepo(MoviesRepoImpl impl);

    @Binds
    abstract MoviesDataRepo bindMoviesDataRepo(MoviesDataRepoImpl impl);
}



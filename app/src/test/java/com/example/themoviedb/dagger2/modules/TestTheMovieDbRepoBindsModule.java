package com.example.themoviedb.dagger2.modules;

import com.example.themoviedb.data.domain.MoviesDataRepo;
import com.example.themoviedb.data.domain.MoviesRepo;
import com.example.themoviedb.movielist.MoviesDataRepoImpl;
import com.example.themoviedb.movielist.TestMoviesRepoImpl;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class TestTheMovieDbRepoBindsModule {

    @Binds
    abstract MoviesRepo bindMoviesRepo(TestMoviesRepoImpl impl);

    @Binds
    abstract MoviesDataRepo bindMoviesDataRepo(MoviesDataRepoImpl impl);
}

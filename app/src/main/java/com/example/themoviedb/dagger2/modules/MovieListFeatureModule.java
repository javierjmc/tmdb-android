package com.example.themoviedb.dagger2.modules;


import com.example.themoviedb.dagger2.scopes.FragmentScope;
import com.example.themoviedb.movielist.MovieListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MovieListFeatureModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract MovieListFragment movieListFragmentInjector();
}

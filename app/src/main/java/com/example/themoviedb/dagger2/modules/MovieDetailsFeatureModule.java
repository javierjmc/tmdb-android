package com.example.themoviedb.dagger2.modules;


import com.example.themoviedb.dagger2.scopes.FragmentScope;
import com.example.themoviedb.moviedetails.MovieDetailsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Provides Fragments and Presenters for the MovieDetails Screen.
 */
@Module
public abstract class MovieDetailsFeatureModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract MovieDetailsFragment movieDetailsFragmentInjector();
}

package com.example.themoviedb.dagger2.modules;

import dagger.Module;
import dagger.android.AndroidInjectionModule;

/**
 * Module for activity related dependencies.
 */
@Module(includes = {
    AndroidInjectionModule.class,
    MovieListFeatureModule.class,
    MovieDetailsFeatureModule.class
})
public abstract class TheMovieDbActivitiesModule {
}

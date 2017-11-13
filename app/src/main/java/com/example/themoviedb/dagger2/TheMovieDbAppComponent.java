package com.example.themoviedb.dagger2;


import com.example.themoviedb.TheMovieDbApp;
import com.example.themoviedb.dagger2.modules.ApiModule;
import com.example.themoviedb.dagger2.modules.TheMovieDbActivitiesModule;
import com.example.themoviedb.dagger2.modules.TheMovieDbAppModule;
import com.example.themoviedb.dagger2.modules.TheMovieDbRepoBindsModule;
import com.example.themoviedb.dagger2.scopes.ApplicationScope;

import dagger.Component;
import dagger.android.AndroidInjector;

@ApplicationScope
@Component(
    modules = {
        TheMovieDbAppModule.class,
        TheMovieDbActivitiesModule.class,
        ApiModule.class,
        TheMovieDbRepoBindsModule.class
    })
public interface TheMovieDbAppComponent extends AndroidInjector<TheMovieDbApp> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<TheMovieDbApp> {

        @Override
        public abstract TheMovieDbAppComponent build();
    }
}

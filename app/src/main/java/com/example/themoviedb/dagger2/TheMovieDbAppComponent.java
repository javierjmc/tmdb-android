package com.example.themoviedb.dagger2;


import com.example.themoviedb.TheMovieDbApp;
import com.example.themoviedb.dagger2.modules.TheMovieDbAppModule;
import com.example.themoviedb.dagger2.scopes.ApplicationScope;

import dagger.Component;
import dagger.android.AndroidInjector;

@ApplicationScope
@Component(
    modules = {
        TheMovieDbAppModule.class
    })
public interface TheMovieDbAppComponent extends AndroidInjector<TheMovieDbApp> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<TheMovieDbApp> {

        @Override
        public abstract TheMovieDbAppComponent build();
    }
}

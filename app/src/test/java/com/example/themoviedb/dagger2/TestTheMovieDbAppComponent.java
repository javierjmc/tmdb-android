package com.example.themoviedb.dagger2;

import com.example.themoviedb.TestTheMovieDbApp;
import com.example.themoviedb.dagger2.modules.TestApiModule;
import com.example.themoviedb.dagger2.modules.TestTheMovieDbAppModule;
import com.example.themoviedb.dagger2.modules.TestTheMovieDbRepoBindsModule;
import com.example.themoviedb.dagger2.modules.TheMovieDbActivitiesModule;
import com.example.themoviedb.dagger2.scopes.ApplicationScope;

import dagger.Component;
import dagger.android.AndroidInjector;

/**
 * {@link TheMovieDbAppComponent} used for testing purposes.
 */
@ApplicationScope
@Component(
    modules = {
        TestTheMovieDbAppModule.class,
        TheMovieDbActivitiesModule.class,
        TestApiModule.class,
        TestTheMovieDbRepoBindsModule.class
    })
public interface TestTheMovieDbAppComponent extends AndroidInjector<TestTheMovieDbApp> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<TestTheMovieDbApp> {

        @Override
        public abstract TestTheMovieDbAppComponent build();
    }
}

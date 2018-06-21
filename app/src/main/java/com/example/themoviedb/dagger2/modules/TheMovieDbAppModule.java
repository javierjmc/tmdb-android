package com.example.themoviedb.dagger2.modules;

import android.app.Application;
import android.content.Context;

import com.example.themoviedb.TheMovieDbApp;
import com.example.themoviedb.dagger2.scopes.ApplicationScope;
import com.example.themoviedb.data.domain.ApiSchedulers;
import com.example.themoviedb.data.domain.ApiSchedulersImpl;

import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Module for app related dependencies, such as {@link Application} or {@link Context}.
 */
@Module(
    includes = {
        AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class
    })
public abstract class TheMovieDbAppModule {
    @Provides
    static Application provideApplication(TheMovieDbApp impl) {
        return impl;
    }

    @Provides
    @ApplicationScope
    static ApiSchedulers provideApiSchedulers(ApiSchedulersImpl impl) {
        return impl;
    }

    @Provides
    @ApplicationScope
    static Context provideContext(Application app) {
        return app.getBaseContext();
    }
}

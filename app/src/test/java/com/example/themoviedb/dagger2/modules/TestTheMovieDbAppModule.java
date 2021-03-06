package com.example.themoviedb.dagger2.modules;

import android.app.Application;

import com.example.themoviedb.TestTheMovieDbApp;
import com.example.themoviedb.dagger2.scopes.ApplicationScope;
import com.example.themoviedb.data.domain.ApiSchedulers;
import com.example.themoviedb.data.domain.ApiSchedulersImpl;

import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

@Module(
    includes = {
        AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class
    })
public abstract class TestTheMovieDbAppModule extends TheMovieDbAppModule {
    @Provides
    static Application provideApplication(TestTheMovieDbApp impl) {
        return impl;
    }

    @Provides
    @ApplicationScope
    static ApiSchedulers provideApiSchedulers(ApiSchedulersImpl impl) { return impl; }
}

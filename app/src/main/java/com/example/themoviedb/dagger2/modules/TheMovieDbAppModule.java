package com.example.themoviedb.dagger2.modules;

import android.app.Application;

import com.example.themoviedb.TheMovieDbApp;

import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

@Module(
    includes = {
        AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        ApiModule.class
    })
public abstract class TheMovieDbAppModule {
    @Provides
    static Application provideApplication(TheMovieDbApp impl) {
        return impl;
    }
}

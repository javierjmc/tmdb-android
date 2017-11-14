package com.example.themoviedb;

import com.example.themoviedb.dagger2.DaggerTheMovieDbAppComponent;

import net.danlew.android.joda.JodaTimeAndroid;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import timber.log.Timber;

public class TheMovieDbApp extends DaggerApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        JodaTimeAndroid.init(this);

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            //Plant CrashReportingTree, Fabric/Crashlytics, Firebase etc...
        }
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerTheMovieDbAppComponent.builder().create(this);
    }
}

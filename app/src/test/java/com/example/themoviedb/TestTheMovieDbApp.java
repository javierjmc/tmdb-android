package com.example.themoviedb;

import net.danlew.android.joda.JodaTimeAndroid;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import timber.log.Timber;

public class TestTheMovieDbApp extends DaggerApplication {
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
        return null;
    }
}

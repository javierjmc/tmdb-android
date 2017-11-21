package com.example.themoviedb;

import com.example.themoviedb.dagger2.DaggerTheMovieDbAppComponent;
import com.example.themoviedb.utils.DevTool;

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
            DevTool.initForApp(this);
            Timber.plant(new Timber.DebugTree());
        } else {
            //Plant CrashReportingTree, Fabric/Crashlytics, Firebase etc...
        }

        DevTool.plantDevTree();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerTheMovieDbAppComponent.builder().create(this);
    }
}

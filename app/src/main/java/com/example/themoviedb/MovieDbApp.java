package com.example.themoviedb;

import android.app.Application;

import net.danlew.android.joda.JodaTimeAndroid;

import timber.log.Timber;

public class MovieDbApp extends Application {
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
}

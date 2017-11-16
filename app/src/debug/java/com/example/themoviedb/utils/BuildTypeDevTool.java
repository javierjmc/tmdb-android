package com.example.themoviedb.utils;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.facebook.stetho.timber.StethoTree;

import okhttp3.OkHttpClient;
import timber.log.Timber;

/**
 * Debug methods for development builds.
 */
class BuildTypeDevTool implements DevTool.DevToolInterface {

    @Override
    public void initForApp(final Application app) {
        Stetho.initializeWithDefaults(app);
    }

    @Override
    public void injectDev(final OkHttpClient.Builder client) {
        client.addNetworkInterceptor(new StethoInterceptor());
    }

    @Override
    public void plantDevTree() {
        Timber.plant(new StethoTree());
    }
}

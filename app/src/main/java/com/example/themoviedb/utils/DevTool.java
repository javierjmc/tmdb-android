package com.example.themoviedb.utils;

import android.app.Application;

import okhttp3.OkHttpClient;
import timber.log.Timber;

/**
 * Tools used in development. Initializes {@link com.facebook.stetho.Stetho}, provides a {@link Timber.Tree} and adds an {@link okhttp3.Interceptor} to the http client.
 */
public abstract class DevTool {

    interface DevToolInterface {
        void initForApp(final Application app);

        void injectDev(final OkHttpClient.Builder client);

        void plantDevTree();
    }

    private static final DevToolInterface INSTANCE = new BuildTypeDevTool();

    public static void initForApp(final Application app) {
        INSTANCE.initForApp(app);
    }

    public static void injectDev(final OkHttpClient.Builder client) {
        INSTANCE.injectDev(client);
    }

    public static void plantDevTree() {
        INSTANCE.plantDevTree();
    }
}

package com.example.themoviedb.dagger2.modules;

import android.app.Application;

import com.example.themoviedb.dagger2.scopes.ApplicationScope;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * Provides instance of the OkHttpClient.
 */
@Module
public abstract class OkHttpModule {

    private static final int DEFAULT_DISK_CACHE_SIZE = 30 * 1024 * 1024; // 30MB

    @Provides
    @ApplicationScope
    static OkHttpClient provideOkHttpClient(final Application app) {
        final OkHttpClient.Builder client = new OkHttpClient.Builder();

        // Set up disk cache
        client.cache(new Cache(app.getCacheDir(), DEFAULT_DISK_CACHE_SIZE));

        // Bump the default timeouts up a bit (default is 10s)
        client.readTimeout(30, TimeUnit.SECONDS);
        client.writeTimeout(30, TimeUnit.SECONDS);
        client.connectTimeout(30, TimeUnit.SECONDS);

        return client.build();
    }
}

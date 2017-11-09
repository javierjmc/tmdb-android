package com.example.themoviedb.dagger2.modules;

import com.example.themoviedb.dagger2.scopes.ApplicationScope;
import com.squareup.moshi.Moshi;

import dagger.Module;
import dagger.Provides;
import retrofit2.converter.moshi.MoshiConverterFactory;


@Module
public class MoshiModule {
    public static final Moshi MOSHI = new Moshi.Builder().build();

    @Provides
    MoshiConverterFactory provideConverter(final Moshi moshi) {
        return MoshiConverterFactory.create(moshi);
    }

    @Provides
    @ApplicationScope
    Moshi provideMoshi() {
        return MOSHI;
    }
}


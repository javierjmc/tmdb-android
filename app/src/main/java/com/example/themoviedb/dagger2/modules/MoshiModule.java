package com.example.themoviedb.dagger2.modules;

import com.example.themoviedb.dagger2.scopes.ApplicationScope;
import com.example.themoviedb.data.domain.moshi.JodaTimeMoshiAdapter;
import com.example.themoviedb.data.domain.moshi.MoshiAdapterFactory;
import com.squareup.moshi.Moshi;

import dagger.Module;
import dagger.Provides;
import retrofit2.converter.moshi.MoshiConverterFactory;


@Module
public class MoshiModule {
    public static final Moshi MOSHI = new Moshi.Builder()
        .add(new JodaTimeMoshiAdapter())
        .add(MoshiAdapterFactory.create())
        .build();

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


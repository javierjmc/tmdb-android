package com.example.themoviedb.dagger2.modules;

import com.example.themoviedb.data.domain.TheMovieDbApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Provides the {@link TheMovieDbApi} interface.
 */
@Module
public class RestServiceModule {

    @Provides
    public TheMovieDbApi provideTheMovieDbApi(final Retrofit retrofit) {
        return retrofit.create(TheMovieDbApi.class);
    }

}

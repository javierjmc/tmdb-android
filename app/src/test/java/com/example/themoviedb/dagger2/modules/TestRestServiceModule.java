package com.example.themoviedb.dagger2.modules;

import com.example.themoviedb.data.domain.TestTheMovieDbApi;
import com.example.themoviedb.data.domain.TheMovieDbApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Provides a fake {@link TheMovieDbApi} interface for testing.
 */
@Module
public class TestRestServiceModule {

    @Provides
    public TestTheMovieDbApi provideTheMovieDbApi(final Retrofit retrofit) {
        return retrofit.create(TestTheMovieDbApi.class);
    }

}

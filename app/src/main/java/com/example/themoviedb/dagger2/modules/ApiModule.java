package com.example.themoviedb.dagger2.modules;

import com.example.themoviedb.BuildConfig;
import com.example.themoviedb.dagger2.qualifiers.ApiEndpoint;
import com.example.themoviedb.dagger2.scopes.ApplicationScope;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import timber.log.Timber;

/**
 * Provides objects needed to do network operations: {@link Retrofit}, {@link HttpUrl}, {@link Converter.Factory}, {@link HttpLoggingInterceptor.Level}
 */
@Module(
    includes = {
        OkHttpModule.class,
        MoshiModule.class,
        RestServiceModule.class
    })
public class ApiModule {

    @Provides
    HttpLoggingInterceptor.Level provideOkHttpLogLevel() {
        if (BuildConfig.DEBUG) {
            return HttpLoggingInterceptor.Level.BODY;
        } else {
            return HttpLoggingInterceptor.Level.NONE;
        }
    }

    @Provides
    Converter.Factory provideConverter(final MoshiConverterFactory moshiConverterFactory) {
        return moshiConverterFactory;
    }

    @Provides
    @ApplicationScope
    @ApiEndpoint
    HttpUrl provideApiEndpoint() {
        return HttpUrl.parse(BuildConfig.SERVER_URL);
    }

    @Provides
    @ApplicationScope
    Retrofit provideRestAdapter(final @ApiEndpoint HttpUrl endpointUrl,
                                final HttpLoggingInterceptor.Level logLevel,
                                final OkHttpClient baseClient,
                                final Converter.Factory converterFactory) {

        final OkHttpClient.Builder client = baseClient.newBuilder();

        if (logLevel != HttpLoggingInterceptor.Level.NONE) {
            client.addInterceptor(new HttpLoggingInterceptor(message -> Timber.v(message)).setLevel(logLevel));
        }

        return new Retrofit.Builder()
            .baseUrl(endpointUrl)
            .client(client.build())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
    }
}


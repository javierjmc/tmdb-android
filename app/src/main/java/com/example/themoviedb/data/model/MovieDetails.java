package com.example.themoviedb.data.model;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;

import org.joda.time.DateTime;

import java.util.List;

@AutoValue
public abstract class MovieDetails implements FeedItem {

    public abstract long id();

    @Json(name = "vote_average")
    public abstract float voteAverage();

    @Json(name = "vote_count")
    public abstract long voteCount();

    public abstract String title();

    @Nullable
    @Json(name = "poster_path")
    public abstract String posterPath();

    @Nullable
    @Json(name = "original_language")
    public abstract String originalLanguage();

    @Nullable
    @Json(name = "original_title")
    public abstract String originalTitle();

    @Json(name = "genre_ids")
    public abstract List<Genre> genreIds();

    public abstract boolean adult();

    public abstract String overview();

    @Json(name = "release_date")
    public abstract DateTime releaseDate();

    public abstract int runtime();

    public abstract String tagline();

    public static Builder builder() {
        return new AutoValue_MovieDetails.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder id(long value);

        public abstract Builder voteAverage(float value);

        public abstract Builder voteCount(long value);

        public abstract Builder title(String value);

        public abstract Builder posterPath(String value);

        public abstract Builder originalLanguage(String value);

        public abstract Builder originalTitle(String value);

        public abstract Builder genreIds(List<Genre> value);

        public abstract Builder adult(boolean value);

        public abstract Builder overview(String value);

        public abstract Builder releaseDate(DateTime value);

        public abstract Builder runtime(int value);

        public abstract Builder tagline(String value);

        public abstract MovieDetails build();
    }
}

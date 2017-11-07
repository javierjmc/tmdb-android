package com.example.themoviedb.data;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

import org.joda.time.DateTime;

import java.util.List;

@AutoValue
public abstract class Movie {
    public abstract long id();

    public abstract float voteAverage();

    public abstract long voteCount();

    public abstract String title();

    @Nullable public abstract String posterPath();

    @Nullable public abstract String originalLanguage();

    @Nullable public abstract String originalTitle();

    public abstract List<Long> genres();

    public abstract boolean adult();

    public abstract String overview();

    public abstract DateTime releaseDate();

    public static Builder builder() {
        return new AutoValue_Movie.Builder();
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

        public abstract Builder genres(List<Long> value);

        public abstract Builder adult(boolean value);

        public abstract Builder overview(String value);

        public abstract Builder releaseDate(DateTime value);

        public abstract Movie build();
    }
}



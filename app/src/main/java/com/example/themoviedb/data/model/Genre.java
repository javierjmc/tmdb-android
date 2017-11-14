package com.example.themoviedb.data.model;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Genre implements FeedItem {
    public abstract long id();

    public abstract String name();

    public static Builder builder() {
        return new AutoValue_Genre.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder id(long value);

        public abstract Builder name(String value);

        public abstract Genre build();
    }
}

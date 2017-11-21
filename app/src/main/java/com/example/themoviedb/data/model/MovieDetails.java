package com.example.themoviedb.data.model;

import android.support.annotation.Nullable;

import com.squareup.moshi.Json;

import org.joda.time.DateTime;

import java.util.List;

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
}

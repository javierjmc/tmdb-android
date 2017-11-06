package com.example.themoviedb.data;

import org.joda.time.DateTime;

import java.util.List;

public class Movie {
    public long id;
    public float voteAverage;
    public long voteCount;
    public String title;
    public String posterPath;
    public String originalLanguage;
    public String originalTitle;
    public List<Long> genres;
    public boolean adult;
    public String overview;
    public DateTime releaseDate;

    public Movie(long id, float voteAverage, long voteCount, String title, String posterPath, String originalLanguage, String originalTitle, List<Long> genres, boolean adult, String overview, DateTime releaseDate) {
        this.id = id;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
        this.title = title;
        this.posterPath = posterPath;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.genres = genres;
        this.adult = adult;
        this.overview = overview;
        this.releaseDate = releaseDate;
    }

    public long getId() {
        return id;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public long getVoteCount() {
        return voteCount;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public List<Long> getGenres() {
        return genres;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getOverview() {
        return overview;
    }

    public DateTime getReleaseDate() {
        return releaseDate;
    }
}

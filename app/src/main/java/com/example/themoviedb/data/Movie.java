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
}

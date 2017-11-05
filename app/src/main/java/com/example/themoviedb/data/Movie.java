package com.example.themoviedb.data;

import org.joda.time.DateTime;

import java.util.List;

public class Movie {
    public long mId;
    public float mVoteAverage;
    public long mVoteCount;
    public String mTitle;
    public String mPosterPath;
    public String mOriginalLanguage;
    public String mOriginalTitle;
    public List<Long> mGenres;
    public boolean mAdult;
    public String mOverview;
    public DateTime mReleaseDate;
}

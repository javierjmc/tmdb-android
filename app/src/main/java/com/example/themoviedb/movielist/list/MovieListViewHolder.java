package com.example.themoviedb.movielist.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.themoviedb.R;
import com.example.themoviedb.data.Movie;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.poster)
    ImageView mPoster;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.rating)
    TextView mRating;
    @BindView(R.id.date)
    TextView mDate;
    @BindView(R.id.genres)
    TextView mGenres;
    @BindView(R.id.overview)
    TextView mOverview;


    public MovieListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Movie movie) {
        mTitle.setText(movie.getTitle());
        mRating.setText(String.valueOf(movie.getVoteAverage()));
        mDate.setText(movie.getReleaseDate().toString());
        mGenres.setText(movie.getGenres().toString());
        mOverview.setText(movie.getOverview());
    }
}

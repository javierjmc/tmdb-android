package com.example.themoviedb.movielist.list;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.themoviedb.R;
import com.example.themoviedb.data.model.Movie;
import com.example.themoviedb.utils.GlideApp;

import java.util.List;

import butterknife.BindDrawable;
import butterknife.BindString;
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

    @BindString(R.string.movie_image_url_endppoint)
    String mImageUrlBase;
    @BindDrawable(R.drawable.ic_movie)
    Drawable mMovieIcon;

    private Context mContext;


    public MovieListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = itemView.getContext();
    }

    public void bind(Movie movie, List<String> genres) {
        mTitle.setText(movie.title());
        mRating.setText(String.valueOf(movie.voteAverage()));
        mDate.setText(Integer.toString(movie.releaseDate().getYear()));
        mGenres.setText(genres.toString().replaceAll("[\\[.\\].\\s+]", " "));
        mOverview.setText(movie.overview());

        GlideApp
            .with(mContext)
            .load(mImageUrlBase.concat(movie.posterPath()))
            .placeholder(mMovieIcon)
            .centerCrop()
            .into(mPoster);
    }
}

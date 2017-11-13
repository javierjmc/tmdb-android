package com.example.themoviedb.movielist.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.themoviedb.R;
import com.example.themoviedb.data.model.Movie;
import com.example.themoviedb.utils.GlideApp;

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

    private Context mContext;


    public MovieListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = itemView.getContext();
    }

    public void bind(Movie movie) {
        mTitle.setText(movie.title());
        mRating.setText(String.valueOf(movie.voteAverage()));
        mDate.setText(movie.releaseDate().toString());
        mGenres.setText(movie.genreIds().toString());
        mOverview.setText(movie.overview());

        GlideApp
            .with(mContext)
            .load(mImageUrlBase.concat(movie.posterPath()))
            .centerCrop()
            .into(mPoster);
    }
}

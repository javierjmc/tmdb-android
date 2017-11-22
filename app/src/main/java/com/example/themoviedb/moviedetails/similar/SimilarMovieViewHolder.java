package com.example.themoviedb.moviedetails.similar;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.themoviedb.R;
import com.example.themoviedb.data.model.Movie;
import com.example.themoviedb.utils.GlideApp;

import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SimilarMovieViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.poster)
    ImageView mPoster;
    @BindView(R.id.rating)
    TextView mRating;
    @BindView(R.id.seen)
    ImageView mWatched;

    @BindString(R.string.movie_image_small_url_endppoint)
    String mImageUrlBase;
    @BindDrawable(R.drawable.ic_movie)
    Drawable mMovieIcon;

    private Context mContext;

    public SimilarMovieViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = itemView.getContext();
    }

    public void bind(Movie movie, SimilarMoviesAdapter.OnSimilarMovieItemClickListener feedItemListener) {
        mRating.setText(String.valueOf(movie.voteAverage()));

        final Boolean watched = movie.watched();
        if (watched != null) {
            mWatched.setVisibility(watched ? View.VISIBLE : View.GONE);
        }

        GlideApp
            .with(mContext)
            .load(mImageUrlBase.concat(movie.posterPath()))
            .placeholder(mMovieIcon)
            .centerCrop()
            .into(mPoster);

        itemView.setOnClickListener(view -> feedItemListener.onMovieItemClick(movie));
    }
}

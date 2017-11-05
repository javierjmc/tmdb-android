package com.example.themoviedb.movielist.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.themoviedb.data.Movie;

import butterknife.ButterKnife;

public class MovieListViewHolder extends RecyclerView.ViewHolder {

    public MovieListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Movie movie) {

    }
}

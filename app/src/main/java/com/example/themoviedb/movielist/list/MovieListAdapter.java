package com.example.themoviedb.movielist.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.themoviedb.R;
import com.example.themoviedb.data.model.FeedItem;
import com.example.themoviedb.data.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListViewHolder> {

    private List<FeedItem> movieList = new ArrayList<>();

    public void setMovieList(List<FeedItem> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    @Override
    public MovieListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new MovieListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieListViewHolder viewHolder, int position) {
        viewHolder.bind((Movie) movieList.get(position));
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}

package com.example.themoviedb.moviedetails.similar;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.themoviedb.R;
import com.example.themoviedb.data.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class SimilarMoviesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface OnSimilarMovieItemClickListener {
        void onMovieItemClick(Movie movie);
    }

    private List<Movie> similarMovies = new ArrayList<>();
    private OnSimilarMovieItemClickListener listener;

    public void setSimilarMovies(List<Movie> similarMovies) {
        this.similarMovies = similarMovies;
        notifyDataSetChanged();
    }

    public List<Movie> getSimilarMovies() {
        return similarMovies;
    }

    private OnSimilarMovieItemClickListener getFeedItemListener() {
        return listener;
    }

    public void setFeedItemListener(OnSimilarMovieItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SimilarMovieViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item_small, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ((SimilarMovieViewHolder) viewHolder).bind(similarMovies.get(position), getFeedItemListener());
    }

    @Override
    public int getItemCount() {
        return similarMovies.size();
    }
}

package com.example.themoviedb.movielist.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.themoviedb.R;
import com.example.themoviedb.data.model.FeedItem;
import com.example.themoviedb.data.model.Genre;
import com.example.themoviedb.data.model.Movie;
import com.example.themoviedb.utils.GenreUtil;

import java.util.ArrayList;
import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_MOVIE = 0;
    private static final int VIEW_TYPE_LOADING_NEXT_PAGE = 1;

    private List<FeedItem> feedItems = new ArrayList<>();
    private boolean isLoadingNextPage = false;
    private List<Genre> genres = new ArrayList<>();

    public boolean isLoadingNextPage() {
        return isLoadingNextPage;
    }

    public void setItems(List<FeedItem> movieList) {
        this.feedItems = movieList;
        notifyDataSetChanged();
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
        notifyDataSetChanged();
    }

    public List<FeedItem> getFeedItems() {
        return feedItems;
    }

    /**
     * @return true if value has changed since last invocation
     */
    public boolean setLoadingNextPage(boolean loadingNextPage) {
        final boolean hasLoadingMoreChanged = loadingNextPage != isLoadingNextPage;

        final boolean notifyInserted = loadingNextPage && hasLoadingMoreChanged;
        final boolean notifyRemoved = !loadingNextPage && hasLoadingMoreChanged;
        isLoadingNextPage = loadingNextPage;

        if (notifyInserted) {
            notifyItemInserted(feedItems.size());
        } else if (notifyRemoved) {
            notifyItemRemoved(feedItems.size());
        }

        return hasLoadingMoreChanged;
    }

    @Override
    public int getItemViewType(int position) {
        if (isLoadingNextPage && position == feedItems.size()) {
            return VIEW_TYPE_LOADING_NEXT_PAGE;
        }

        final FeedItem item = feedItems.get(position);

        if (item instanceof Movie) {
            return VIEW_TYPE_MOVIE;
        }

        throw new IllegalArgumentException("There's no view type implementation for item" + item + ", at position: " + position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_MOVIE:
                return new MovieListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false));
            case VIEW_TYPE_LOADING_NEXT_PAGE:
                return new LoadingViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.loader_item, parent, false));
        }

        throw new IllegalArgumentException("Couldn't create a ViewHolder for viewType: " + viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof LoadingViewHolder) {
            return;
        }

        if (viewHolder instanceof MovieListViewHolder) {
            final Movie movie = (Movie) feedItems.get(position);
            final List<String> genresString = GenreUtil.filterGenres(genres, movie);

            ((MovieListViewHolder) viewHolder).bind(movie, genresString);
        } else {
            throw new IllegalArgumentException("couldn't accept  ViewHolder " + viewHolder);
        }
    }

    @Override
    public int getItemCount() {
        return feedItems == null ? 0 : (feedItems.size() + (isLoadingNextPage ? 1 : 0));
    }
}

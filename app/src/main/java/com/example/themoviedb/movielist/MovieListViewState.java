package com.example.themoviedb.movielist;

import android.support.annotation.Nullable;

import com.example.themoviedb.data.model.FeedItem;
import com.google.auto.value.AutoValue;

import java.util.List;

@AutoValue
public abstract class MovieListViewState {

    @Nullable
    public abstract Boolean loadingFirstPage();

    @Nullable
    public abstract Throwable firstPageError();

    @Nullable
    public abstract List<FeedItem> data();

    @Nullable
    public abstract Integer page();

    @Nullable
    public abstract Boolean loadingNextPage();

    @Nullable
    public abstract Throwable nextPageError();

    @Nullable
    public abstract Boolean loadingPullToRefresh();

    @Nullable
    public abstract Throwable pullToRefreshError();

    @Nullable
    public static Builder builder() {
        return new AutoValue_MovieListViewState.Builder();
    }

    public abstract Builder toBuilder();

    public boolean isLoading() {
        return loadingFirstPage() != null ? loadingFirstPage() : loadingNextPage() != null ? loadingNextPage() : loadingPullToRefresh() != null ? loadingPullToRefresh() : false;
    }

    public Throwable getError() {
        return firstPageError() != null ? firstPageError() : nextPageError() != null ? nextPageError() : pullToRefreshError();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder loadingFirstPage(Boolean value);

        public abstract Builder firstPageError(Throwable value);

        public abstract Builder data(List<FeedItem> value);

        public abstract Builder page(Integer value);

        public abstract Builder loadingNextPage(Boolean value);

        public abstract Builder nextPageError(Throwable value);

        public abstract Builder loadingPullToRefresh(Boolean value);

        public abstract Builder pullToRefreshError(Throwable value);

        public abstract MovieListViewState build();
    }
}

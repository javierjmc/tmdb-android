package com.example.themoviedb.movielist;

import android.support.annotation.Nullable;

import com.example.themoviedb.data.model.FeedItem;

import java.util.List;

public class MovieListViewState {

    @Nullable
    public Boolean loadingFirstPage;

    @Nullable
    public Throwable firstPageError;

    @Nullable
    public List<FeedItem> data;

    @Nullable
    public Integer page;

    @Nullable
    public Boolean loadingNextPage;

    @Nullable
    public Throwable nextPageError;

    @Nullable
    public Boolean loadingPullToRefresh;

    @Nullable
    public Throwable pullToRefreshError;


    public boolean isLoading() {
        return loadingFirstPage() != null ? loadingFirstPage() : loadingNextPage() != null ? loadingNextPage() : loadingPullToRefresh() != null ? loadingPullToRefresh() : false;
    }

    public Throwable getError() {
        return firstPageError() != null ? firstPageError() : nextPageError() != null ? nextPageError() : pullToRefreshError();
    }

    public static Builder builder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    public MovieListViewState(Boolean loadingFirstPage, Throwable firstPageError, List<FeedItem> data, Integer page, Boolean loadingNextPage, Throwable nextPageError, Boolean loadingPullToRefresh, Throwable pullToRefreshError) {
        this.loadingFirstPage = loadingFirstPage;
        this.firstPageError = firstPageError;
        this.data = data;
        this.page = page;
        this.loadingNextPage = loadingNextPage;
        this.nextPageError = nextPageError;
        this.loadingPullToRefresh = loadingPullToRefresh;
        this.pullToRefreshError = pullToRefreshError;
    }

    @Nullable
    public Boolean loadingFirstPage() {
        return loadingFirstPage;
    }

    @Nullable
    public Throwable firstPageError() {
        return firstPageError;
    }

    @Nullable
    public List<FeedItem> data() {
        return data;
    }

    @Nullable
    public Integer page() {
        return page;
    }

    @Nullable
    public Boolean loadingNextPage() {
        return loadingNextPage;
    }

    @Nullable
    public Throwable nextPageError() {
        return nextPageError;
    }

    @Nullable
    public Boolean loadingPullToRefresh() {
        return loadingPullToRefresh;
    }

    @Nullable
    public Throwable pullToRefreshError() {
        return pullToRefreshError;
    }

    @Override
    public String toString() {
        return "MovieListViewState{" +
            "loadingFirstPage=" + loadingFirstPage +
            ", firstPageError=" + firstPageError +
            ", data=" + data +
            ", page=" + page +
            ", loadingNextPage=" + loadingNextPage +
            ", nextPageError=" + nextPageError +
            ", loadingPullToRefresh=" + loadingPullToRefresh +
            ", pullToRefreshError=" + pullToRefreshError +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieListViewState that = (MovieListViewState) o;

        if (loadingFirstPage != null ? !loadingFirstPage.equals(that.loadingFirstPage) : that.loadingFirstPage != null)
            return false;
        if (firstPageError != null ? !firstPageError.equals(that.firstPageError) : that.firstPageError != null)
            return false;
        if (data != null ? !data.equals(that.data) : that.data != null) return false;
        if (page != null ? !page.equals(that.page) : that.page != null) return false;
        if (loadingNextPage != null ? !loadingNextPage.equals(that.loadingNextPage) : that.loadingNextPage != null)
            return false;
        if (nextPageError != null ? !nextPageError.equals(that.nextPageError) : that.nextPageError != null)
            return false;
        if (loadingPullToRefresh != null ? !loadingPullToRefresh.equals(that.loadingPullToRefresh) : that.loadingPullToRefresh != null)
            return false;
        return pullToRefreshError != null ? pullToRefreshError.equals(that.pullToRefreshError) : that.pullToRefreshError == null;
    }

    @Override
    public int hashCode() {
        int result = loadingFirstPage != null ? loadingFirstPage.hashCode() : 0;
        result = 31 * result + (firstPageError != null ? firstPageError.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        result = 31 * result + (page != null ? page.hashCode() : 0);
        result = 31 * result + (loadingNextPage != null ? loadingNextPage.hashCode() : 0);
        result = 31 * result + (nextPageError != null ? nextPageError.hashCode() : 0);
        result = 31 * result + (loadingPullToRefresh != null ? loadingPullToRefresh.hashCode() : 0);
        result = 31 * result + (pullToRefreshError != null ? pullToRefreshError.hashCode() : 0);
        return result;
    }

    public static final class Builder {
        @Nullable
        public Boolean loadingFirstPage;
        @Nullable
        public Throwable firstPageError;
        @Nullable
        public List<FeedItem> data;
        @Nullable
        public Integer page;
        @Nullable
        public Boolean loadingNextPage;
        @Nullable
        public Throwable nextPageError;
        @Nullable
        public Boolean loadingPullToRefresh;
        @Nullable
        public Throwable pullToRefreshError;

        public Builder() {
        }

        public Builder(MovieListViewState toCopyFrom) {
            this.loadingFirstPage = toCopyFrom.loadingFirstPage();
            this.firstPageError = toCopyFrom.firstPageError();
            this.data = toCopyFrom.data();
            this.page = toCopyFrom.page();
            this.loadingNextPage = toCopyFrom.loadingNextPage();
            this.nextPageError = toCopyFrom.nextPageError();
            this.loadingPullToRefresh = toCopyFrom.loadingPullToRefresh();
            this.pullToRefreshError = toCopyFrom.pullToRefreshError();
        }

        public Builder loadingFirstPage(Boolean value) {
            this.loadingFirstPage = value;
            return this;
        }

        public Builder firstPageError(Throwable value) {
            this.firstPageError = value;
            return this;
        }

        public Builder data(List<FeedItem> value) {
            this.data = value;
            return this;
        }


        public Builder page(Integer value) {
            this.page = value;
            return this;
        }


        public Builder loadingNextPage(Boolean value) {
            this.loadingNextPage = value;
            return this;
        }

        public Builder nextPageError(Throwable value) {
            this.nextPageError = value;
            return this;
        }

        public Builder loadingPullToRefresh(Boolean value) {
            this.loadingPullToRefresh = value;
            return this;
        }

        public Builder pullToRefreshError(Throwable value) {
            this.pullToRefreshError = value;
            return this;
        }

        public MovieListViewState build() {
            return new MovieListViewState(loadingFirstPage, firstPageError, data, page, loadingNextPage, nextPageError, loadingPullToRefresh, pullToRefreshError);
        }
    }
}



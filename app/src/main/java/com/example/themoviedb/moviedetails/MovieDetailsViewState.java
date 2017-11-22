package com.example.themoviedb.moviedetails;


import android.support.annotation.Nullable;

import com.example.themoviedb.data.model.Movie;

import java.util.List;

public class MovieDetailsViewState {

    @Nullable
    public Boolean loadingMovieDetails;

    @Nullable
    public Throwable loadingMovieDetailsError;

    @Nullable
    public Movie movie;

    @Nullable
    public Boolean loadingSimilarMovies;

    @Nullable
    public Throwable loadingSimilarMoviesError;

    @Nullable
    public List<Movie> similarMovies;

    @Nullable
    public Boolean markingMovieAsWatched;

    @Nullable
    public Throwable markingMovieAsWatchedError;

    @Nullable
    public List<String> genreNames;

    public boolean isLoading() {
        return loadingMovieDetails() != null ? loadingMovieDetails() : loadingSimilarMovies() != null ? loadingSimilarMovies() : false;
    }

    public Throwable getError() {
        return loadingMovieDetailsError() != null ? loadingMovieDetailsError() : loadingSimilarMoviesError() != null ? loadingSimilarMoviesError() : markingMovieAsWatchedError();
    }

    public static Builder builder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    public MovieDetailsViewState(Boolean loadingMovieDetails, Throwable loadingMovieDetailsError, Movie movie, Boolean loadingSimilarMovies, Throwable loadingSimilarMoviesError, List<Movie> similarMovies, Boolean markingMovieAsWatched, Throwable markingMovieAsWatchedError, List<String> genreNames) {
        this.loadingMovieDetails = loadingMovieDetails;
        this.loadingMovieDetailsError = loadingMovieDetailsError;
        this.movie = movie;
        this.loadingSimilarMovies = loadingSimilarMovies;
        this.loadingSimilarMoviesError = loadingSimilarMoviesError;
        this.similarMovies = similarMovies;
        this.markingMovieAsWatched = markingMovieAsWatched;
        this.markingMovieAsWatchedError = markingMovieAsWatchedError;
        this.genreNames = genreNames;
    }

    @Nullable
    public Boolean loadingMovieDetails() {
        return loadingMovieDetails;
    }

    @Nullable
    public Throwable loadingMovieDetailsError() {
        return loadingMovieDetailsError;
    }

    @Nullable
    public Movie movie() {
        return movie;
    }

    @Nullable
    public Boolean loadingSimilarMovies() {
        return loadingSimilarMovies;
    }

    @Nullable
    public Throwable loadingSimilarMoviesError() {
        return loadingSimilarMoviesError;
    }

    @Nullable
    public List<Movie> similarMovies() {
        return similarMovies;
    }

    @Nullable
    public Boolean markingMovieAsWatched() {
        return markingMovieAsWatched;
    }

    @Nullable
    public Throwable markingMovieAsWatchedError() {
        return markingMovieAsWatchedError;
    }

    @Nullable
    public List<String> genreNames() {
        return genreNames;
    }

    @Override
    public String toString() {
        return "MovieDetailsViewState{" +
            "loadingMovieDetails=" + loadingMovieDetails +
            ", loadingMovieDetailsError=" + loadingMovieDetailsError +
            ", movie=" + movie +
            ", loadingSimilarMovies=" + loadingSimilarMovies +
            ", loadingSimilarMoviesError=" + loadingSimilarMoviesError +
            ", similarMovies=" + similarMovies +
            ", markingMovieAsWatched=" + markingMovieAsWatched +
            ", markingMovieAsWatchedError=" + markingMovieAsWatchedError +
            ", genreNames=" + genreNames +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieDetailsViewState that = (MovieDetailsViewState) o;

        if (loadingMovieDetails != null ? !loadingMovieDetails.equals(that.loadingMovieDetails) : that.loadingMovieDetails != null)
            return false;
        if (loadingMovieDetailsError != null ? !loadingMovieDetailsError.equals(that.loadingMovieDetailsError) : that.loadingMovieDetailsError != null)
            return false;
        if (movie != null ? !movie.equals(that.movie) : that.movie != null) return false;
        if (loadingSimilarMovies != null ? !loadingSimilarMovies.equals(that.loadingSimilarMovies) : that.loadingSimilarMovies != null)
            return false;
        if (loadingSimilarMoviesError != null ? !loadingSimilarMoviesError.equals(that.loadingSimilarMoviesError) : that.loadingSimilarMoviesError != null)
            return false;
        if (similarMovies != null ? !similarMovies.equals(that.similarMovies) : that.similarMovies != null)
            return false;
        if (markingMovieAsWatched != null ? !markingMovieAsWatched.equals(that.markingMovieAsWatched) : that.markingMovieAsWatched != null)
            return false;
        if (markingMovieAsWatchedError != null ? !markingMovieAsWatchedError.equals(that.markingMovieAsWatchedError) : that.markingMovieAsWatchedError != null)
            return false;
        return genreNames != null ? genreNames.equals(that.genreNames) : that.genreNames == null;
    }

    @Override
    public int hashCode() {
        int result = loadingMovieDetails != null ? loadingMovieDetails.hashCode() : 0;
        result = 31 * result + (loadingMovieDetailsError != null ? loadingMovieDetailsError.hashCode() : 0);
        result = 31 * result + (movie != null ? movie.hashCode() : 0);
        result = 31 * result + (loadingSimilarMovies != null ? loadingSimilarMovies.hashCode() : 0);
        result = 31 * result + (loadingSimilarMoviesError != null ? loadingSimilarMoviesError.hashCode() : 0);
        result = 31 * result + (similarMovies != null ? similarMovies.hashCode() : 0);
        result = 31 * result + (markingMovieAsWatched != null ? markingMovieAsWatched.hashCode() : 0);
        result = 31 * result + (markingMovieAsWatchedError != null ? markingMovieAsWatchedError.hashCode() : 0);
        result = 31 * result + (genreNames != null ? genreNames.hashCode() : 0);
        return result;
    }

    public static final class Builder {
        @Nullable
        public Boolean loadingMovieDetails;
        @Nullable
        public Throwable loadingMovieDetailsError;
        @Nullable
        public Movie movie;
        @Nullable
        public Boolean loadingSimilarMovies;
        @Nullable
        public Throwable loadingSimilarMoviesError;
        @Nullable
        public List<Movie> similarMovies;
        @Nullable
        public Boolean markingMovieAsWatched;
        @Nullable
        public Throwable markingMovieAsWatchedError;
        @Nullable
        public List<String> genreNames;

        public Builder() {
        }

        public Builder(MovieDetailsViewState toCopyFrom) {
            this.loadingMovieDetails = toCopyFrom.loadingMovieDetails();
            this.loadingMovieDetailsError = toCopyFrom.loadingMovieDetailsError();
            this.movie = toCopyFrom.movie();
            this.loadingSimilarMovies = toCopyFrom.loadingSimilarMovies();
            this.loadingSimilarMoviesError = toCopyFrom.loadingSimilarMoviesError();
            this.similarMovies = toCopyFrom.similarMovies();
            this.markingMovieAsWatched = toCopyFrom.markingMovieAsWatched();
            this.markingMovieAsWatchedError = toCopyFrom.markingMovieAsWatchedError();
            this.genreNames = toCopyFrom.genreNames();
        }

        public Builder loadingMovieDetails(Boolean value) {
            this.loadingMovieDetails = value;
            return this;
        }

        public Builder loadingMovieDetailsError(Throwable value) {
            this.loadingMovieDetailsError = value;
            return this;
        }

        public Builder movie(Movie value) {
            this.movie = value;
            return this;
        }

        public Builder loadingSimilarMovies(Boolean value) {
            this.loadingSimilarMovies = value;
            return this;
        }

        public Builder loadingSimilarMoviesError(Throwable value) {
            this.loadingSimilarMoviesError = value;
            return this;
        }

        public Builder similarMovies(List<Movie> value) {
            this.similarMovies = value;
            return this;
        }

        public Builder markingMovieAsWatched(Boolean value) {
            this.markingMovieAsWatched = value;
            return this;
        }

        public Builder markingMovieAsWatchedError(Throwable value) {
            this.markingMovieAsWatchedError = value;
            return this;
        }

        public Builder genreNames(List<String> value) {
            this.genreNames = value;
            return this;
        }

        public MovieDetailsViewState build() {
            return new MovieDetailsViewState(loadingMovieDetails, loadingMovieDetailsError, movie, loadingSimilarMovies, loadingSimilarMoviesError, similarMovies, markingMovieAsWatched, markingMovieAsWatchedError, genreNames);
        }
    }
}

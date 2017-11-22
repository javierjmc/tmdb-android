/*
 * Copyright 2016 Hannes Dorfmann.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.themoviedb.data.model;

import android.util.Pair;

import java.util.List;

public interface PartialStateChanges {

    /**
     * Indicates that the genres are loading
     */
    final class GenresLoading implements PartialStateChanges {

        @Override
        public String toString() {
            return "GenresLoadingState{}";
        }
    }

    /**
     * Indicates that an error has occurred while loading genres
     */
    final class GenresError implements PartialStateChanges {
        private final Throwable error;

        public GenresError(Throwable error) {
            this.error = error;
        }

        public Throwable getError() {
            return error;
        }

        @Override
        public String toString() {
            return "GenresError{" +
                "error=" + error +
                '}';
        }
    }

    /**
     * Indicates that the genres has been loaded successfully
     */
    final class GenresLoaded implements PartialStateChanges {
        private final List<Genre> genres;

        public GenresLoaded(List<Genre> genres) {
            this.genres = genres;
        }

        public List<Genre> getData() {
            return genres;
        }

        @Override
        public String toString() {
            return "GenresLoaded{" +
                "genres=" + genres +
                '}';
        }
    }

    /**
     * Indicates that the genre names have been loaded successfully
     */
    final class GenreNamesLoaded implements PartialStateChanges {
        private final List<String> genreNames;

        public GenreNamesLoaded(List<String> genres) {
            this.genreNames = genres;
        }

        public List<String> getData() {
            return genreNames;
        }

        @Override
        public String toString() {
            return "GenreNamesLoaded{" +
                "genres=" + genreNames +
                '}';
        }
    }

    /**
     * Indicates that the first page is loading
     */
    final class FirstPageLoading implements PartialStateChanges {

        @Override
        public String toString() {
            return "FirstPageLoadingState{}";
        }
    }

    /**
     * Indicates that an error has occurred while loading the first page
     */
    final class FirstPageError implements PartialStateChanges {
        private final Throwable error;
        private final int page;

        public FirstPageError(Pair<Integer, Throwable> error) {
            this.page = error.first;
            this.error = error.second;
        }

        public Throwable getError() {
            return error;
        }

        public int getPage() {
            return page;
        }

        @Override
        public String toString() {
            return "PageErrorState{" +
                "error=" + error +
                "page=" + page +
                '}';
        }
    }

    /**
     * Indicates that the first page data has been loaded successfully
     */
    final class FirstPageLoaded implements PartialStateChanges {
        private final List<FeedItem> data;
        private final int page;

        public FirstPageLoaded(Pair<Integer, List<FeedItem>> data) {
            this.page = data.first;
            this.data = data.second;
        }

        public List<FeedItem> getData() {
            return data;
        }

        public int getPage() {
            return page;
        }
    }

    /**
     * Indicates that an error has occurred while loading the next page
     */
    final class NextPageError implements PartialStateChanges {
        private final Throwable error;
        private final int page;

        public NextPageError(Pair<Integer, Throwable> error) {
            this.page = error.first;
            this.error = error.second;
        }

        public Throwable getError() {
            return error;
        }

        public int getPage() {
            return page;
        }

        @Override
        public String toString() {
            return "PageErrorState{" +
                "error=" + error +
                "page=" + page +
                '}';
        }
    }

    /**
     * Indicates that the first page data has been loaded successfully
     */
    final class NextPageLoaded implements PartialStateChanges {
        private final List<FeedItem> data;
        private final int page;

        public NextPageLoaded(Pair<Integer, List<FeedItem>> data) {
            this.page = data.first;
            this.data = data.second;
        }

        public List<FeedItem> getData() {
            return data;
        }

        public int getPage() {
            return page;
        }
    }

    /**
     * Indicates that loading the next page has started
     */
    final class NextPageLoading implements PartialStateChanges {
    }

    /**
     * Indicates that movie details are loading
     */
    final class MovieDetailsLoading implements PartialStateChanges {

        @Override
        public String toString() {
            return "MovieDetailsLoading{}";
        }
    }

    /**
     * Indicates that an error has occurred while loading movie details
     */
    final class MovieDetailsError implements PartialStateChanges {
        private final Throwable error;

        public MovieDetailsError(Throwable error) {
            this.error = error;
        }

        public Throwable getError() {
            return error;
        }

        @Override
        public String toString() {
            return "MovieDetailsError{" +
                "error=" + error +
                '}';
        }
    }

    /**
     * Indicates that the movie details have been loaded successfully
     */
    final class MovieDetailsLoaded implements PartialStateChanges {
        private final Movie movie;

        public MovieDetailsLoaded(Movie movie) {
            this.movie = movie;
        }

        public Movie getData() {
            return movie;
        }

        @Override
        public String toString() {
            return "MovieDetailsLoaded{" +
                "movie=" + movie +
                '}';
        }
    }

    /**
     * Indicates that similar movies are loading
     */
    final class SimilarMoviesLoading implements PartialStateChanges {

        @Override
        public String toString() {
            return "SimilarMoviesLoading{}";
        }
    }


    /**
     * Indicates that an error has occurred while loading similar movies
     */
    final class SimilarMoviesError implements PartialStateChanges {
        private final Throwable error;

        public SimilarMoviesError(Throwable error) {
            this.error = error;
        }

        public Throwable getError() {
            return error;
        }

        @Override
        public String toString() {
            return "SimilarMoviesError{" +
                "error=" + error +
                '}';
        }
    }

    /**
     * Indicates that the movie details have been loaded successfully
     */
    final class SimilarMoviesLoaded implements PartialStateChanges {
        private final List<Movie> data;

        public SimilarMoviesLoaded(List<Movie> data) {
            this.data = data;
        }

        public List<Movie> getData() {
            return data;
        }

        @Override
        public String toString() {
            return "SimilarMoviesLoaded{" +
                "data=" + data +
                '}';
        }
    }

    /**
     * Indicates that the movie is being marked as watched
     */
    final class MarkingMovieAsWatched implements PartialStateChanges {

        @Override
        public String toString() {
            return "MarkingMovieAsWatched{}";
        }
    }


    /**
     * Indicates that an error has occurred while marking the movie as watched
     */
    final class MarkingMovieAsWatchedError implements PartialStateChanges {
        private final Throwable error;

        public MarkingMovieAsWatchedError(Throwable error) {
            this.error = error;
        }

        public Throwable getError() {
            return error;
        }

        @Override
        public String toString() {
            return "MarkingMovieAsWatchedError{" +
                "error=" + error +
                '}';
        }
    }

    /**
     * Indicates that the movie was successfully marked as watched
     */
    final class MarkingMovieAsWatchedDone implements PartialStateChanges {
        private final Boolean data;

        public MarkingMovieAsWatchedDone(Boolean data) {
            this.data = data;
        }

        public Boolean getData() {
            return data;
        }

        @Override
        public String toString() {
            return "MarkingMovieAsWatchedDone{" +
                "data=" + data +
                '}';
        }
    }

    /**
     * Indicates that loading the newest items via pull to refresh has started
     */
    final class PullToRefreshLoading implements PartialStateChanges {
    }

    /**
     * Indicates that an error while loading the newest items via pull to refresh has occurred
     */
    final class PullToRefeshError implements PartialStateChanges {
        private final Throwable error;

        public PullToRefeshError(Throwable error) {
            this.error = error;
        }

        public Throwable getError() {
            return error;
        }
    }

    /**
     * Indicates that data has been loaded successfully over pull-to-refresh
     */
    final class PullToRefreshLoaded implements PartialStateChanges {
        private final List<FeedItem> data;

        public PullToRefreshLoaded(List<FeedItem> data) {
            this.data = data;
        }

        public List<FeedItem> getData() {
            return data;
        }
    }
}

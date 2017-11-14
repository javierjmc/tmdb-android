package com.example.themoviedb.data.domain;

import com.squareup.moshi.Json;

public class ApiResponseSchema<T> {

    /**
     * The page being loaded
     */
    public int page;

    /**
     * The total amount of results
     */
    @Json(name = "total_results")
    public long totalResults;

    /**
     * The total amount of pages for the given request
     */
    @Json(name = "total_pages")
    public long totalPages;

    /**
     * The data payload of the response
     */
    public T results;

    /**
     * Getter for the payload data for simpler {@code .map}s
     */
    public T getResults() {
        return results;
    }

    /**
     * Getter for the page for simpler {@code .map}s
     */
    public int getPage() {
        return page;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
            " page=" + page +
            " totalResults=" + totalResults +
            " totalPages=" + totalPages +
            ", results=" + results +
            '}';
    }

}

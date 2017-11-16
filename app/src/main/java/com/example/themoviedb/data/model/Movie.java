package com.example.themoviedb.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.Nullable;

import com.squareup.moshi.Json;

import org.joda.time.DateTime;

import java.util.List;

@Entity(tableName = "movies")
public class Movie implements FeedItem {

    @PrimaryKey
    @ColumnInfo(name = "movie_id")
    public int id;

    @Json(name = "vote_average")
    public float voteAverage;

    @Json(name = "vote_count")
    public long voteCount;

    public String title;

    @Nullable
    @Json(name = "poster_path")
    public String posterPath;

    @Nullable
    @Json(name = "original_language")
    public String originalLanguage;

    @Nullable
    @Json(name = "original_title")
    public String originalTitle;

    @Json(name = "genre_ids")
    public List<Integer> genreIds;

    public boolean adult;

    public String overview;

    @Json(name = "release_date")
    public DateTime releaseDate;

    public static Builder builder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    public Movie(int id, float voteAverage, long voteCount, String title, String posterPath, String originalLanguage, String originalTitle, List<Integer> genreIds, boolean adult, String overview, DateTime releaseDate) {
        this.id = id;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
        this.title = title;
        this.posterPath = posterPath;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.genreIds = genreIds;
        this.adult = adult;
        this.overview = overview;
        this.releaseDate = releaseDate;
    }

    public int id() {
        return id;
    }

    public float voteAverage() {
        return voteAverage;
    }

    public long voteCount() {
        return voteCount;
    }

    public String title() {
        return title;
    }

    @Nullable
    public String posterPath() {
        return posterPath;
    }

    @Nullable
    public String originalLanguage() {
        return originalLanguage;
    }

    @Nullable
    public String originalTitle() {
        return originalTitle;
    }

    public List<Integer> genreIds() {
        return genreIds;
    }

    public boolean adult() {
        return adult;
    }

    public String overview() {
        return overview;
    }

    public DateTime releaseDate() {
        return releaseDate;
    }

    @Override
    public String toString() {
        return "Movie{" +
            "id=" + id +
            ", voteAverage=" + voteAverage +
            ", voteCount=" + voteCount +
            ", title='" + title + '\'' +
            ", posterPath='" + posterPath + '\'' +
            ", originalLanguage='" + originalLanguage + '\'' +
            ", originalTitle='" + originalTitle + '\'' +
            ", genreIds=" + genreIds +
            ", adult=" + adult +
            ", overview='" + overview + '\'' +
            ", releaseDate=" + releaseDate +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (id != movie.id) return false;
        if (Float.compare(movie.voteAverage, voteAverage) != 0) return false;
        if (voteCount != movie.voteCount) return false;
        if (adult != movie.adult) return false;
        if (title != null ? !title.equals(movie.title) : movie.title != null) return false;
        if (posterPath != null ? !posterPath.equals(movie.posterPath) : movie.posterPath != null)
            return false;
        if (originalLanguage != null ? !originalLanguage.equals(movie.originalLanguage) : movie.originalLanguage != null)
            return false;
        if (originalTitle != null ? !originalTitle.equals(movie.originalTitle) : movie.originalTitle != null)
            return false;
        if (genreIds != null ? !genreIds.equals(movie.genreIds) : movie.genreIds != null)
            return false;
        if (overview != null ? !overview.equals(movie.overview) : movie.overview != null)
            return false;
        return releaseDate != null ? releaseDate.equals(movie.releaseDate) : movie.releaseDate == null;
    }

    @Override
    public int hashCode() {
        int result = id ^ (id >>> 32);
        result = 31 * result + (voteAverage != +0.0f ? Float.floatToIntBits(voteAverage) : 0);
        result = 31 * result + (int) (voteCount ^ (voteCount >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (posterPath != null ? posterPath.hashCode() : 0);
        result = 31 * result + (originalLanguage != null ? originalLanguage.hashCode() : 0);
        result = 31 * result + (originalTitle != null ? originalTitle.hashCode() : 0);
        result = 31 * result + (genreIds != null ? genreIds.hashCode() : 0);
        result = 31 * result + (adult ? 1 : 0);
        result = 31 * result + (overview != null ? overview.hashCode() : 0);
        result = 31 * result + (releaseDate != null ? releaseDate.hashCode() : 0);
        return result;
    }

    public static final class Builder {
        public int id;
        public float voteAverage;
        public long voteCount;
        public String title;
        @Nullable
        public String posterPath;
        @Nullable
        public String originalLanguage;
        @Nullable
        public String originalTitle;
        public List<Integer> genreIds;
        public boolean adult;
        public String overview;
        public DateTime releaseDate;

        public Builder() {
        }

        public Builder(Movie toCopyFrom) {
            this.id = toCopyFrom.id();
            this.voteAverage = toCopyFrom.voteAverage();
            this.voteCount = toCopyFrom.voteCount();
            this.title = toCopyFrom.title();
            this.posterPath = toCopyFrom.posterPath();
            this.originalLanguage = toCopyFrom.originalLanguage();
            this.originalTitle = toCopyFrom.originalTitle();
            this.genreIds = toCopyFrom.genreIds();
            this.adult = toCopyFrom.adult();
            this.overview = toCopyFrom.overview();
            this.releaseDate = toCopyFrom.releaseDate();
        }

        public Builder id(int value) {
            this.id = value;
            return this;
        }

        public Builder voteAverage(float value) {
            this.voteAverage = value;
            return this;
        }

        public Builder voteCount(long value) {
            this.voteCount = value;
            return this;
        }

        public Builder title(String value) {
            this.title = value;
            return this;
        }

        public Builder posterPath(String value) {
            this.posterPath = value;
            return this;
        }

        public Builder originalLanguage(String value) {
            this.originalLanguage = value;
            return this;
        }

        public Builder originalTitle(String value) {
            this.originalTitle = value;
            return this;
        }

        public Builder genreIds(List<Integer> value) {
            this.genreIds = value;
            return this;
        }

        public Builder adult(boolean value) {
            this.adult = value;
            return this;
        }

        public Builder overview(String value) {
            this.overview = value;
            return this;
        }

        public Builder releaseDate(DateTime value) {
            this.releaseDate = value;
            return this;
        }

        public Movie build() {
            return new Movie(id, voteAverage, voteCount, title, posterPath, originalLanguage, originalTitle, genreIds, adult, overview, releaseDate);
        }
    }
}



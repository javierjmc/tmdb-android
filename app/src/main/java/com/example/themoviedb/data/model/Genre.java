package com.example.themoviedb.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "genres")
public class Genre implements FeedItem {
    @PrimaryKey
    @ColumnInfo(name = "genre_id")
    public int id;

    public String name;

    public Genre(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int id() {
        return id;
    }

    public String name() {
        return name;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    @Override
    public String toString() {
        return "Genre{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Genre genre = (Genre) o;

        if (id != genre.id) return false;
        return name != null ? name.equals(genre.name) : genre.name == null;
    }

    @Override
    public int hashCode() {
        int result = id ^ (id >>> 32);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    public static final class Builder {
        public int id;

        public String name;

        public Builder() {
        }

        public Builder(Genre toCopyFrom) {
            this.id = toCopyFrom.id();
            this.name = toCopyFrom.name();
        }

        public Builder id(int value) {
            this.id = value;
            return this;
        }

        public Builder name(String value) {
            this.name = value;
            return this;
        }

        public Genre build() {
            return new Genre(id, name);
        }
    }

}

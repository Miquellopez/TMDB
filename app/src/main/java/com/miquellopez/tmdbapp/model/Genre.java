package com.miquellopez.tmdbapp.model;

import com.google.gson.annotations.SerializedName;

public class Genre {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String genre;

    public Genre(int id, String genre) {
        this.id = id;
        this.genre = genre;
    }

    public Genre() {
    }

    public int getId() {
        return id;
    }

    public String getGenre() {
        return genre;
    }
}

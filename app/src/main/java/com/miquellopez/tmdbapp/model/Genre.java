package com.miquellopez.tmdbapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Genre {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    public Genre() {
    }

    public Genre(int id, String name) {
        this.id = id;
        this.name = name;
    }

}

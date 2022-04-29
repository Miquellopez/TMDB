package com.miquellopez.tmdbapp.model;

import com.google.gson.annotations.SerializedName;

public class Creator {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;


    public Creator(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Creator() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

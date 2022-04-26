package com.miquellopez.tmdbapp.model;

import com.google.gson.annotations.SerializedName;

public class Serie {

    @SerializedName("poster_path")
    private String cover;

    @SerializedName("id")
    private String id;

    @SerializedName("overview")
    private String overview;

    @SerializedName("first_air_date")
    private String firstAirDate;

    public Serie() {
    }

    public Serie(String cover, String id, String overview, String firstAirDate) {
        this.cover = cover;
        this.id = id;
        this.overview = overview;
        this.firstAirDate = firstAirDate;
    }

    public String getCover() {
        return cover;
    }

    public String getId() {
        return id;
    }

    public String getOverview() {
        return overview;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }


}

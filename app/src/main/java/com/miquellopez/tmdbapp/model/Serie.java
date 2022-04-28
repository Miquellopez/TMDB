package com.miquellopez.tmdbapp.model;

import com.google.gson.annotations.SerializedName;


public class Serie {

    @SerializedName("poster_path")
    private String cover;

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("overview")
    private String overview;

    @SerializedName("number_of_seasons")
    private int numberOfSeasons;

    @SerializedName("first_air_date")
    private String firstAirDate;

    public Serie() {
    }

    public Serie(String cover, int id, String name, String overview, int numberOfSeasons, String firstAirDate) {
        this.cover = cover;
        this.id = id;
        this.name = name;
        this.overview = overview;
        this.numberOfSeasons = numberOfSeasons;
        this.firstAirDate = firstAirDate;
    }

    public String getName() {
        return name;
    }

    public String getCover() {
        return cover;
    }

    public int getId() {
        return id;
    }


    public String getOverview() {
        return overview;
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

}

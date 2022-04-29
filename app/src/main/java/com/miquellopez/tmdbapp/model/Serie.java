package com.miquellopez.tmdbapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Serie {

    @SerializedName("poster_path")
    private String cover;

    @SerializedName("created_by")
    private List<Creator> creators;

    @SerializedName("genres")
    private List<Genre> genres;

    @SerializedName("id")
    private int id;

    @SerializedName("homepage")
    private String homepage;

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

    public Serie(String cover, int id, List<Creator> creators, List<Genre> genres, String homepage, String name, String overview, int numberOfSeasons, String firstAirDate) {
        this.cover = cover;
        this.id = id;
        this.creators = creators;
        this.genres = genres;
        this.homepage = homepage;
        this.name = name;
        this.overview = overview;
        this.numberOfSeasons = numberOfSeasons;
        this.firstAirDate = firstAirDate;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public String getHomepage() {
        return homepage;
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

    public List<Creator> getCreators() {
        return creators;
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

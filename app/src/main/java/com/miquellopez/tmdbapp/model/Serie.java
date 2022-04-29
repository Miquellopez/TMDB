package com.miquellopez.tmdbapp.model;


import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Serie {


    @SerializedName("id")
    private int id;

    @SerializedName("vote_average")
    private double voteAverage;

    @SerializedName("poster_path")
    private String cover;

    @SerializedName("created_by")
    private List<Creator> creators;

    @SerializedName("genres")
    private List<Genre> genres;

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

    public Serie(int id, double voteAverage, String cover, List<Creator> creators, List<Genre> genres, String homepage, String name, String overview, int numberOfSeasons, String firstAirDate) {
        this.id = id;
        this.voteAverage = voteAverage;
        this.cover = cover;
        this.creators = creators;
        this.genres = genres;
        this.homepage = homepage;
        this.name = name;
        this.overview = overview;
        this.numberOfSeasons = numberOfSeasons;
        this.firstAirDate = firstAirDate;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public void setCreators(List<Creator> creators) {
        this.creators = creators;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setNumberOfSeasons(int numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public void setFirstAirDate(String firstAirDate) {
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

    public void setId(int id) {
        this.id = id;
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

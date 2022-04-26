package com.miquellopez.tmdbapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SeriesPage {

    @SerializedName("page")
    private int page;

    @SerializedName("results")
    private List<Serie> series;

    public SeriesPage() {
    }

    public SeriesPage(int page, List<Serie> series) {
        this.page = page;
        this.series = series;
    }


    public List<Serie> getSeries() {
        return series;
    }
}

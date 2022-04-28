package com.miquellopez.tmdbapp.retrofit;

import com.miquellopez.tmdbapp.model.Serie;
import com.miquellopez.tmdbapp.model.SeriesPage;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SeriesAPIService {

    @GET("popular")
    Call<SeriesPage> getSeries(@Query("api_key") String key, @Query("page") int page);


    @GET("{serieId}")
    Call<Serie> getSerieById(@Path("serieId") int id,@Query("api_key") String key);
}

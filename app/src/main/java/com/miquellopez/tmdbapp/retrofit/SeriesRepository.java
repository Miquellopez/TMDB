package com.miquellopez.tmdbapp.retrofit;

import static com.miquellopez.tmdbapp.utils.Constants.KEY;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.miquellopez.tmdbapp.model.Serie;
import com.miquellopez.tmdbapp.model.SeriesPage;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SeriesRepository {

    Retrofit seriesRetrofit;
    SeriesAPIService service;
    Application application;

    public SeriesRepository(Application application) {
        this.seriesRetrofit = new Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/tv/").addConverterFactory(GsonConverterFactory.create()).build();
        this.service = seriesRetrofit.create(SeriesAPIService.class);
        this.application = application;
    }

    public LiveData<SeriesPage> getSeries(int numPage) {

        MutableLiveData<SeriesPage> seriesPage = new MutableLiveData<>();

        Call<SeriesPage> call = service.getSeries(KEY, numPage);
        call.enqueue(new Callback<SeriesPage>() {
            @Override
            public void onResponse(Call<SeriesPage> call, Response<SeriesPage> response) {
                seriesPage.postValue(response.body());
            }

            @Override
            public void onFailure(Call<SeriesPage> call, Throwable t) {
                Toast.makeText(application.getApplicationContext(), "Cannot get series list", Toast.LENGTH_LONG).show();
            }
        });
        return seriesPage;
    }

    public LiveData<Serie> getSerieFromID(int id) {

        MutableLiveData<Serie> serie = new MutableLiveData<>();

        Call<Serie> call = service.getSerieById(id, KEY);
        call.enqueue(new Callback<Serie>() {
            @Override
            public void onResponse(Call<Serie> call, Response<Serie> response) {
                serie.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Serie> call, Throwable t) {
                Toast.makeText(application.getApplicationContext(), "Cannot load the serie", Toast.LENGTH_LONG).show();
            }
        });

        return serie;

    }

}

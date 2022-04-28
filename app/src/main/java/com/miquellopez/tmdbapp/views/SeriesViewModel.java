package com.miquellopez.tmdbapp.views;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.miquellopez.tmdbapp.model.SeriesPage;
import com.miquellopez.tmdbapp.retrofit.SeriesRepository;

public class SeriesViewModel extends AndroidViewModel {

    SeriesRepository repository;
    LiveData<SeriesPage> series;

    public SeriesViewModel(@NonNull Application application) {
        super(application);
        repository = new SeriesRepository(application);
    }

    public LiveData <SeriesPage> getSeries(int numPage) {
        series = repository.getSeries(numPage);
        return series;
    }

}
package com.miquellopez.tmdbapp.views;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.miquellopez.tmdbapp.model.Serie;
import com.miquellopez.tmdbapp.retrofit.SeriesRepository;

public class SerieDetailViewModel extends AndroidViewModel {

    SeriesRepository repository;

    public SerieDetailViewModel(@NonNull Application application) {
        super(application);
        repository = new SeriesRepository(application);
    }

    public LiveData<Serie> getSerie(int id) {
        return repository.getSerieFromID(id);
    }
}
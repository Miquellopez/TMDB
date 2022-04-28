package com.miquellopez.tmdbapp.views;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.miquellopez.tmdbapp.model.SeriesPage;
import com.miquellopez.tmdbapp.retrofit.SeriesRepository;

public class SeriesViewModel extends AndroidViewModel {

    SeriesRepository repository;

    public SeriesViewModel(@NonNull Application application) {
        super(application);
        repository = new SeriesRepository(application);
    }

    public LiveData <SeriesPage> getSeries(int numPage) {
        return repository.getSeries(numPage);
    }


}
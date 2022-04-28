package com.miquellopez.tmdbapp.views;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.miquellopez.tmdbapp.R;
import com.miquellopez.tmdbapp.databinding.SerieDetailFragmentBinding;


public class SerieDetailFragment extends Fragment {

    private SerieDetailViewModel mViewModel;
    private SerieDetailFragmentBinding binding;

    public static SerieDetailFragment newInstance() {
        return new SerieDetailFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(SerieDetailViewModel.class);
        return inflater.inflate(R.layout.serie_detail_fragment, container, false);
    }




}
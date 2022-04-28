package com.miquellopez.tmdbapp.views;

import static com.miquellopez.tmdbapp.utils.Constants.URL;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.miquellopez.tmdbapp.databinding.SerieDetailFragmentBinding;
import com.miquellopez.tmdbapp.model.Serie;


public class SerieDetailFragment extends Fragment {

    private SerieDetailViewModel mViewModel;
    private SerieDetailFragmentBinding binding;
    private LiveData<Serie> serie;
    private int id;

    public static SerieDetailFragment newInstance() {
        return new SerieDetailFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(SerieDetailViewModel.class);
        binding = SerieDetailFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            id = (SerieDetailFragmentArgs.fromBundle(getArguments()).getId());
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        serie = mViewModel.getSerie(id);
        serie.observe(getViewLifecycleOwner(), this::serieChanged);
    }

    private void serieChanged(Serie serie) {
        Glide.with(requireContext()).
                load(URL + serie.getCover()).
                apply(RequestOptions.fitCenterTransform()).
                into(binding.ivCover);
        binding.tvSerieNameCall.setText(serie.getName());
        binding.tvFirstAirDateCall.setText(serie.getFirstAirDate());
        binding.tvNumberOfSeasonsCall.setText(String.valueOf(serie.getNumberOfSeasons()));
        binding.tvOverview.setText(serie.getOverview());
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
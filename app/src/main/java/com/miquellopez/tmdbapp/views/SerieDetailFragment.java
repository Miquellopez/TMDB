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

import com.miquellopez.tmdbapp.databinding.SerieDetailFragmentBinding;

import com.miquellopez.tmdbapp.model.Serie;


public class SerieDetailFragment extends Fragment {

    private SerieDetailViewModel mViewModel;
    private SerieDetailFragmentBinding binding;
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

        /*
        Se carga el shimmer hasta que se resuelva la consulta
         */
        binding.shimmerLayout.startShimmer();

        /*
        Se recupera la serie de la API
         */
        LiveData<Serie> serie = mViewModel.getSerie(id);
        serie.observe(getViewLifecycleOwner(), this::serieChanged);
    }

    private void serieChanged(Serie serie) {

        /*
        Se para el shimmer al haber cargado la serie
         */
        stopShimmer();

        Glide.with(requireContext()).
                load(URL + serie.getCover()).
                fitCenter().
                into(binding.ivCover);
        setupCreators(serie);
        setupGenres(serie);
        binding.tvHomepageCall.setText(serie.getHomepage());
        binding.tvSerieNameCall.setText(serie.getName());
        binding.tvFirstAirDateCall.setText(serie.getFirstAirDate());
        binding.tvNumberOfSeasonsCall.setText(String.valueOf(serie.getNumberOfSeasons()));
        binding.tvOverview.setText(serie.getOverview());
    }

    private void setupGenres(Serie serie) {
        StringBuilder sbg = new StringBuilder();
        for (int i = 0; i < serie.getGenres().size(); i++) {
            sbg.append(serie.getGenres().get(i).getGenre());
            if (i != serie.getGenres().size() - 1) {
                sbg.append(", ");
            }
        }
        String genres = sbg.toString();
        binding.tvGenresCall.setText(genres);
    }

    private void setupCreators(Serie serie) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < serie.getCreators().size(); i++) {
            sb.append(serie.getCreators().get(i).getName());
            if (i != serie.getCreators().size() - 1) {
                sb.append(", ");
            }
        }
        String resultCreators = sb.toString();
        binding.tvCreatorsCall.setText(resultCreators);
    }

    private void stopShimmer() {
        binding.shimmerLayout.stopShimmer();
        binding.shimmerLayout.setVisibility(View.INVISIBLE);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
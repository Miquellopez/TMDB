package com.miquellopez.tmdbapp.views;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.miquellopez.tmdbapp.databinding.SeriesFragmentBinding;
import com.miquellopez.tmdbapp.model.Serie;
import com.miquellopez.tmdbapp.model.SeriesPage;
import com.miquellopez.tmdbapp.recyclerview.PaginationScrollListener;
import com.miquellopez.tmdbapp.recyclerview.SeriesAdapter;


public class SeriesFragment extends Fragment {

    private static final int MAX_PAGE = 4;
    private SeriesViewModel mViewModel;
    private SeriesFragmentBinding binding;
    private SeriesAdapter adapter;
    private int currentPage = 1;
    private int positionAdapter;
    private boolean isLoading = false;

    public static SeriesFragment newInstance() {
        return new SeriesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(SeriesViewModel.class);
        binding = SeriesFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        binding.rvSeries.setLayoutManager(gridLayoutManager);
        binding.rvSeries.setHasFixedSize(true);

        adapter = new SeriesAdapter(getContext());
        adapter.setListener(this::viewSerie);
        binding.rvSeries.setAdapter(adapter);

        loadNextPage();

        binding.rvSeries.addOnScrollListener(new PaginationScrollListener(gridLayoutManager) {
            @Override
            protected void loadMoreItems() {
                if (currentPage != MAX_PAGE) {
                    isLoading = true;
                    currentPage += 1;
                    loadNextPage();
                }
            }
        });

    }

    private void viewSerie(Serie serie) {
        String id = serie.getId();
        NavDirections direction = SeriesFragmentDirections.actionSeriesFragmentToSerieDetailFragment().setId(id);
        Navigation.findNavController(requireView()).navigate(direction);
    }


    private void loadNextPage() {
        LiveData<SeriesPage> nextSeries = mViewModel.getSeries(currentPage);
        nextSeries.observe(getViewLifecycleOwner(), this::onNextPage);
    }

    private void onNextPage(SeriesPage seriesPage) {
        adapter.addSeriesToList(seriesPage.getSeries());
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
package com.miquellopez.tmdbapp.views;

import static com.miquellopez.tmdbapp.utils.Constants.MAX_PAGE;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.miquellopez.tmdbapp.databinding.SeriesFragmentBinding;
import com.miquellopez.tmdbapp.model.Serie;
import com.miquellopez.tmdbapp.model.SeriesPage;
import com.miquellopez.tmdbapp.recyclerview.PaginationScrollListener;
import com.miquellopez.tmdbapp.recyclerview.SeriesAdapter;

import java.util.List;


public class SeriesFragment extends Fragment {

    private SeriesViewModel mViewModel;
    private SeriesFragmentBinding binding;
    private SeriesAdapter adapter;
    private int currentPage;
    private GridLayoutManager gridLayoutManager;
    private int positionIndex;
    private int auxPage;

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

        currentPage = 1;
        setupRV();

        if (positionIndex != 0) {
            restoreState();
        } else {
            loadNextPage();
        }


        binding.rvSeries.addOnScrollListener(new PaginationScrollListener(gridLayoutManager) {
            @Override
            protected void loadMoreItems() {

                if (currentPage != MAX_PAGE) {
                    currentPage += 1;

                    loadNextPage();

                }
            }
        });

    }

    /*
    Se recupera el estado de scroll y la lista que había antes de navegar al detalle de una serie
     */
    private void restoreState() {
        adapter.cleanList();
        List<Serie> series = mViewModel.getSeriesState();
        adapter.addSeriesToList(series);
        binding.rvSeries.getLayoutManager().scrollToPosition(positionIndex);
        positionIndex = 0;
        currentPage = auxPage;
    }

    private void setupRV() {

        //Setup del recyclerview
        gridLayoutManager = new GridLayoutManager(getContext(), 2);
        binding.rvSeries.setLayoutManager(gridLayoutManager);
        binding.rvSeries.setHasFixedSize(true);

        //Setup del adapter
        adapter = new SeriesAdapter(getContext());
        adapter.setListener(this::viewSerie);
        binding.rvSeries.setAdapter(adapter);
    }


    /*
    Se navega al detalle de la serie seleccionada y se guarda el estado anterior del fragment
     */
    private void viewSerie(Serie serie) {
        int id = serie.getId();
        saveState();
        mViewModel.saveList(adapter.getSeriesList());
        NavDirections direction = SeriesFragmentDirections.actionSeriesFragmentToSerieDetailFragment(id);
        Navigation.findNavController(requireView()).navigate(direction);
    }

    /*
    Se guarda el estado del fragment
     */
    private void saveState() {
        auxPage = currentPage;
        positionIndex = gridLayoutManager.findFirstCompletelyVisibleItemPosition();
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
package com.miquellopez.tmdbapp.views;

import static com.miquellopez.tmdbapp.utils.Constants.MAX_PAGE;

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

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.miquellopez.tmdbapp.databinding.SeriesFragmentBinding;
import com.miquellopez.tmdbapp.model.Serie;
import com.miquellopez.tmdbapp.model.SeriesPage;
import com.miquellopez.tmdbapp.recyclerview.PaginationScrollListener;
import com.miquellopez.tmdbapp.recyclerview.SeriesAdapter;

import java.util.List;
import java.util.Objects;


public class SeriesFragment extends Fragment {

    private static final String BUNDLE_RECYCLER_LAYOUT = "classname.recycler.layout";

    private SeriesViewModel mViewModel;
    private SeriesFragmentBinding binding;
    private SeriesAdapter adapter;
    private int currentPage = 1;
    GridLayoutManager gridLayoutManager;
    private Parcelable state;
    LiveData<SeriesPage> nextSeries;
    private List<Serie> auxList;
    private int auxPage;

    public static SeriesFragment newInstance() {
        return new SeriesFragment();
    }


    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(SeriesViewModel.class);
        binding = SeriesFragmentBinding.inflate(inflater, container, false);
        currentPage = 1;
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        gridLayoutManager = new GridLayoutManager(getContext(), 2);
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
                    currentPage += 1;
                    loadNextPage();
                }
            }
        });

    }

    private void viewSerie(Serie serie) {
        int id = serie.getId();
        NavDirections direction = SeriesFragmentDirections.actionSeriesFragmentToSerieDetailFragment(id);
        Navigation.findNavController(requireView()).navigate(direction);
    }


    private void loadNextPage() {
        nextSeries = mViewModel.getSeries(currentPage);
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
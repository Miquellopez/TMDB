package com.miquellopez.tmdbapp.recyclerview;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class PaginationScrollListener extends RecyclerView.OnScrollListener {

    private GridLayoutManager layoutManager;

    public PaginationScrollListener(GridLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

        if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                && firstVisibleItemPosition >= 0) {
            loadMoreItems();
        }

    }

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }


    protected abstract void loadMoreItems();


}

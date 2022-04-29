package com.miquellopez.tmdbapp.recyclerview;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.miquellopez.tmdbapp.R;
import com.miquellopez.tmdbapp.model.Serie;

import java.util.ArrayList;
import java.util.List;

public class SeriesAdapter extends RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder> {

    private Context context;
    private List<Serie> seriesList = new ArrayList<>();
    OnSerieClickedListener listener;
    private int position;
    private static final String url = "https://image.tmdb.org/t/p/w500/";

    public void setListener(OnSerieClickedListener listener) {
        this.listener = listener;
    }

    public SeriesAdapter(Context context) {
        this.context = context;
    }

    public void addSeriesToList(List<Serie> seriesList) {
        this.seriesList.addAll(seriesList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SeriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.serie_item, parent, false);
        return new SeriesViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull SeriesViewHolder holder, int position) {

        Serie serie = seriesList.get(position);
        Glide.with(context).
                load(url + serie.getCover()).
                apply(RequestOptions.centerCropTransform()).
                into(holder.serieImage);
    }

    @Override
    public int getItemCount() {
        int size = 0;
        if (seriesList != null) {
            size = seriesList.size();
        }
        return size;
    }

    public List<Serie> getSeriesList() {
        return seriesList;
    }

    public void cleanList() {
        this.seriesList.clear();
    }

    public class SeriesViewHolder extends RecyclerView.ViewHolder {

        private ImageView serieImage;
        private View serieLayout;

        public SeriesViewHolder(View itemView) {
            super(itemView);
            serieImage = itemView.findViewById(R.id.iv_serie_cover);
            serieLayout = itemView.findViewById(R.id.ly_serie_item);

            serieLayout.setOnClickListener(view -> {
                savePostion();
                Serie serie = seriesList.get(position);
                listener.onSerieClickedListener(serie);
            });
        }

        public void savePostion() {
            position = getBindingAdapterPosition();
        }
    }

    public interface OnSerieClickedListener {
        void onSerieClickedListener(Serie serie);
    }
}
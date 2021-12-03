package com.example.homework44.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homework44.data.network.dtos.episode.Episodes;
import com.example.homework44.databinding.EpisodesItemsBinding;
import com.example.homework44.utils.OnItemClickEpisode;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.ViewHolder> {
    private ArrayList<Episodes> list = new ArrayList<>();
    OnItemClickEpisode onItemClickEpisode;

    public void addEpisodes(ArrayList<Episodes> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void onCLick(OnItemClickEpisode onItemClickEpisode) {
        this.onItemClickEpisode = onItemClickEpisode;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ViewHolder(EpisodesItemsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.onFill(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        EpisodesItemsBinding binding;

        public ViewHolder(@NonNull @NotNull EpisodesItemsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void onFill(Episodes s) {
            binding.txtName.setText(s.getName());
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickEpisode.onClick(s.getId());
                }
            });

        }
    }
}

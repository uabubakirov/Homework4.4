package com.example.homework44.ui.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homework44.data.network.dtos.episode.Episodes;
import com.example.homework44.databinding.EpisodesItemsBinding;
import com.example.homework44.utils.OnItemClickEpisode;

import org.jetbrains.annotations.NotNull;

public class EpisodeAdapter extends ListAdapter<Episodes,EpisodeAdapter.ViewHolder> {

    OnItemClickEpisode onItemClickEpisode;

    public EpisodeAdapter() {
        super(new EpisodeComparator());
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
        holder.onFill(getItem(position));
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
                    onItemClickEpisode.onClick(s.getId(),s.getName());
                }
            });

        }
    }
    public static class EpisodeComparator extends DiffUtil.ItemCallback<Episodes>{

        @Override
        public boolean areItemsTheSame(@NonNull @NotNull Episodes oldItem, @NonNull @NotNull Episodes newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull @NotNull Episodes oldItem, @NonNull @NotNull Episodes newItem) {
            return oldItem == newItem;
        }
    }}

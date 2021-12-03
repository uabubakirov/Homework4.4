package com.example.homework44.ui.adapters;

import android.annotation.SuppressLint;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homework44.data.network.dtos.episode.Episodes;
import com.example.homework44.data.network.dtos.location.Locations;
import com.example.homework44.databinding.LocationItemsBinding;
import com.example.homework44.utils.OnItemClickLocation;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class LocationAdapter extends ListAdapter<Locations, LocationAdapter.ViewHolder> {


    private OnItemClickLocation onItemClickLocation;

    public LocationAdapter() {
        super(new LocationComparator());
    }


    public void onCLick(OnItemClickLocation onItemClickLocation){
        this.onItemClickLocation=onItemClickLocation;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ViewHolder(LocationItemsBinding.inflate(LayoutInflater.
                from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.onFill(getItem(position));
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        LocationItemsBinding binding;
        public ViewHolder(LocationItemsBinding binding) {
            super(binding.getRoot());
            this.binding= binding;
        }
        private void onFill (Locations s){
            binding.txtName.setText(s.getName());
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickLocation.onClick(s.getId());
                }
            });

        }
    }
    public static class LocationComparator extends DiffUtil.ItemCallback<Locations> {

        @Override
        public boolean areItemsTheSame(@NonNull @NotNull Locations oldItem, @NonNull @NotNull Locations newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull @NotNull Locations oldItem, @NonNull @NotNull Locations newItem) {
            return oldItem == newItem;
        }
    }
}

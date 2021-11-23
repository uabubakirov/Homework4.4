package com.example.homework44.ui.adapters;

import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homework44.data.network.dtos.location.Locations;
import com.example.homework44.databinding.LocationItemsBinding;
import com.example.homework44.utils.OnItemClickLocation;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {

    private ArrayList<Locations> list = new ArrayList<>();
    private OnItemClickLocation onItemClickLocation;
    public void addLocations(ArrayList<Locations> locations){
        this.list.addAll(locations);
        notifyDataSetChanged();
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
        holder.onFill(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
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
}

package com.example.homework44.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.homework44.databinding.CharactersItemsBinding;
import com.example.homework44.data.network.dtos.character.Characters;
import com.example.homework44.utils.OnItemClickCharacter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.ViewHolder> {
    private ArrayList<Characters> list = new ArrayList<>();
    OnItemClickCharacter onItemClickCharacter;


    public void addData(ArrayList<Characters> characters){
        this.list.addAll(characters);
        notifyDataSetChanged();
    }
    public void onCLick(OnItemClickCharacter onItemClickCharacter){
        this.onItemClickCharacter = onItemClickCharacter;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ViewHolder(CharactersItemsBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
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
        CharactersItemsBinding binding;
        public ViewHolder(@NonNull @NotNull CharactersItemsBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
        public void onFill(Characters s){
            binding.txtName.setText(s.getName());
            Glide.with(binding.imgImage).load(s.getImage()).into(binding.imgImage);
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickCharacter.onCLick(s.getId());
                }
            });

        }
    }
}

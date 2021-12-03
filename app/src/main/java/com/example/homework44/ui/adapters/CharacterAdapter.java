package com.example.homework44.ui.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.homework44.databinding.CharactersItemsBinding;
import com.example.homework44.data.network.dtos.character.Characters;
import com.example.homework44.utils.OnItemClickCharacter;
import com.example.homework44.utils.OnLongItemClick;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CharacterAdapter extends ListAdapter<Characters, CharacterAdapter.ViewHolder> {

    OnItemClickCharacter onItemClickCharacter;
    OnLongItemClick onLongItemClick;

    public CharacterAdapter() {
        super(new CharactersComparator());
    }
    public void onCLick(OnItemClickCharacter onItemClickCharacter) {
        this.onItemClickCharacter = onItemClickCharacter;
    }

    public void onLongClick(OnLongItemClick onLongItemClick) {
        this.onLongItemClick = onLongItemClick;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ViewHolder(CharactersItemsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.onFill(getItem(position));
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        CharactersItemsBinding binding;

        public ViewHolder(@NonNull @NotNull CharactersItemsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onFill(Characters s) {
            binding.txtName.setText(s.getName());
            Glide.with(binding.imgImage).load(s.getImage()).into(binding.imgImage);
            binding.getRoot().setOnClickListener(v -> {
                onItemClickCharacter.onCLick(s.getId(), s.getName());
            });
            binding.getRoot().setOnLongClickListener(v -> {
                onLongItemClick.onLongClick(s.getId());
                return true;
            });
        }
    }
    public static class CharactersComparator extends DiffUtil.ItemCallback<Characters>{
        @Override
        public boolean areItemsTheSame(@NonNull @NotNull Characters oldItem, @NonNull @NotNull Characters newItem) {
            return oldItem.getId() == newItem.getId();
        }


        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull @NotNull Characters oldItem, @NonNull @NotNull Characters newItem) {
            return oldItem.equals(newItem);
        }
    }


}

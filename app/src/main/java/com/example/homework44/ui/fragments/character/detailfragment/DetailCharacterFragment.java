package com.example.homework44.ui.fragments.character.detailfragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.homework44.R;
import com.example.homework44.data.network.dtos.character.Characters;
import com.example.homework44.databinding.FragmentCharacterBinding;
import com.example.homework44.databinding.FragmentDetailCharacterBinding;
import com.example.homework44.ui.fragments.base.BaseFragment;
import com.example.homework44.ui.fragments.character.CharacterViewModel;
import com.example.homework44.utils.Keys;

import org.jetbrains.annotations.NotNull;


public class DetailCharacterFragment extends BaseFragment<CharacterViewModel, FragmentDetailCharacterBinding> {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailCharacterBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }


    @Override
    protected void initialize() {
        viewModel = new ViewModelProvider(this).get(CharacterViewModel.class);

    }

    @Override
    protected void setupRequests() {
        viewModel.fetchCharacter(getArguments().getInt(Keys.KEY_ID)).observe(getViewLifecycleOwner(), new Observer<Characters>() {
            @Override
            public void onChanged(Characters characters) {
                binding.txtName.setText(characters.getName());
                Glide.with(binding.imgImage).load(characters.getImage()).into(binding.imgImage);
                binding.txtGender.setText(characters.getGender());
                binding.txtSpecies.setText(characters.getSpecies());
                binding.txtStatus.setText(characters.getStatus());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
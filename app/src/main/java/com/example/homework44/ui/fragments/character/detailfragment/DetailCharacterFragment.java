package com.example.homework44.ui.fragments.character.detailfragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.homework44.hilt.data.network.dtos.character.Characters;
import com.example.homework44.databinding.FragmentDetailCharacterBinding;
import com.example.homework44.base.BaseFragment;
import com.example.homework44.ui.fragments.character.CharacterViewModel;
import com.example.homework44.utils.Toasts;

import org.jetbrains.annotations.NotNull;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
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
        if (internetCheck(getContext())){
        viewModel.fetchCharacter(DetailCharacterFragmentArgs.fromBundle(getArguments()).getId()).observe(getViewLifecycleOwner(), new Observer<Characters>() {
            @Override
            public void onChanged(Characters characters) {
                binding.txtName.setText(characters.getName());
                Glide.with(binding.imgImage).load(characters.getImage()).into(binding.imgImage);
                binding.txtGender.setText(characters.getGender());
                binding.txtSpecies.setText(characters.getSpecies());
                binding.txtStatus.setText(characters.getStatus());
            }
        });
        }else {
            Toast.makeText(getContext(), Toasts.NO_INTERNET_DF,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
package com.example.homework44.ui.fragments.location.detailfragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.homework44.R;
import com.example.homework44.data.network.dtos.location.Locations;
import com.example.homework44.databinding.FragmentLocationDetailBinding;
import com.example.homework44.ui.fragments.base.BaseFragment;
import com.example.homework44.ui.fragments.location.LocationViewModel;
import com.example.homework44.utils.Keys;


public class LocationDetailFragment extends BaseFragment<LocationViewModel, FragmentLocationDetailBinding> {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentLocationDetailBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    protected void setupObservers() {
        viewModel.fetchLocation(getArguments().getInt(Keys.KEY_ID)).observe(getViewLifecycleOwner(), new Observer<Locations>() {
            @Override
            public void onChanged(Locations locations) {
                binding.txtName.setText(locations.getName());
                binding.txtDimension.setText(locations.getDimension());
                binding.txtType.setText(locations.getType());
            }
        });
    }

    @Override
    protected void initialize() {
        viewModel = new ViewModelProvider(this).get(LocationViewModel.class);
    }
}
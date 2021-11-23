package com.example.homework44.ui.fragments.location;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.homework44.R;
import com.example.homework44.data.network.dtos.location.Locations;
import com.example.homework44.databinding.FragmentLocationBinding;
import com.example.homework44.ui.adapters.LocationAdapter;
import com.example.homework44.ui.fragments.base.BaseFragment;
import com.example.homework44.utils.Keys;
import com.example.homework44.utils.OnItemClickLocation;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class LocationFragment extends BaseFragment<LocationViewModel, FragmentLocationBinding> {

    LocationAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLocationBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    protected void setupListeners() {
        adapter.onCLick(new OnItemClickLocation() {
            @Override
            public void onClick(int id) {
                Bundle bundle = new Bundle();
                bundle.putInt(Keys.KEY_ID,id);
                Navigation.findNavController(requireView()).navigate(R.id.locationDetailFragment,bundle);
            }
        });
    }

    @Override
    protected void setupObservers() {
        viewModel.fetchLocations().observe(getViewLifecycleOwner(), new Observer<ArrayList<Locations>>() {
            @Override
            public void onChanged(ArrayList<Locations> locations) {
                adapter.addLocations(locations);
            }
        });
        binding.rvLocation.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull @NotNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!recyclerView.canScrollVertically(1) && dy > 0){
                    binding.progressbar.setVisibility(View.VISIBLE);
                    viewModel.page++;
                    viewModel.fetchLocations().observe(getViewLifecycleOwner(), new Observer<ArrayList<Locations>>() {
                        @Override
                        public void onChanged(ArrayList<Locations> locations) {
                            adapter.addLocations(locations);
                            binding.progressbar.setVisibility(View.GONE);
                        }
                    });
                }
            }
        });
    }
    @Override
    protected void initialize() {
        viewModel = new ViewModelProvider(this).get(LocationViewModel.class);
        adapter = new LocationAdapter();
        binding.rvLocation.setAdapter(adapter);
        binding.rvLocation.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

}
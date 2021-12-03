package com.example.homework44.ui.fragments.episode.detailfragment;

import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.homework44.data.network.dtos.episode.Episodes;
import com.example.homework44.databinding.FragmentEpisodeDetailBinding;
import com.example.homework44.base.BaseFragment;
import com.example.homework44.ui.fragments.episode.EpisodeViewModel;
import com.example.homework44.utils.Toasts;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class EpisodeDetailFragment extends BaseFragment<EpisodeViewModel, FragmentEpisodeDetailBinding> {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEpisodeDetailBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    protected void initialize() {
        viewModel=new ViewModelProvider(this).get(EpisodeViewModel.class);

    }

    @Override
    protected void setupObservers() {
        if (internetCheck(getContext())){
        viewModel.fetchEpisode(EpisodeDetailFragmentArgs.fromBundle(getArguments()).getId()).observe(getViewLifecycleOwner(), new Observer<Episodes>() {
            @Override
            public void onChanged(Episodes episodes) {
                binding.txtName.setText(episodes.getName());
                binding.txtAirdate.setText(episodes.getAirDate());
                binding.txtEpisode.setText(episodes.getEpisode());
            }
        });}else {
            Toast.makeText(getContext(), Toasts.NO_INTERNET_DF,Toast.LENGTH_SHORT).show();
        }
    }
}
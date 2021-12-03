package com.example.homework44.ui.fragments.episode;

import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.homework44.data.network.dtos.episode.Episodes;
import com.example.homework44.data.repositories.EpisodeRepositories;
import com.example.homework44.databinding.FragmentEpisodeBinding;
import com.example.homework44.ui.adapters.EpisodeAdapter;
import com.example.homework44.base.BaseFragment;
import com.example.homework44.utils.OnItemClickEpisode;
import com.example.homework44.utils.Toasts;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class EpisodeFragment extends BaseFragment<EpisodeViewModel, FragmentEpisodeBinding> {

    EpisodeAdapter adapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEpisodeBinding.inflate(inflater,container,false);
        return binding.getRoot();

    }

    @Override
    protected void setupListeners() {
        adapter.onCLick(new OnItemClickEpisode() {
            @Override
            public void onClick(int id) {
                Navigation.findNavController(requireView()).navigate(EpisodeFragmentDirections.actionEpisodesNavigationToEpisodeDetailFragment(id));
            }
        });
    }

    @Override
    protected void initialize() {
        viewModel = new ViewModelProvider(this).get(EpisodeViewModel.class);
        adapter = new EpisodeAdapter();
        binding.rvEpisodes.setAdapter(adapter);
        binding.rvEpisodes.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void setupObservers() {
        if (internetCheck(getContext())){
        binding.progressbarItems.setVisibility(View.VISIBLE);
        viewModel.fetchEpisodes().observe(getViewLifecycleOwner(), new Observer<ArrayList<Episodes>>() {
            @Override
            public void onChanged(ArrayList<Episodes> episodes) {
                adapter.submitList(episodes);
                binding.progressbarItems.setVisibility(View.GONE);
                binding.rvEpisodes.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        if (!recyclerView.canScrollVertically(1) && dy > 0) {
                            binding.progressbar.setVisibility(View.VISIBLE);
                            viewModel.page++;
                            viewModel.fetchEpisodes().observe(getViewLifecycleOwner(), new Observer<ArrayList<Episodes>>() {
                                @Override
                                public void onChanged(ArrayList<Episodes> list) {
                                    ArrayList arrayList = new ArrayList(adapter.getCurrentList());
                                    arrayList.addAll(episodes);
                                    adapter.submitList(arrayList);
                                    binding.progressbar.setVisibility(View.GONE);

                                }
                            });
                        }
                    }
                });
            }
        });}else {
            Toast.makeText(getContext(), Toasts.NO_INTERNET,Toast.LENGTH_SHORT).show();
            adapter.submitList(viewModel.getDataFromDb());
        }




    }
}
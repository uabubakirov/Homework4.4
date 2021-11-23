package com.example.homework44.ui.fragments.episode;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.homework44.R;
import com.example.homework44.data.network.dtos.character.Characters;
import com.example.homework44.data.network.dtos.episode.Episodes;
import com.example.homework44.databinding.FragmentEpisodeBinding;
import com.example.homework44.ui.adapters.EpisodeAdapter;
import com.example.homework44.ui.fragments.base.BaseFragment;
import com.example.homework44.utils.Keys;
import com.example.homework44.utils.OnItemClickEpisode;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

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
                Bundle bundle = new Bundle();
                bundle.putInt(Keys.KEY_ID,id);
                Navigation.findNavController(requireView()).navigate(R.id.episodeDetailFragment,bundle);
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
        viewModel.fetchEpisodes().observe(getViewLifecycleOwner(), new Observer<ArrayList<Episodes>>() {
            @Override
            public void onChanged(ArrayList<Episodes> episodes) {
                adapter.addEpisodes(episodes);

            }
        });
        binding.rvEpisodes.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (!recyclerView.canScrollVertically(1) && dy > 0) {
                    binding.progressbar.setVisibility(View.VISIBLE);
                    viewModel.page++;
                    viewModel.fetchEpisodes().observe(getViewLifecycleOwner(), new Observer<ArrayList<Episodes>>() {
                        @Override
                        public void onChanged(ArrayList<Episodes> list) {
                            adapter.addEpisodes(list);
                            binding.progressbar.setVisibility(View.GONE);

                        }
                    });
                }
            }
        });

    }
}
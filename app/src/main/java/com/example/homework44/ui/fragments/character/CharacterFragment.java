package com.example.homework44.ui.fragments.character;

import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.homework44.App;
import com.example.homework44.data.network.dtos.character.Characters;
import com.example.homework44.data.repositories.CharacterRepositories;
import com.example.homework44.databinding.FragmentCharacterBinding;
import com.example.homework44.ui.adapters.CharacterAdapter;
import com.example.homework44.base.BaseFragment;
import com.example.homework44.utils.OnItemClickCharacter;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CharacterFragment extends BaseFragment<CharacterViewModel, FragmentCharacterBinding> {

    CharacterAdapter adapter;
    private LinearLayoutManager charactersLayoutManager;
    CharacterRepositories characterRepositories;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCharacterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    protected void initialize() {
        viewModel = new ViewModelProvider(this).get(CharacterViewModel.class);
        adapter = new CharacterAdapter();
        binding.rvCharacters.setAdapter(adapter);
        charactersLayoutManager = new LinearLayoutManager(requireContext());
        binding.rvCharacters.setLayoutManager(charactersLayoutManager);
    }


    @Override
    protected void setupListeners() {
        adapter.onCLick((id, name) -> {
            Navigation.findNavController(requireView()).navigate(CharacterFragmentDirections.actionCharacterNavigationToDetailCharacterFragment(id,name));
            viewModel.page = 1;
        });
        adapter.onLongClick(id -> {
            Navigation.findNavController(requireView()).navigate(CharacterFragmentDirections.actionCharacterNavigationToImageFragment(id));
        });

    }

    @Override
    protected void setupRequests() {
        binding.progressbarItems.setVisibility(View.VISIBLE);
        viewModel.fetchCharacters().observe(getViewLifecycleOwner(), new Observer<ArrayList<Characters>>() {
            @Override
            public void onChanged(ArrayList<Characters> characters) {
                adapter.addData(characters);
                binding.progressbarItems.setVisibility(View.GONE);
            }
        });
        binding.rvCharacters.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (!recyclerView.canScrollVertically(1) && dy > 0) {
                    binding.progressbar.setVisibility(View.VISIBLE);
                    viewModel.page++;
                    viewModel.fetchCharacters().observe(getViewLifecycleOwner(), new Observer<ArrayList<Characters>>() {
                        @Override
                        public void onChanged(ArrayList<Characters> characters) {
                            adapter.addData(characters);
                            binding.progressbar.setVisibility(View.GONE);
                        }
                    });


                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

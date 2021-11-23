package com.example.homework44.ui.fragments.character;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.homework44.R;
import com.example.homework44.data.network.dtos.character.Characters;
import com.example.homework44.databinding.FragmentCharacterBinding;
import com.example.homework44.ui.adapters.CharacterAdapter;
import com.example.homework44.ui.fragments.base.BaseFragment;
import com.example.homework44.utils.Keys;
import com.example.homework44.utils.OnItemClickCharacter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CharacterFragment extends BaseFragment<CharacterViewModel, FragmentCharacterBinding>{

    CharacterAdapter adapter;
    private LinearLayoutManager charactersLayoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCharacterBinding.inflate(inflater,container,false);
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
      adapter.onCLick(id -> {
          Bundle bundle = new Bundle();
          bundle.putInt(Keys.KEY_ID,id);
          Navigation.findNavController(requireView()).navigate(R.id.detailCharacterFragment,bundle);
      });

    }
    @Override
    protected void setupRequests() {
        viewModel.fetchCharacters().observe(getViewLifecycleOwner(), new Observer<ArrayList<Characters>>() {
            @Override
            public void onChanged(ArrayList<Characters> characters) {
                adapter.addData(characters);
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

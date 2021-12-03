package com.example.homework44.ui.fragments.character;

import androidx.lifecycle.LiveData;

import com.example.homework44.data.network.dtos.character.Characters;
import com.example.homework44.data.repositories.CharacterRepositories;
import com.example.homework44.base.BaseViewModel;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class CharacterViewModel extends BaseViewModel {
   private final CharacterRepositories repositories;
   int page = 1;
   int idCharacter;
    @Inject
    public CharacterViewModel(CharacterRepositories repositories) {
        this.repositories = repositories;
    }

    public LiveData<ArrayList<Characters>> fetchCharacters(){
        return repositories.fetchCharacters(page);
    }

    public LiveData<Characters> fetchCharacter(int id){
        return repositories.fetchCharacter(id);
    }

}

package com.example.homework44.ui.fragments.character;

import androidx.lifecycle.LiveData;

import com.example.homework44.hilt.data.local.daos.CharacterDao;
import com.example.homework44.hilt.data.network.dtos.character.Characters;
import com.example.homework44.hilt.data.repositories.CharacterRepositories;
import com.example.homework44.base.BaseViewModel;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class CharacterViewModel extends BaseViewModel {
   private final CharacterRepositories repositories;
   private final CharacterDao characterDao;
   int page = 1;
   int idCharacter;
    @Inject
    public CharacterViewModel(CharacterRepositories repositories, CharacterDao characterDao) {
        this.repositories = repositories;
        this.characterDao = characterDao;
    }

    public LiveData<ArrayList<Characters>> fetchCharacters(){
        return repositories.fetchCharacters(page);
    }
    public ArrayList<Characters> getDataFromDb(){
       return (ArrayList<Characters>) characterDao.getAll();
    }

    public LiveData<Characters> fetchCharacter(int id){
        return repositories.fetchCharacter(id);
    }

}

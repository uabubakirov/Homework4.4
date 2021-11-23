package com.example.homework44.ui.fragments.character;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.homework44.App;
import com.example.homework44.data.network.dtos.character.Characters;
import com.example.homework44.data.repositories.CharacterRepositories;
import com.example.homework44.ui.fragments.base.BaseViewModel;
import com.example.homework44.ui.fragments.character.detailfragment.DetailCharacterFragment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharacterViewModel extends BaseViewModel {
    CharacterRepositories characterRepositories = new CharacterRepositories();
    int page = 1;
    int idCharacter;

    public LiveData<ArrayList<Characters>> fetchCharacters(){
        return characterRepositories.fetchCharacters(page);
    }

    public LiveData<Characters> fetchCharacter(int id){
        return characterRepositories.fetchCharacter(id);
    }

}

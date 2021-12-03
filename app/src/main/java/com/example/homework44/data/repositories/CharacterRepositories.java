package com.example.homework44.data.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.homework44.App;
import com.example.homework44.data.local.daos.CharacterDao;
import com.example.homework44.data.network.apiservice.CharacterApi;
import com.example.homework44.data.network.dtos.RickAndMortyResponse;
import com.example.homework44.data.network.dtos.character.Characters;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharacterRepositories {
    private final CharacterApi characterApi;
    private final CharacterDao characterDao;


    @Inject
    public CharacterRepositories(CharacterApi characterApi, CharacterDao characterDao) {
        this.characterApi = characterApi;
        this.characterDao = characterDao;
    }

    public LiveData<ArrayList<Characters>> fetchCharacters(int page) {
        MutableLiveData<ArrayList<Characters>> data = new MutableLiveData();
        characterApi.fetchCharacters(page).enqueue(new Callback<RickAndMortyResponse<Characters>>() {
            @Override
            public void onResponse(Call<RickAndMortyResponse<Characters>> call, Response<RickAndMortyResponse<Characters>> response) {
                ArrayList<Characters> characters = new ArrayList<>();
                characters = response.body().getResults();
                characterDao.insertAll(characters);
                data.setValue(characters);
            }

            @Override
            public void onFailure(Call<RickAndMortyResponse<Characters>> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
    public LiveData<Characters> fetchCharacter(int id){
        MutableLiveData<Characters> data = new MutableLiveData<>();
        characterApi.fetchCharacter(id).enqueue(new Callback<Characters>() {
            @Override
            public void onResponse(Call<Characters> call, Response<Characters> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Characters> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}





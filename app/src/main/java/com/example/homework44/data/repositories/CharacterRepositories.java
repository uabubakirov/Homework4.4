package com.example.homework44.data.repositories;

import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.homework44.App;
import com.example.homework44.data.network.apiservice.CharacterApi;
import com.example.homework44.data.network.dtos.RickAndMortyResponse;
import com.example.homework44.data.network.dtos.character.Characters;
import com.example.homework44.ui.fragments.character.CharacterViewModel;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharacterRepositories {
    private final CharacterApi characterApi;

    @Inject
    public CharacterRepositories(CharacterApi characterApi) {
        this.characterApi = characterApi;
    }
    public LiveData<ArrayList<Characters>> fetchCharacters(int page) {
        MutableLiveData<ArrayList<Characters>> data = new MutableLiveData();
        characterApi.fetchCharacters(page).enqueue(new Callback<RickAndMortyResponse<Characters>>() {
            @Override
            public void onResponse(Call<RickAndMortyResponse<Characters>> call, Response<RickAndMortyResponse<Characters>> response) {
                data.setValue(response.body().getResults());
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





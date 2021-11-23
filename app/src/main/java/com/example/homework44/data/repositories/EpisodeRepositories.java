package com.example.homework44.data.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.homework44.App;
import com.example.homework44.data.network.dtos.RickAndMortyResponse;
import com.example.homework44.data.network.dtos.episode.Episodes;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EpisodeRepositories {
    public LiveData<ArrayList<Episodes>> fetchEpisodes(int page){
        MutableLiveData<ArrayList<Episodes>> data = new MutableLiveData<>();
        App.episodeApi.fetchEpisodes(page).enqueue(new Callback<RickAndMortyResponse<Episodes>>() {
            @Override
            public void onResponse(Call<RickAndMortyResponse<Episodes>> call, Response<RickAndMortyResponse<Episodes>> response) {
                if (response.body()!=null) {
                    data.setValue(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<RickAndMortyResponse<Episodes>> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
    public LiveData<Episodes> fetchEpisode(int id){
        MutableLiveData<Episodes> data = new MutableLiveData<>();
        App.episodeApi.fetchEpisode(id).enqueue(new Callback<Episodes>() {
            @Override
            public void onResponse(Call<Episodes> call, Response<Episodes> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Episodes> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}

package com.example.homework44.hilt.data.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.homework44.hilt.data.local.daos.EpisodeDao;
import com.example.homework44.hilt.data.network.apiservice.EpisodeApi;
import com.example.homework44.hilt.data.network.dtos.RickAndMortyResponse;
import com.example.homework44.hilt.data.network.dtos.episode.Episodes;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EpisodeRepositories {
    private final EpisodeApi episodeApi;
    private final EpisodeDao episodeDao;
    @Inject
    public EpisodeRepositories(EpisodeApi episodeApi, EpisodeDao episodeDao) {
        this.episodeApi = episodeApi;
        this.episodeDao = episodeDao;
    }


    public LiveData<ArrayList<Episodes>> fetchEpisodes(int page) {
        MutableLiveData<ArrayList<Episodes>> data = new MutableLiveData<>();

        episodeApi.fetchEpisodes(page).enqueue(new Callback<RickAndMortyResponse<Episodes>>() {
            @Override
            public void onResponse(Call<RickAndMortyResponse<Episodes>> call, Response<RickAndMortyResponse<Episodes>> response) {
                if (response.body() != null) {
                    ArrayList<Episodes> episodes = new ArrayList<>();
                    episodes = response.body().getResults();
                    episodeDao.insertAll(episodes);
                    data.setValue(episodes);
                }
            }

            @Override
            public void onFailure(Call<RickAndMortyResponse<Episodes>> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public LiveData<Episodes> fetchEpisode(int id) {
        MutableLiveData<Episodes> data = new MutableLiveData<>();
        episodeApi.fetchEpisode(id).enqueue(new Callback<Episodes>() {
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

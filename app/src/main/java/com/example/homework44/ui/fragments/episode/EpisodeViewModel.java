package com.example.homework44.ui.fragments.episode;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.homework44.data.network.dtos.episode.Episodes;
import com.example.homework44.data.repositories.EpisodeRepositories;
import com.example.homework44.ui.fragments.base.BaseViewModel;

import java.util.ArrayList;

public class EpisodeViewModel extends BaseViewModel {
    int page = 1;

    EpisodeRepositories episodeRepositories = new EpisodeRepositories();

    public LiveData<ArrayList<Episodes>> fetchEpisodes(){
       return episodeRepositories.fetchEpisodes(page);
    }

    public LiveData<Episodes> fetchEpisode(int id){
        return episodeRepositories.fetchEpisode(id);
    }
}

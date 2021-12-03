package com.example.homework44.ui.fragments.episode;

import androidx.lifecycle.LiveData;

import com.example.homework44.data.network.dtos.episode.Episodes;
import com.example.homework44.data.repositories.EpisodeRepositories;
import com.example.homework44.base.BaseViewModel;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class EpisodeViewModel extends BaseViewModel {
    private final EpisodeRepositories episodeRepositories;
    int page = 1;
    @Inject
    public EpisodeViewModel(EpisodeRepositories episodeRepositories) {
        this.episodeRepositories = episodeRepositories;
    }


    public LiveData<ArrayList<Episodes>> fetchEpisodes(){
       return episodeRepositories.fetchEpisodes(page);
    }

    public LiveData<Episodes> fetchEpisode(int id){
        return episodeRepositories.fetchEpisode(id);
    }
}

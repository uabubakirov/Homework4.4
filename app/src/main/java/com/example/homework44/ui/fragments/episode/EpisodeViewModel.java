package com.example.homework44.ui.fragments.episode;

import androidx.lifecycle.LiveData;

import com.example.homework44.hilt.data.local.daos.EpisodeDao;
import com.example.homework44.hilt.data.network.dtos.episode.Episodes;
import com.example.homework44.hilt.data.repositories.EpisodeRepositories;
import com.example.homework44.base.BaseViewModel;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class EpisodeViewModel extends BaseViewModel {
    private final EpisodeRepositories episodeRepositories;
    private final EpisodeDao episodeDao;
    int page = 1;
    @Inject
    public EpisodeViewModel(EpisodeRepositories episodeRepositories, EpisodeDao episodeDao) {
        this.episodeRepositories = episodeRepositories;
        this.episodeDao = episodeDao;
    }
    public ArrayList<Episodes> getDataFromDb(){
        return (ArrayList<Episodes>) episodeDao.getAll();
    }

    public LiveData<ArrayList<Episodes>> fetchEpisodes(){
       return episodeRepositories.fetchEpisodes(page);
    }

    public LiveData<Episodes> fetchEpisode(int id){
        return episodeRepositories.fetchEpisode(id);
    }
}

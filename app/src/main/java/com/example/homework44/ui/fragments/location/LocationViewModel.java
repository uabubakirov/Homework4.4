package com.example.homework44.ui.fragments.location;

import androidx.lifecycle.LiveData;

import com.example.homework44.hilt.data.local.daos.LocationDao;
import com.example.homework44.hilt.data.network.dtos.location.Locations;
import com.example.homework44.hilt.data.repositories.LocationRepositories;
import com.example.homework44.base.BaseViewModel;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class LocationViewModel extends BaseViewModel {
    private final LocationRepositories locationRepositories;
    private final LocationDao locationDao;
    int page = 1;
    @Inject
    public LocationViewModel(LocationRepositories locationRepositories, LocationDao locationDao) {
        this.locationRepositories = locationRepositories;
        this.locationDao = locationDao;
    }

    public LiveData<ArrayList<Locations>> fetchLocations(){
        return locationRepositories.fetchLocations(page);
    }
    public ArrayList<Locations> getDataFromDb(){
        return (ArrayList<Locations>) locationDao.getAll();
    }
    public LiveData<Locations> fetchLocation(int id){
        return locationRepositories.fetchLocation(id);
    }
}

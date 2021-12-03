package com.example.homework44.ui.fragments.location;

import androidx.lifecycle.LiveData;

import com.example.homework44.data.network.dtos.location.Locations;
import com.example.homework44.data.repositories.LocationRepositories;
import com.example.homework44.base.BaseViewModel;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class LocationViewModel extends BaseViewModel {
    private final LocationRepositories locationRepositories;
    int page = 1;
    @Inject
    public LocationViewModel(LocationRepositories locationRepositories) {
        this.locationRepositories = locationRepositories;
    }

    public LiveData<ArrayList<Locations>> fetchLocations(){
        return locationRepositories.fetchLocations(page);
    }

    public LiveData<Locations> fetchLocation(int id){
        return locationRepositories.fetchLocation(id);
    }
}

package com.example.homework44.ui.fragments.location;

import android.location.Location;

import androidx.lifecycle.LiveData;

import com.example.homework44.data.network.dtos.location.Locations;
import com.example.homework44.data.repositories.LocationRepositories;
import com.example.homework44.ui.fragments.base.BaseViewModel;

import java.util.ArrayList;

public class LocationViewModel extends BaseViewModel {
    int page = 1;
    LocationRepositories locationRepositories = new LocationRepositories();

    public LiveData<ArrayList<Locations>> fetchLocations(){
        return locationRepositories.fetchLocations(page);
    }

    public LiveData<Locations> fetchLocation(int id){
        return locationRepositories.fetchLocation(id);
    }
}

package com.example.homework44.data.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.homework44.data.local.daos.LocationDao;
import com.example.homework44.data.network.apiservice.LocationApi;
import com.example.homework44.data.network.dtos.RickAndMortyResponse;
import com.example.homework44.data.network.dtos.location.Locations;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationRepositories {
    private final LocationApi locationApi;
    private final LocationDao locationDao;
    @Inject
    public LocationRepositories(LocationApi locationApi, LocationDao locationDao) {
        this.locationApi = locationApi;
        this.locationDao = locationDao;
    }

    public LiveData<ArrayList<Locations>> fetchLocations(int page){
        MutableLiveData<ArrayList<Locations>> data = new MutableLiveData<>();
        locationApi.fetchLocations(page).enqueue(new Callback<RickAndMortyResponse<Locations>>() {
            @Override
            public void onResponse(Call<RickAndMortyResponse<Locations>> call, Response<RickAndMortyResponse<Locations>> response) {
                if (response.body()!=null) {
                    ArrayList<Locations> locations = new ArrayList<>();
                    locations = response.body().getResults();
                    locationDao.insertAll(locations);
                    data.setValue(locations);
                }

            }

            @Override
            public void onFailure(Call<RickAndMortyResponse<Locations>> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
    public LiveData<Locations> fetchLocation(int id){
        MutableLiveData<Locations> data = new MutableLiveData<>();
        locationApi.fetchLocation(id).enqueue(new Callback<Locations>() {
            @Override
            public void onResponse(Call<Locations> call, Response<Locations> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Locations> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}

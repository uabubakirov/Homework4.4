package com.example.homework44.data.network.apiservice;

import android.location.Location;

import com.example.homework44.data.network.dtos.RickAndMortyResponse;
import com.example.homework44.data.network.dtos.episode.Episodes;
import com.example.homework44.data.network.dtos.location.Locations;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LocationApi {
    @GET("/api/location")
    public Call<RickAndMortyResponse<Locations>> fetchLocations(@Query("page")int page);

    @GET("/api/location/{id}")
    public Call<Locations> fetchLocation(@Path("id") int id);
}

package com.example.homework44.data.network.apiservice;

import com.example.homework44.data.network.dtos.RickAndMortyResponse;
import com.example.homework44.data.network.dtos.episode.Episodes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EpisodeApi {
    @GET("/api/episode")
    public Call<RickAndMortyResponse<Episodes>> fetchEpisodes(@Query("page")int page);
    @GET("/api/episode/{id}")
    public Call<Episodes> fetchEpisode(@Path("id") int id);
}

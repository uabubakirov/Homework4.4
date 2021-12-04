package com.example.homework44.hilt.data.network.apiservice;

import com.example.homework44.hilt.data.network.dtos.RickAndMortyResponse;
import com.example.homework44.hilt.data.network.dtos.character.Characters;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CharacterApi {
    @GET("/api/character")
    public Call<RickAndMortyResponse<Characters>> fetchCharacters(@Query("page")int page);
    @GET("/api/character/{id}")
    public Call<Characters> fetchCharacter(@Path("id") int id);
}

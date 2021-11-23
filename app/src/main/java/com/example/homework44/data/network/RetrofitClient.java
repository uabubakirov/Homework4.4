package com.example.homework44.data.network;

import com.example.homework44.data.network.apiservice.CharacterApi;
import com.example.homework44.data.network.apiservice.EpisodeApi;
import com.example.homework44.data.network.apiservice.LocationApi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(provideLoggingInterceptor())
            .build();

    private HttpLoggingInterceptor provideLoggingInterceptor(){
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build();

    public CharacterApi provideCharacterApiService(){
        return retrofit.create(CharacterApi.class);
    }
    public EpisodeApi provideEpisodeApiService(){
        return retrofit.create(EpisodeApi.class);
    }
    public LocationApi provideLocationApiService(){
        return retrofit.create(LocationApi.class);
    }


}
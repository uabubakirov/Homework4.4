package com.example.homework44.di;

import com.example.homework44.data.network.RetrofitClient;
import com.example.homework44.data.network.apiservice.CharacterApi;
import com.example.homework44.data.network.apiservice.EpisodeApi;
import com.example.homework44.data.network.apiservice.LocationApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {
    @Singleton
    private final RetrofitClient retrofitClient = new RetrofitClient();
    @Provides
    @Singleton
    CharacterApi provideCharacterApi(){
        return retrofitClient.provideCharacterApiService();
    }
    @Provides
    @Singleton
    EpisodeApi provideEpisodeApi(){
        return retrofitClient.provideEpisodeApiService();
    }

    @Provides
    @Singleton
    LocationApi provideLocationApi(){
        return retrofitClient.provideLocationApiService();
    }


}

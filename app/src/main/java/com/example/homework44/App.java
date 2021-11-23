package com.example.homework44;

import android.app.Application;

import com.example.homework44.data.network.RetrofitClient;
import com.example.homework44.data.network.apiservice.CharacterApi;
import com.example.homework44.data.network.apiservice.EpisodeApi;
import com.example.homework44.data.network.apiservice.LocationApi;

public class App extends Application {

    public static CharacterApi characterApi;
    public static EpisodeApi episodeApi;
    public static LocationApi locationApi;

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitClient retrofit = new RetrofitClient();
        characterApi = retrofit.provideCharacterApiService();
        episodeApi = retrofit.provideEpisodeApiService();
        locationApi = retrofit.provideLocationApiService();

    }
}

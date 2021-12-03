package com.example.homework44;

import android.app.Application;

import com.example.homework44.data.local.AppDatabase;
import com.example.homework44.data.local.RoomClient;
import com.example.homework44.data.local.daos.CharacterDao;
import com.example.homework44.data.network.RetrofitClient;
import com.example.homework44.data.network.apiservice.CharacterApi;
import com.example.homework44.data.network.apiservice.EpisodeApi;
import com.example.homework44.data.network.apiservice.LocationApi;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class App extends Application {

}

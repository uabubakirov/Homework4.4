package com.example.homework44;

import android.app.Application;

import com.example.homework44.hilt.data.local.RoomClient;
import com.example.homework44.hilt.data.local.daos.CharacterDao;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class App extends Application {

    public static CharacterDao characterDao;
    @Override
    public void onCreate() {
        super.onCreate();
        RoomClient roomClient = new RoomClient();
        characterDao = roomClient.provideCharacterDao(roomClient.provideDatabase(this));
    }
}

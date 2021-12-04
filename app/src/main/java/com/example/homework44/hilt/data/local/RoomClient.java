package com.example.homework44.hilt.data.local;

import android.content.Context;

import androidx.room.Room;

import com.example.homework44.hilt.data.local.daos.CharacterDao;
import com.example.homework44.hilt.data.local.daos.EpisodeDao;
import com.example.homework44.hilt.data.local.daos.LocationDao;

public class RoomClient {
    public AppDatabase provideDatabase(Context context){
        return Room.databaseBuilder(context,AppDatabase.class,"rick_and_morty")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }
    public CharacterDao provideCharacterDao(AppDatabase database){
        return database.characterDao();
    }
    public LocationDao provideLocationDao(AppDatabase database){
        return database.locationDao();
    }
    public EpisodeDao provideEpisodeDao(AppDatabase database){
        return database.episodeDao();
    }

}

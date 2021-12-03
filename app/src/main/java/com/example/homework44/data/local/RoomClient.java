package com.example.homework44.data.local;

import android.content.Context;

import androidx.room.Room;

import com.example.homework44.data.local.daos.CharacterDao;

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

}

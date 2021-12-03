package com.example.homework44.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.homework44.data.local.daos.CharacterDao;
import com.example.homework44.data.network.dtos.character.Characters;

@Database(entities = {Characters.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CharacterDao characterDao();
}

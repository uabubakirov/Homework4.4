package com.example.homework44.data.local;

import android.location.Location;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.homework44.data.local.daos.CharacterDao;
import com.example.homework44.data.local.daos.EpisodeDao;
import com.example.homework44.data.local.daos.LocationDao;
import com.example.homework44.data.network.dtos.character.Characters;
import com.example.homework44.data.network.dtos.episode.Episodes;
import com.example.homework44.data.network.dtos.location.Locations;

@Database(entities = {Characters.class, Locations.class, Episodes.class},version = 3)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CharacterDao characterDao();
    public abstract LocationDao locationDao();
    public abstract EpisodeDao episodeDao();
}

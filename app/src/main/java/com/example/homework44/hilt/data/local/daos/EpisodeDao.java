package com.example.homework44.hilt.data.local.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.homework44.hilt.data.network.dtos.episode.Episodes;

import java.util.ArrayList;
import java.util.List;
@Dao
public interface EpisodeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(ArrayList<Episodes> episodes);
    @Delete
    void delete(Episodes episodes);
    @Query("SELECT * FROM episodes")
    List<Episodes> getAll();
}

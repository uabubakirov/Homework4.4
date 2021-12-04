package com.example.homework44.data.local.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.homework44.data.network.dtos.location.Locations;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface LocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(ArrayList<Locations> locations);
    @Delete
    void delete(Locations locations);
    @Query("SELECT * FROM locations")
    List<Locations> getAll();

}

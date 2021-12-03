package com.example.homework44.data.local.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.homework44.data.network.dtos.character.Characters;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface CharacterDao {
    @Insert
    void insertAll(ArrayList<Characters> characters);
    @Delete
    void delete(Characters characters);
    @Query("SELECT * FROM characters")
    List<Characters> getAll();

}

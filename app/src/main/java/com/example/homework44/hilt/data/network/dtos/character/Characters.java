package com.example.homework44.hilt.data.network.dtos.character;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
@Entity
public class Characters {
    @SerializedName("id")
    @PrimaryKey
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("image")
    private String image;
    @SerializedName("status")
    private String status;
    @SerializedName("species")
    private String species;
    @SerializedName("gender")
    private String gender;

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

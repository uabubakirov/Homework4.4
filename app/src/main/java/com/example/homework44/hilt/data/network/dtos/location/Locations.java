package com.example.homework44.hilt.data.network.dtos.location;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
@Entity
public class Locations {
    @PrimaryKey
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("type")
    private String type;
    @SerializedName("dimension")
    private String dimension;

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

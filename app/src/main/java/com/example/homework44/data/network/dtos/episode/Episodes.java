package com.example.homework44.data.network.dtos.episode;

import com.google.gson.annotations.SerializedName;

public class Episodes {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("air_date")
    private String airDate;
    @SerializedName("episode")
    private String episode;

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

    public String getAirDate() {
        return airDate;
    }

    public void setAirDate(String airDate) {
        this.airDate = airDate;
    }

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }
}

package com.example.restapiapplication.models;

import nonapi.io.github.classgraph.json.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Playlist {

    @Id
    private String id;
    private String name;
    private String description;
    private int totalFollowers;
    private List<Track> trackList;
    private int totalTrackNumber;
    private String userId;
    private Boolean isPublic;

    public Playlist(){
        this.id = UUID.randomUUID().toString();
        this.name = "My Playlist";
        this.description = "Playlist";
        this.totalFollowers = 0;
        this.trackList = new ArrayList<>();
        this.totalTrackNumber = 0;
        this.userId = null;
        this.isPublic = true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTotalFollowers() {
        return totalFollowers;
    }

    public void setTotalFollowers(int totalFollowers) {
        this.totalFollowers = totalFollowers;
    }

    public List<Track> getTrackList() {
        return trackList;
    }

    public void setTrackList(List<Track> trackList) {
        this.trackList = trackList;
    }

    public int getTotalTrackNumber() {
        return trackList.size();
    }

    public void setTotalTrackNumber(int totalTrackNumber) {
        this.totalTrackNumber = totalTrackNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }
}

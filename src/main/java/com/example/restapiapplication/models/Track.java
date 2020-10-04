package com.example.restapiapplication.models;

import lombok.Getter;
import lombok.Setter;
import nonapi.io.github.classgraph.json.Id;

import java.util.UUID;

@Setter
@Getter
public class Track {

    @Id
    String id;
    String artist;
    String name;
    String genre;

    public Track(){
        this.id = UUID.randomUUID().toString();
        this.name = "Cem Karaca";
        this.artist  ="Bu son olsun";
        this.genre = "Rock";
    }

    public Track(String name, String artist, String genre){
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.artist = artist;
        this.genre = genre;
    }

}

package com.example.restapiapplication.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import nonapi.io.github.classgraph.json.Id;

@Setter
@Getter
@Data
public class Track {

    @Id
    String id;
    String artist;
    String name;
    String genre;
}

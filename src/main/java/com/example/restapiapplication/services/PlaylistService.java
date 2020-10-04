package com.example.restapiapplication.services;

import com.example.restapiapplication.models.Playlist;
import com.example.restapiapplication.models.Track;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PlaylistService {

    List<Playlist> getAll();

    Playlist findById(String playlistId);

    List<Playlist> findByUserId(String userId);

    void create(Playlist playlist);

    void update(String id, Playlist playlist);

    void delete(String id);

    void addTrack(String id, List<Track> tracks);

    void deleteTrack(String id, Track track);
}

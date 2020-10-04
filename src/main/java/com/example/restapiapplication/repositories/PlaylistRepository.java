package com.example.restapiapplication.repositories;

import com.example.restapiapplication.models.Playlist;
import com.example.restapiapplication.models.Track;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaylistRepository {

    List<Playlist> getAll();

    Playlist findById(String playlistId);

    List<Playlist> findByUserId(String userId);

    void create(Playlist playlist);

    void update(String id, Playlist playlist);

    void delete(String id);

    void addTrack(String id, List<Track> tracks);

    void deleteTrack(String id, Track track);
}

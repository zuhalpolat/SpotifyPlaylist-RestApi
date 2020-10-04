package com.example.restapiapplication.services;

import com.example.restapiapplication.models.Playlist;
import com.example.restapiapplication.models.Track;
import com.example.restapiapplication.repositories.PlaylistRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class PlaylistServiceImpl implements PlaylistService {

    @Autowired
    PlaylistRepositoryImpl playlistRepository;

    @Override
    public List<Playlist> getAll() {
        return playlistRepository.getAll();
    }

    @Override
    public Optional<Playlist> findById(String playlistId) {
        return playlistRepository.findById(playlistId);
    }

    @Override
    public Optional<Playlist> findAllByUserId(String userId) {
        return playlistRepository.findAllByUserId(userId);
    }

    @Override
    public void create(Playlist playlist) {
        playlistRepository.create(playlist);
    }

    @Override
    public void update(Playlist playlist) {
        playlistRepository.update(playlist);
    }

    @Override
    public void delete(String id) {
        playlistRepository.delete(id);
    }

    @Override
    public void addTrack(String id, List<Track> tracks) {
        playlistRepository.addTrack(id, tracks);
    }

    @Override
    public void deleteTrack(String id, Track track) {
        playlistRepository.deleteTrack(id, track);
    }
}

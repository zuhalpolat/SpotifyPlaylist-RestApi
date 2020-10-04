package com.example.restapiapplication.services;

import com.example.restapiapplication.models.Playlist;
import com.example.restapiapplication.models.Track;
import com.example.restapiapplication.repositories.PlaylistRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaylistServiceImpl implements PlaylistService {

    @Autowired
    PlaylistRepositoryImpl playlistRepository;

    @Override
    public List<Playlist> getAll() {
        return playlistRepository.getAll();
    }

    @Override
    public Playlist findById(String playlistId) {
        return playlistRepository.findById(playlistId);
    }

    @Override
    public List<Playlist> findByUserId(String userId) {
        return playlistRepository.findByUserId(userId);
    }

    @Override
    public void create(Playlist playlist) {
        playlistRepository.create(playlist);
    }

    @Override
    public void update(String id, Playlist playlist) {
        playlistRepository.update(id, playlist);
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

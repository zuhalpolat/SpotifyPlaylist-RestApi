package com.example.restapiapplication.controllers;

import com.example.restapiapplication.models.Playlist;
import com.example.restapiapplication.models.Track;
import com.example.restapiapplication.services.PlaylistServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    @Autowired
    private PlaylistServiceImpl playlistService;

    @GetMapping
    public ResponseEntity<List<Playlist>> getAllPlaylists(){
        List<Playlist> playlists = playlistService.getAll();
        return ResponseEntity.ok().body(playlists);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Playlist> getPlaylistById(@PathVariable String id){
        Playlist playlist = playlistService.findById(id);
        return ResponseEntity.ok().body(playlist);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Playlist>> getAllPlaylistByUserId(@RequestParam String userId){
        List<Playlist> playlists = playlistService.findByUserId(userId);
        return ResponseEntity.ok().body(playlists);
    }

    @PostMapping
    public ResponseEntity<Void> createPlaylist(@RequestBody Playlist playlist){
        playlistService.create(playlist);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> updatePlaylist(@PathVariable String id, @RequestBody Playlist playlist){
        playlistService.update(id, playlist);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlaylist(@PathVariable String id){
        playlistService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/tracks")
    public ResponseEntity<Void> addTrackToPlaylist(@PathVariable String id, @RequestBody List<Track> tracks){
        playlistService.addTrack(id, tracks);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/tracks")
    public ResponseEntity<Void> deleteTrackFromPlaylist(@PathVariable String id, @RequestBody Track track){
        playlistService.deleteTrack(id, track);
        return ResponseEntity.ok().build();
    }
}

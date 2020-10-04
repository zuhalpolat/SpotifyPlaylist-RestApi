package com.example.restapiapplication.controllers;

import com.example.restapiapplication.models.Playlist;
import com.example.restapiapplication.models.Track;
import com.example.restapiapplication.services.PlaylistServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    private PlaylistServiceImpl playlistService;

    @GetMapping
    public ResponseEntity<List<Playlist>> getAllPlaylist(){
        List<Playlist> playlists = playlistService.getAll();
        return ResponseEntity.ok().body(playlists);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Playlist> findById(@PathVariable String id){
        Optional<Playlist> playlist = playlistService.findById(id);
        return ResponseEntity.ok().body(playlist.get());
    }

    @GetMapping
    public ResponseEntity<Optional<Playlist>> findAllByUserId(@RequestParam("owner") String id){
        Optional<Playlist> playlists = playlistService.findAllByUserId(id);
        return ResponseEntity.ok().body(playlists);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Playlist playlist){
        playlistService.create(playlist);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody Playlist playlist){
        playlistService.update(playlist);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        playlistService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/tracks")
    public ResponseEntity<Void> addTrack(@PathVariable String id, @RequestBody List<Track> tracks){
        playlistService.addTrack(id, tracks);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/tracks")
    public ResponseEntity<Void> deleteTrack(@PathVariable String id, @RequestBody Track track){
        playlistService.deleteTrack(id, track);
        return ResponseEntity.ok().build();
    }
}

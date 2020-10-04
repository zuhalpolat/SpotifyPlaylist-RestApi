package com.example.restapiapplication.repositories;

import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.kv.GetResult;
import com.couchbase.client.java.query.QueryResult;
import com.example.restapiapplication.models.Playlist;
import com.example.restapiapplication.models.Track;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PlaylistRepositoryImpl implements PlaylistRepository{

    private final Cluster couchbaseCluster;
    private final Collection playlistCollection;

    public PlaylistRepositoryImpl(Cluster couchbaseCluster, Collection playlistCollection){
        this.couchbaseCluster = couchbaseCluster;
        this.playlistCollection = playlistCollection;
    }

    @Override
    public List<Playlist> getAll() {
        String statement = "select * from playlist";
        QueryResult queryResult = couchbaseCluster.query(statement);
        return queryResult.rowsAs(Playlist.class);
    }

    @Override
    public Optional<Playlist> findById(String playlistId) {
        GetResult getResult = playlistCollection.get(playlistId);
        return Optional.of(getResult.contentAs(Playlist.class));
    }


    @Override
    public Optional<Playlist> findAllByUserId(String userId) {
        GetResult getResult = playlistCollection.get(userId);
        return Optional.of(getResult.contentAs(Playlist.class));
    }

    @Override
    public void create(Playlist playlist) {
        playlistCollection.insert(playlist.getId(), playlist);
    }

    @Override
    public void update(Playlist playlist) {
        playlistCollection.replace(playlist.getId(), playlist);
    }

    @Override
    public void delete(String id) {
        playlistCollection.remove(id);
    }

    @Override
    public void addTrack(String id, List<Track> tracks) {
        Optional<Playlist> playlist = findById(id);
        playlist.get().getTrackList().addAll(tracks);
        playlistCollection.replace(playlist.get().getId(), playlist);
    }

    @Override
    public void deleteTrack(String id, Track track) {
        Optional<Playlist> playlist = findById(id);
        playlist.get().getTrackList().remove(track);
        playlistCollection.replace(playlist.get().getId(), playlist);
    }
}

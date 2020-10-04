package com.example.restapiapplication.exceptions;

import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.json.JsonObject;
import com.couchbase.client.java.kv.GetResult;
import com.couchbase.client.java.query.QueryResult;
import com.kodluyoruz.playlist.model.Playlist;
import com.kodluyoruz.playlist.model.Track;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.couchbase.client.java.query.QueryOptions.queryOptions;

@Repository
public class PlaylistRepository{

    private final Cluster couchbaseCluster;
    private final Collection playlistCollection;

    public PlaylistRepository(Cluster couchbaseCluster, Collection playlistCollection){
        this.couchbaseCluster = couchbaseCluster;
        this.playlistCollection = playlistCollection;
    }

    public void create(Playlist playlist) throws NotCreatedException {
        try {
            playlistCollection.insert(playlist.getId(), playlist);
        }
        catch (Exception e){
            throw new NotCreatedException(e.getMessage());
        }
    }

    public Optional<Playlist> findById(String id) {
        try {
            GetResult getResult = playlistCollection.get(id);
            return Optional.of(getResult.contentAs(Playlist.class));
        }
        catch(Exception e){
            throw new PlaylistNotFoundException(e.getMessage());
        }
    }

    public List<Playlist> findAllByUserId(String id) {
        try {
            QueryResult queryResult = couchbaseCluster.query("SELECT id, name, description, followersCount, tracksList, trackCount, userId FROM playlist WHERE userId = $id", queryOptions().parameters(JsonObject.create().put("id", id)));
            return queryResult.rowsAs(Playlist.class);
        }
        catch (Exception e){
            throw new UserNotFoundException(e.getMessage());
        }
    }

    public void delete(String id) {
        try {
            playlistCollection.remove(id);
        }
        catch (Exception e){
            throw new PlaylistNotFoundException(e.getMessage());
        }
    }

    public void update(Playlist playlist) {
        try {
            playlistCollection.replace(playlist.getId(), playlist);
        }
        catch (Exception e){
            throw new PlaylistNotFoundException(e.getMessage());
        }
    }

    public void addTracks(String id, List<Track> tracks) throws NotCreatedException {
        try {
            Optional<Playlist> playlist = findById(id);
            playlist.get().getTracks().addAll(tracks);
            playlistCollection.replace(playlist.get().getId(), playlist);
        }
        catch (Exception e){
            throw new NotCreatedException(e.getMessage());
        }
    }

    public void deleteTrack(String id, Track tracks) {
        try {
            Optional<Playlist> playlist = findById(id);
            playlist.get().getTracks().remove(tracks);
            playlistCollection.replace(playlist.get().getId(), playlist);
        }catch (Exception e){
            throw new PlaylistNotFoundException(e.getMessage());
        }
    }
}

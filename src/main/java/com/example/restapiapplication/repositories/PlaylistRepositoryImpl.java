package com.example.restapiapplication.repositories;

import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.json.JsonArray;
import com.couchbase.client.java.json.JsonObject;
import com.couchbase.client.java.kv.GetResult;
import com.couchbase.client.java.query.QueryResult;
import com.example.restapiapplication.exceptions.NotCreatedException;
import com.example.restapiapplication.exceptions.PlaylistNotFoundException;
import com.example.restapiapplication.exceptions.TrackNotFoundException;
import com.example.restapiapplication.exceptions.UserNotFoundException;
import com.example.restapiapplication.models.Playlist;
import com.example.restapiapplication.models.Track;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.couchbase.client.java.query.QueryOptions.queryOptions;

@Repository
public class PlaylistRepositoryImpl implements PlaylistRepository{

    private final Cluster couchbaseCluster;
    private final Collection playlistCollection;

    public PlaylistRepositoryImpl(Cluster couchbaseCluster, Collection playlistCollection){
        this.couchbaseCluster = couchbaseCluster;
        this.playlistCollection = playlistCollection;
    }

    @Override
    public List<Playlist> getAll() throws NotCreatedException {
        try {
            String statement = "select `playlist`.* from playlist";
            QueryResult queryResult = couchbaseCluster.query(statement);
            return queryResult.rowsAs(Playlist.class);
        }
        catch (Exception e){
            throw new NotCreatedException(e.getMessage());
        }
    }

    @Override
    public Playlist findById(String playlistId) {
        try {
            GetResult getResult = playlistCollection.get(playlistId);
            return getResult.contentAs(Playlist.class);
        }
        catch (Exception e){
            throw new PlaylistNotFoundException(e.getMessage());
        }
    }

    @Override
    public List<Playlist> findByUserId(String userId) {
        try {
            String query = "Select `playlist`.* from playlist where userId=$userId";
            QueryResult queryResult = couchbaseCluster.query(query, queryOptions().parameters(JsonObject.create().put("userId", userId)));
            return queryResult.rowsAs(Playlist.class);
        }
        catch (Exception e){
            throw new UserNotFoundException(e.getMessage());
        }
    }

    @Override
    public void create(Playlist playlist) throws NotCreatedException {
        try {
            playlistCollection.insert(playlist.getId(), playlist);
        }
        catch (Exception e){
            throw new NotCreatedException(e.getMessage());
        }
    }

    @Override
    public void update(String id, Playlist playlist) {
        try {
            playlistCollection.replace(id, playlist);
        }
        catch (Exception e){
            throw new PlaylistNotFoundException(e.getMessage());
        }
    }

    @Override
    public void delete(String id) {
        try {
            playlistCollection.remove(id);
        }
        catch (Exception e){
            throw new PlaylistNotFoundException(e.getMessage());
        }
    }

    @Override
    public void addTrack(String id, List<Track> tracks) {
        try {
            Playlist playlist = findById(id);
            playlist.getTrackList().addAll(tracks);
            playlistCollection.replace(playlist.getId(), playlist);
        }
        catch (Exception e){
            throw new TrackNotFoundException(e.getMessage());
        }
    }

    @Override
    public void deleteTrack(String id, Track track) {
        try {
            Playlist playlist = findById(id);
            playlist.getTrackList().remove(track);
            playlistCollection.replace(playlist.getId(), playlist);
        }
        catch (Exception e){
            throw new TrackNotFoundException(e.getMessage());
        }
    }
}

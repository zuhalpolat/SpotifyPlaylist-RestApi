package com.example.restapiapplication.repositories;

import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.json.JsonArray;
import com.couchbase.client.java.json.JsonObject;
import com.couchbase.client.java.kv.GetResult;
import com.couchbase.client.java.query.QueryResult;
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
    public List<Playlist> getAll() {
        String statement = "select `playlist`.* from playlist";
        QueryResult queryResult = couchbaseCluster.query(statement);
        return queryResult.rowsAs(Playlist.class);
    }

    @Override
    public Playlist findById(String playlistId) {
        GetResult getResult = playlistCollection.get(playlistId);
        return getResult.contentAs(Playlist.class);
    }

    @Override
    public List<Playlist> findByUserId(String userId) {
        String query = "Select `playlist`.* from playlist where userId=$userId";
        QueryResult queryResult = couchbaseCluster.query(query, queryOptions().parameters(JsonObject.create().put("userId", userId)));
        return queryResult.rowsAs(Playlist.class);
    }

    @Override
    public void create(Playlist playlist) {
        playlistCollection.insert(playlist.getId(), playlist);
    }

    @Override
    public void update(String id, Playlist playlist) {
        playlistCollection.replace(id, playlist);
    }

    @Override
    public void delete(String id) {
        playlistCollection.remove(id);
    }

    @Override
    public void addTrack(String id, List<Track> tracks) {
        Playlist playlist = findById(id);
        playlist.getTrackList().addAll(tracks);
        playlistCollection.replace(playlist.getId(), playlist);
    }

    @Override
    public void deleteTrack(String id, Track track) {
        Playlist playlist = findById(id);
        playlist.getTrackList().remove(track);
        playlistCollection.replace(playlist.getId(), playlist);
    }
}

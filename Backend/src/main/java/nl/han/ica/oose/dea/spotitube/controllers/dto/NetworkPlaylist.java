package nl.han.ica.oose.dea.spotitube.controllers.dto;

import nl.han.ica.oose.dea.spotitube.controllers.dto.converters.TrackDTOConverter;
import nl.han.ica.oose.dea.spotitube.domain.interfaces.IPlaylist;

import java.util.ArrayList;

public class NetworkPlaylist {
    private int id;
    private String name;
    private boolean owner;
    private ArrayList<NetworkTrack> tracks;

    public NetworkPlaylist() {
        tracks = new ArrayList<>();
    }

    public NetworkPlaylist(IPlaylist playlist, String currentUser){
        this.id = playlist.getID();
        this.name = playlist.getName();
        this.tracks = new ArrayList<>();
        this.owner = playlist.getOwner().equals(currentUser);
        this.tracks = TrackDTOConverter.tracksToNetwork(playlist.getTracks());
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isOwner() {
        return owner;
    }

    public ArrayList<NetworkTrack> getTracks() {
        return tracks;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public void setTracks(ArrayList<NetworkTrack> tracks) {
        this.tracks = tracks;
    }
}

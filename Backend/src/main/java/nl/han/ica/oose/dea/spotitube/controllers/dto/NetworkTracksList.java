package nl.han.ica.oose.dea.spotitube.controllers.dto;

import java.util.ArrayList;

public class NetworkTracksList {
    private ArrayList<NetworkTrack> tracks;

    public NetworkTracksList() {}

    public NetworkTracksList(ArrayList<NetworkTrack> tracks) {
        this.tracks = tracks;
    }

    public ArrayList<NetworkTrack> getTracks() {
        return tracks;
    }

    public void setTracks(ArrayList<NetworkTrack> tracks) {
        this.tracks = tracks;
    }
}

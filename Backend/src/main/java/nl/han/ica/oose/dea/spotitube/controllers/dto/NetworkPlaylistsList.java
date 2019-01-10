package nl.han.ica.oose.dea.spotitube.controllers.dto;

import nl.han.ica.oose.dea.spotitube.domain.Playlist;
import nl.han.ica.oose.dea.spotitube.domain.Track;

import java.util.ArrayList;

public class NetworkPlaylistsList {
    private ArrayList<NetworkPlaylist> playlists;
    private int length;

    public NetworkPlaylistsList(ArrayList<NetworkPlaylist> playlists) {
        this.playlists = playlists;
        calculateLength();
    }

    private void calculateLength() {
        this.length = 0;
        for (NetworkPlaylist p : playlists) {
            for (NetworkTrack t : p.getTracks()) {
                this.length += t.getDuration();
            }
        }
    }

    public ArrayList<NetworkPlaylist> getPlaylists() {
        return playlists;
    }

    public int getLength() {
        return length;
    }

    public void setPlaylists(ArrayList<NetworkPlaylist> playlists) {
        this.playlists = playlists;
    }

    public void setLength(int length) {
        this.length = length;
    }
}

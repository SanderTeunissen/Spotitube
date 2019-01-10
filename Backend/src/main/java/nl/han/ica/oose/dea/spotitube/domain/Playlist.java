package nl.han.ica.oose.dea.spotitube.domain;

import nl.han.ica.oose.dea.spotitube.domain.interfaces.IPlaylist;
import nl.han.ica.oose.dea.spotitube.domain.interfaces.ITrack;
import nl.han.ica.oose.dea.spotitube.domain.util.Entity;

import java.util.ArrayList;

public class Playlist extends Entity implements IPlaylist {
    private String name;
    private String owner;
    private ArrayList<ITrack> tracks;

    public Playlist(int ID, String name, String owner) {
        super(ID);
        this.name = name;
        this.owner = owner;
        tracks = new ArrayList<>();
    }

    public Playlist(int ID, String name, String owner, ArrayList<ITrack> tracks) {
        super(ID);
        this.name = name;
        this.owner = owner;
        this.tracks = tracks;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getOwner() {
        return owner;
    }

    @Override
    public void setOwner(String owner) { this.owner = owner; }

    @Override
    public ArrayList<ITrack> getTracks() {
        return tracks;
    }

    @Override
    public void setTracks(ArrayList<ITrack> tracks) {
        this.tracks = tracks;
    }
}

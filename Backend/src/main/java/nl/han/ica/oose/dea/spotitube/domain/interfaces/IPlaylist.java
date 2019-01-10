package nl.han.ica.oose.dea.spotitube.domain.interfaces;

import nl.han.ica.oose.dea.spotitube.domain.Track;

import java.util.ArrayList;

public interface IPlaylist extends IEntity {
    String getName();
    void setName(String name);
    String getOwner();
    void setOwner(String owner);
    ArrayList<ITrack> getTracks();
    void setTracks(ArrayList<ITrack> tracks);
}

package nl.han.ica.oose.dea.spotitube.data.dao.interfaces;

import nl.han.ica.oose.dea.spotitube.domain.interfaces.ITrack;

import java.util.ArrayList;

public interface ITrackDAO {
    ArrayList<ITrack> all();
    ArrayList<ITrack> allFromPlaylist(int playlistID);
    void addTrackToPlaylist(int playlistID, int trackID, boolean offlineAvailable);
    void removeTrackFromPlaylist(int playlistID, int trackID);
}

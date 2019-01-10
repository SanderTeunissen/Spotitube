package nl.han.ica.oose.dea.spotitube.services.interfaces;

import nl.han.ica.oose.dea.spotitube.domain.interfaces.ITrack;

import java.util.ArrayList;

public interface ITrackService {
    ArrayList<ITrack> getTracks();
    ArrayList<ITrack> getTracksFromPlaylist(int playlistID);
    void addTrackToPlaylist(int playlistID, int trackID, boolean offlineAvailable);
    void removeTrackFromPlaylist(int playlistID, int trackID);
}

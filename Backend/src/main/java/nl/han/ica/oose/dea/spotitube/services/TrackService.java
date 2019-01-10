package nl.han.ica.oose.dea.spotitube.services;

import nl.han.ica.oose.dea.spotitube.data.dao.interfaces.ITrackDAO;
import nl.han.ica.oose.dea.spotitube.domain.Track;
import nl.han.ica.oose.dea.spotitube.domain.interfaces.ITrack;
import nl.han.ica.oose.dea.spotitube.services.interfaces.ITrackService;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.ArrayList;

@Default
public class TrackService implements ITrackService {
    @Inject
    private ITrackDAO trackDAO;

    @Override
    public ArrayList<ITrack> getTracks() {
        return trackDAO.all();
    }

    @Override
    public ArrayList<ITrack> getTracksFromPlaylist(int playlistID) {
        return trackDAO.allFromPlaylist(playlistID);
    }

    @Override
    public void addTrackToPlaylist(int playlistID, int trackID, boolean offlineAvailable) {
        trackDAO.addTrackToPlaylist(playlistID,trackID,offlineAvailable);
    }

    @Override
    public void removeTrackFromPlaylist(int playlistID, int trackID) {
        trackDAO.removeTrackFromPlaylist(playlistID,trackID);
    }
}

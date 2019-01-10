package nl.han.ica.oose.dea.spotitube.services;

import nl.han.ica.oose.dea.spotitube.data.dao.interfaces.IPlaylistDAO;
import nl.han.ica.oose.dea.spotitube.domain.Playlist;
import nl.han.ica.oose.dea.spotitube.domain.interfaces.IPlaylist;
import nl.han.ica.oose.dea.spotitube.exceptions.EntityNotFoundException;
import nl.han.ica.oose.dea.spotitube.services.interfaces.IPlaylistService;
import nl.han.ica.oose.dea.spotitube.services.interfaces.ITrackService;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Optional;

@Default
public class PlaylistService implements IPlaylistService {
    @Inject
    private IPlaylistDAO playlistDAO;

    @Inject
    private ITrackService trackService;

    public ArrayList<IPlaylist> getPlaylists() {
        ArrayList<IPlaylist> playlists = playlistDAO.all();
        for(IPlaylist p : playlists){
            p.setTracks(trackService.getTracksFromPlaylist(p.getID()));
        }
        return playlists;
    }

    public ArrayList<IPlaylist> getPlaylistsFromUser(String username) {
        return playlistDAO.allFromUser(username);
    }

    public IPlaylist getPlaylistWithID(int playlistID) throws EntityNotFoundException {
        Optional<IPlaylist> optionalUser = playlistDAO.byId(playlistID);
        return optionalUser.orElseThrow(() -> new EntityNotFoundException(Playlist.class));
    }

    public void addPlaylist(IPlaylist playlist) {
        playlistDAO.add(playlist);
    }

    public void updateName(int playlistId, String name) {
        playlistDAO.rename(playlistId,name);
    }

    public void deletePlaylist(int playlistId) {
        playlistDAO.remove(playlistId);
    }

}

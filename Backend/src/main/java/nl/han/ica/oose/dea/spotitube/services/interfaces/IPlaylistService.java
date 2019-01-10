package nl.han.ica.oose.dea.spotitube.services.interfaces;

import nl.han.ica.oose.dea.spotitube.domain.interfaces.IPlaylist;
import nl.han.ica.oose.dea.spotitube.exceptions.EntityNotFoundException;

import java.util.ArrayList;
import java.util.Optional;

public interface IPlaylistService {

    ArrayList<IPlaylist> getPlaylists();

    ArrayList<IPlaylist> getPlaylistsFromUser(String username);

    IPlaylist getPlaylistWithID(int playlistID) throws EntityNotFoundException;

    void addPlaylist(IPlaylist playlist);

    void updateName(int playlistId, String name);

    void deletePlaylist(int playlistId);
}

package nl.han.ica.oose.dea.spotitube.data.dao.interfaces;

import nl.han.ica.oose.dea.spotitube.domain.interfaces.IPlaylist;

import java.util.ArrayList;
import java.util.Optional;

public interface IPlaylistDAO {
    ArrayList<IPlaylist> all();
    ArrayList<IPlaylist> allFromUser(String username);
    Optional<IPlaylist> byId(int playlistID);
    void add(IPlaylist newPlaylist);
    void rename(int playlistID, String newName);
    void remove(int playlistID);
}

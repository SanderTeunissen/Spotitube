package nl.han.ica.oose.dea.spotitube.controllers.dto.converters;

import nl.han.ica.oose.dea.spotitube.controllers.dto.NetworkPlaylist;
import nl.han.ica.oose.dea.spotitube.controllers.dto.NetworkPlaylistsList;
import nl.han.ica.oose.dea.spotitube.domain.Playlist;
import nl.han.ica.oose.dea.spotitube.domain.interfaces.IPlaylist;

import java.util.ArrayList;

public class PlaylistDTOConverter {
    public static NetworkPlaylist playlistToNetwork(IPlaylist playlist, String currentUser){
        return new NetworkPlaylist(playlist, currentUser);
    }

    public static ArrayList<NetworkPlaylist> playlistsToNetwork(ArrayList<IPlaylist> playlists, String currentUser){
        ArrayList<NetworkPlaylist> networkPlaylists = new ArrayList<>();
        for(IPlaylist p : playlists){
            networkPlaylists.add(playlistToNetwork(p, currentUser));
        }
        return networkPlaylists;
    }

    public static NetworkPlaylistsList playlistsToNetworkList(ArrayList<IPlaylist> playlists, String currentUser){
        return new NetworkPlaylistsList(playlistsToNetwork(playlists,currentUser));
    }

    public static IPlaylist networkToPlaylist(NetworkPlaylist np, String currentUser){
        return new Playlist(np.getId(),np.getName(),currentUser,TrackDTOConverter.networkToTracks(np.getTracks()));
    }

    public static ArrayList<IPlaylist> networkToPlaylists(ArrayList<NetworkPlaylist> nps, String currentUser){
        ArrayList<IPlaylist> playlists = new ArrayList<>();
        for(NetworkPlaylist np : nps){
            playlists.add(networkToPlaylist(np, currentUser));
        }
        return playlists;
    }
}

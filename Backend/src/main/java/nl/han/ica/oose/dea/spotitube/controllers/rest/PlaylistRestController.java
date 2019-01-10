package nl.han.ica.oose.dea.spotitube.controllers.rest;

import nl.han.ica.oose.dea.spotitube.annotations.authentication.Authenticated;
import nl.han.ica.oose.dea.spotitube.controllers.dto.*;
import nl.han.ica.oose.dea.spotitube.controllers.dto.converters.PlaylistDTOConverter;
import nl.han.ica.oose.dea.spotitube.controllers.dto.converters.TrackDTOConverter;
import nl.han.ica.oose.dea.spotitube.services.interfaces.IPlaylistService;
import nl.han.ica.oose.dea.spotitube.services.interfaces.ITrackService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/playlists")
@Singleton
public class PlaylistRestController {
    @Inject
    private IPlaylistService playlistService;

    @Inject
    private ITrackService trackService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Authenticated
    public NetworkPlaylistsList getAllPlaylists(@Context HttpServletRequest servletRequest) {
        return PlaylistDTOConverter.playlistsToNetworkList(playlistService.getPlaylists(),getCurrentUser(servletRequest));
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{playlistId}")
    @Authenticated
    public NetworkPlaylistsList deletePlaylist(@PathParam("playlistId") int playlistId, @Context HttpServletRequest servletRequest) {
        playlistService.deletePlaylist(playlistId);
        return PlaylistDTOConverter.playlistsToNetworkList(playlistService.getPlaylists(),getCurrentUser(servletRequest));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Authenticated
    public NetworkPlaylistsList addPlaylist(NetworkPlaylist playlistFromRequest, @Context HttpServletRequest servletRequest) {
        String currentUser = getCurrentUser(servletRequest);
        playlistService.addPlaylist(PlaylistDTOConverter.networkToPlaylist(playlistFromRequest,currentUser));
        return PlaylistDTOConverter.playlistsToNetworkList(playlistService.getPlaylists(),currentUser);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{playlistId}")
    @Authenticated
    public NetworkPlaylistsList renamePlaylist(NetworkPlaylist playlistFromRequest, @Context HttpServletRequest servletRequest) {
        if(playlistFromRequest.isOwner()){
            playlistService.updateName(playlistFromRequest.getId(), playlistFromRequest.getName());
        }
        return PlaylistDTOConverter.playlistsToNetworkList(playlistService.getPlaylists(),getCurrentUser(servletRequest));
    }

    @GET
    @Path("/{playlistId}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    @Authenticated
    public NetworkTracksList getTracksFromPlaylist(@PathParam("playlistId") int playlistId) {
        return TrackDTOConverter.tracksToNetworkList(trackService.getTracksFromPlaylist(playlistId));
    }

    @DELETE
    @Path("/{playlistId}/tracks/{trackId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Authenticated
    public NetworkTracksList removeTrackFromPlaylist(@PathParam("playlistId") int playlistId, @PathParam("trackId") int trackId) {
        trackService.removeTrackFromPlaylist(playlistId,trackId);
        return TrackDTOConverter.tracksToNetworkList(trackService.getTracksFromPlaylist(playlistId));
    }

    @POST
    @Path("/{playlistId}/tracks")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Authenticated
    public NetworkTracksList addTrackFromPlaylist(TrackSelection trackSelection, @PathParam("playlistId") int playlistId) {
        trackService.addTrackToPlaylist(playlistId,trackSelection.getId(),trackSelection.isOfflineAvailable());
        return TrackDTOConverter.tracksToNetworkList(trackService.getTracksFromPlaylist(playlistId));
    }

    private String getCurrentUser(HttpServletRequest servletRequest){
        return (String) servletRequest.getAttribute("currentUserName");
    }
}

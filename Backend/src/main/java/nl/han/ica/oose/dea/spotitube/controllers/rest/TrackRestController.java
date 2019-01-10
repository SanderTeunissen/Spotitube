package nl.han.ica.oose.dea.spotitube.controllers.rest;

import nl.han.ica.oose.dea.spotitube.annotations.authentication.Authenticated;
import nl.han.ica.oose.dea.spotitube.controllers.dto.NetworkTracksList;
import nl.han.ica.oose.dea.spotitube.controllers.dto.converters.TrackDTOConverter;
import nl.han.ica.oose.dea.spotitube.services.interfaces.ITrackService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/tracks")
@Singleton
public class TrackRestController {
    @Inject
    private ITrackService trackService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Authenticated
    public NetworkTracksList getAllTracks() {
        return TrackDTOConverter.tracksToNetworkList(trackService.getTracks());
    }
}

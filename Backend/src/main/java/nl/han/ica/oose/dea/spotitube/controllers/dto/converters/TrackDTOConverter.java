package nl.han.ica.oose.dea.spotitube.controllers.dto.converters;

import nl.han.ica.oose.dea.spotitube.controllers.dto.NetworkTrack;
import nl.han.ica.oose.dea.spotitube.controllers.dto.NetworkTracksList;
import nl.han.ica.oose.dea.spotitube.domain.Song;
import nl.han.ica.oose.dea.spotitube.domain.Track;
import nl.han.ica.oose.dea.spotitube.domain.Video;
import nl.han.ica.oose.dea.spotitube.domain.interfaces.ISong;
import nl.han.ica.oose.dea.spotitube.domain.interfaces.ITrack;
import nl.han.ica.oose.dea.spotitube.domain.interfaces.IVideo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TrackDTOConverter {
    public static NetworkTrack trackToNetwork(ITrack track){
        if(track instanceof ISong){
            return new NetworkTrack((ISong) track);
        } else {
            return new NetworkTrack((IVideo) track);
        }
    }

    public static ArrayList<NetworkTrack> tracksToNetwork(ArrayList<ITrack> tracks){
        ArrayList<NetworkTrack> networkTracks = new ArrayList<>();
        for(ITrack t : tracks){
            networkTracks.add(trackToNetwork(t));
        }
        return networkTracks;
    }

    public static NetworkTracksList tracksToNetworkList(ArrayList<ITrack> tracks){
        return new NetworkTracksList(tracksToNetwork(tracks));
    }

    public static ITrack networkToTrack(NetworkTrack nt){
        if(nt.getAlbum() == null){
            return new Song(nt.getId(),nt.getTitle(),nt.getDuration(),nt.getPerformer(),nt.getUrl(),nt.isOfflineAvailable(),nt.getAlbum());
        }else{
            Date pubDate;
            try{
                pubDate = new SimpleDateFormat("MM/dd/yy").parse(nt.getPublicationDate());
            }catch (Exception e){
                e.printStackTrace();
                pubDate = new Date();
            }
            return new Video(nt.getId(),nt.getTitle(),nt.getDuration(),nt.getPerformer(),nt.getUrl(),nt.isOfflineAvailable(),nt.getDescription(),nt.getPlaycount(), pubDate);
        }
    }

    public static ArrayList<ITrack> networkToTracks(ArrayList<NetworkTrack> nts){
        ArrayList<ITrack> tracks = new ArrayList<>();
        for(NetworkTrack nt : nts){
            tracks.add(networkToTrack(nt));
        }
        return tracks;
    }
}

package nl.han.ica.oose.dea.spotitube.controllers.dto;

import nl.han.ica.oose.dea.spotitube.domain.interfaces.ISong;
import nl.han.ica.oose.dea.spotitube.domain.interfaces.IVideo;

public class NetworkTrack {
    private int id;
    private String title;
    private int duration;
    private String performer;
    private String url;
    private String album;
    private String description;
    private int playcount;
    private String publicationDate;
    private boolean offlineAvailable;

    public NetworkTrack() {}

    public NetworkTrack(ISong song) {
        this.id = song.getID();
        this.title = song.getTitle();
        this.duration = song.getDuration();
        this.performer = song.getPerformer();
        this.url = song.getUrl();
        this.album = song.getAlbum();
        this.offlineAvailable = song.isOfflineAvailable();
    }

    public NetworkTrack(IVideo video) {
        this.id = video.getID();
        this.title = video.getTitle();
        this.duration = video.getDuration();
        this.performer = video.getPerformer();
        this.url = video.getUrl();
        this.description = video.getDescription();
        this.playcount = video.getPlayCount();
        this.publicationDate = video.getPublicationDate().toString();
        this.offlineAvailable = video.isOfflineAvailable();
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }

    public String getPerformer() {
        return performer;
    }

    public String getUrl() {
        return url;
    }

    public String getAlbum() {
        return album;
    }

    public String getDescription() {
        return description;
    }

    public int getPlaycount() {
        return playcount;
    }

    public String getPublicationDate() {
        return this.publicationDate;
    }

    public boolean isOfflineAvailable() {
        return offlineAvailable;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPlaycount(int playcount) {
        this.playcount = playcount;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public void setOfflineAvailable(boolean offlineAvailable) {
        this.offlineAvailable = offlineAvailable;
    }
}

package nl.han.ica.oose.dea.spotitube.domain;

import nl.han.ica.oose.dea.spotitube.domain.interfaces.ITrack;
import nl.han.ica.oose.dea.spotitube.domain.util.Entity;

public class Track extends Entity implements ITrack {
    private String title;
    private int duration;
    private String performer;
    private String url;
    private boolean offlineAvailable;

    public Track(int ID, String title, int duration, String performer, String url) {
        super(ID);
        this.title = title;
        this.duration = duration;
        this.performer = performer;
        this.url = url;
        offlineAvailable = false;
    }

    public Track(int ID, String title, int duration, String performer, String url, boolean offlineAvailable) {
        super(ID);
        this.title = title;
        this.duration = duration;
        this.performer = performer;
        this.url = url;
        this.offlineAvailable = offlineAvailable;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String getPerformer() {
        return performer;
    }

    @Override
    public void setPerformer(String performer) {
        this.performer = performer;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean isOfflineAvailable() {
        return offlineAvailable;
    }

    @Override
    public void setOfflineAvailable(boolean offlineAvailable) {
        this.offlineAvailable = offlineAvailable;
    }
}

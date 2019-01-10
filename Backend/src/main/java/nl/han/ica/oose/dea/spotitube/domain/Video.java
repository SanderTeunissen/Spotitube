package nl.han.ica.oose.dea.spotitube.domain;

import nl.han.ica.oose.dea.spotitube.domain.interfaces.IVideo;

import java.util.Date;

public class Video extends Track implements IVideo {
    private String description;
    private int playCount;
    private Date publicationDate;

    public Video(int ID, String title, int duration, String performer, String url, String description, int playCount, Date publicationDate) {
        super(ID, title, duration, performer, url);
        this.description = description;
        this.playCount = playCount;
        this.publicationDate = publicationDate;
    }

    public Video(int ID, String title, int duration, String performer, String url, boolean offlineAvailable, String description, int playCount, Date publicationDate) {
        super(ID, title, duration, performer, url, offlineAvailable);
        this.description = description;
        this.playCount = playCount;
        this.publicationDate = publicationDate;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int getPlayCount() {
        return playCount;
    }

    @Override
    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

    @Override
    public Date getPublicationDate() {
        return publicationDate;
    }

    @Override
    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }
}

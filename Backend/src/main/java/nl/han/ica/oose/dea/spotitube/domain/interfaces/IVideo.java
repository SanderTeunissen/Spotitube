package nl.han.ica.oose.dea.spotitube.domain.interfaces;

import java.util.Date;

public interface IVideo extends ITrack {
    String getDescription();
    void setDescription(String description);
    int getPlayCount();
    void setPlayCount(int playCount);
    Date getPublicationDate();
    void setPublicationDate(Date publicationDate);
}

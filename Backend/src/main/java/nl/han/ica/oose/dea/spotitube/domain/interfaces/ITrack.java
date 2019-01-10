package nl.han.ica.oose.dea.spotitube.domain.interfaces;

public interface ITrack extends IEntity {
    String getTitle();
    void setTitle(String title);
    int getDuration();
    void setDuration(int duration);
    String getPerformer();
    void setPerformer(String performer);
    String getUrl();
    void setUrl(String url);
    boolean isOfflineAvailable();
    void setOfflineAvailable(boolean offlineAvailable);
}

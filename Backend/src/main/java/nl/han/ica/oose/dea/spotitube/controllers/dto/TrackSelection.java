package nl.han.ica.oose.dea.spotitube.controllers.dto;

public class TrackSelection {
    private int id;
    private String title;
    private int duration;
    private String performer;
    private boolean offlineAvailable;

    public TrackSelection() {}

    public TrackSelection(int id, String title, int duration, String performer, boolean offlineAvailable) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.performer = performer;
        this.offlineAvailable = offlineAvailable;
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

    public void setOfflineAvailable(boolean offlineAvailable) {
        this.offlineAvailable = offlineAvailable;
    }
}

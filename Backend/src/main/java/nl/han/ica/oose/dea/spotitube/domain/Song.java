package nl.han.ica.oose.dea.spotitube.domain;

import nl.han.ica.oose.dea.spotitube.domain.interfaces.ISong;

public class Song extends Track implements ISong {
    private String album;

    public Song(int ID, String title, int duration, String performer, String url, String album) {
        super(ID, title, duration, performer, url);
        this.album = album;
    }

    public Song(int ID, String title, int duration, String performer, String url, boolean offlineAvailable, String album) {
        super(ID, title, duration, performer, url, offlineAvailable);
        this.album = album;
    }

    @Override
    public String getAlbum() {
        return album;
    }

    @Override
    public void setAlbum(String album) {
        this.album = album;
    }
}

package nl.han.ica.oose.dea.spotitube.data.dao;

import nl.han.ica.oose.dea.spotitube.data.connection.IDBConnection;
import nl.han.ica.oose.dea.spotitube.data.dao.interfaces.ITrackDAO;
import nl.han.ica.oose.dea.spotitube.domain.Song;
import nl.han.ica.oose.dea.spotitube.domain.Track;
import nl.han.ica.oose.dea.spotitube.domain.Video;
import nl.han.ica.oose.dea.spotitube.domain.interfaces.ITrack;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@Default
public class TrackDAO implements ITrackDAO {
    @Inject
    private IDBConnection dbConnection;
    private Logger logger = Logger.getLogger(UserDAO.class.getName());

    @Override
    public ArrayList<ITrack> all() {
        try {
            Connection conn = dbConnection.getConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM Tracks");
            ArrayList<ITrack> tracks = getTracksFromDB(statement, false);
            conn.close();
            return tracks;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to collect all tracks from database");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public ArrayList<ITrack> allFromPlaylist(int playlistID) {
        try {
            Connection conn = dbConnection.getConnection();
            PreparedStatement statement = conn.prepareStatement("select t.ID,t.Title,t.Performer,t.Duration,t.URL,t.Discriminator,t.Album,t.PlayCount,t.Description,t.PublicationDate,pt.OfflineAvailable from Tracks as t inner join PlaylistTrack as pt on t.ID = pt.TrackID where pt.PlaylistID = ?");
            statement.setInt(1, playlistID);
            ArrayList<ITrack> tracks = getTracksFromDB(statement, true);
            conn.close();
            return tracks;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, MessageFormat.format("Failed to collect all tracks from playlist with ID: {0} from database", playlistID));
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public void addTrackToPlaylist(int playlistID, int trackID, boolean offlineAvailable) {
        try {
            Connection conn = dbConnection.getConnection();
            PreparedStatement statement = conn.prepareStatement("INSERT INTO PlaylistTrack (PlaylistID, TrackID, OfflineAvailable) VALUE (?, ?, ?)");
            statement.setInt(1, playlistID);
            statement.setInt(2, trackID);
            statement.setBoolean(3, offlineAvailable);
            statement.execute();
            conn.close();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, MessageFormat.format("Failed to collect all tracks from playlist with ID: {0} from database", playlistID));
            e.printStackTrace();
        }
    }

    @Override
    public void removeTrackFromPlaylist(int playlistID, int trackID) {
        try {
            Connection conn = dbConnection.getConnection();
            PreparedStatement statement = conn.prepareStatement("DELETE FROM PlaylistTrack WHERE PlaylistID = ? AND TrackID = ?");
            statement.setInt(1, playlistID);
            statement.setInt(2, trackID);
            statement.execute();
            conn.close();
        } catch (SQLException e) {
            logger.warning(MessageFormat.format("Failed to remove playlist with id {0} from database", playlistID));
            e.printStackTrace();
        }
    }

    private ArrayList<ITrack> getTracksFromDB(PreparedStatement statement, boolean fillOfflineAvailabillity) throws SQLException {
        ArrayList<ITrack> tracks = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next())
        {
            tracks.add(getTrackFromResultset(resultSet, fillOfflineAvailabillity));
        }
        return tracks;
    }

    private ITrack getTrackFromResultset(ResultSet resultSet, boolean fillOfflineAvailabillity) throws SQLException{
        int ID = resultSet.getInt("ID");
        String title = resultSet.getString("Title");
        int duration = resultSet.getInt("Duration");
        String performer = resultSet.getString("Performer");
        String url = resultSet.getString("URL");
        boolean offlineAvailable = false;
        if(fillOfflineAvailabillity) offlineAvailable = resultSet.getBoolean("OfflineAvailable");
        if(resultSet.getString("Discriminator").equals("Song")){
            return new Song(ID,title,duration,performer,url,offlineAvailable,resultSet.getString("Album"));
        } else {
            return new Video(ID,title,duration,performer,url,offlineAvailable,
                    resultSet.getString("Description"),
                    resultSet.getInt("PlayCount"),
                    resultSet.getDate("PublicationDate")
            );
        }
    }

}

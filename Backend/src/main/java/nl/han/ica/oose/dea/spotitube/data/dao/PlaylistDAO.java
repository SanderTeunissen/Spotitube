package nl.han.ica.oose.dea.spotitube.data.dao;

import nl.han.ica.oose.dea.spotitube.data.connection.IDBConnection;
import nl.han.ica.oose.dea.spotitube.data.dao.interfaces.IPlaylistDAO;
import nl.han.ica.oose.dea.spotitube.domain.Playlist;
import nl.han.ica.oose.dea.spotitube.domain.interfaces.IPlaylist;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Default
public class PlaylistDAO implements IPlaylistDAO {
    @Inject private IDBConnection dbConnection;
    private Logger logger = Logger.getLogger(UserDAO.class.getName());

    @Override
    public ArrayList<IPlaylist> all() {
        try{
            Connection conn = dbConnection.getConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM Playlists");
            ArrayList<IPlaylist> playlists = getPlaylistsFromDB(statement);
            conn.close();
            return playlists;
        }catch (SQLException e){
            logger.log(Level.SEVERE, "Failed to collect all playlists from database");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public ArrayList<IPlaylist> allFromUser(String ownerUsername) {
        try{
            Connection conn = dbConnection.getConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM Playlists WHERE Owner == ?");
            statement.setString(1, ownerUsername);
            ArrayList<IPlaylist> playlists = getPlaylistsFromDB(statement);
            conn.close();
            return playlists;
        }catch (SQLException e){
            logger.log(Level.SEVERE, MessageFormat.format("Failed to collect all playlists with owner {0} from database", ownerUsername));
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Optional<IPlaylist> byId(int playlistID) {
        try {
            Connection conn = dbConnection.getConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM Playlists WHERE ID == ?");
            statement.setInt(1, playlistID);
            ResultSet resultSet = statement.executeQuery();
            Optional<IPlaylist> optionalPlaylist;
            if (resultSet.next()) {
                optionalPlaylist = Optional.of(getPlaylistFromResultSet(resultSet));
            } else{
                optionalPlaylist = Optional.empty();
            }
            conn.close();
            return optionalPlaylist;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, MessageFormat.format("Failed to collect playlist with ID {0} from database", playlistID));
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public void add(IPlaylist newPlaylist) {
        try {
            Connection conn = dbConnection.getConnection();
            PreparedStatement statement = conn.prepareStatement("INSERT INTO Playlists (Name, Owner) VALUE (?, ?)");
            statement.setString(1, newPlaylist.getName());
            statement.setString(2, newPlaylist.getOwner());
            statement.execute();
            conn.close();
        } catch (SQLException e) {
            logger.warning(MessageFormat.format("Failed to insert playlist with name {0} into database", newPlaylist.getName()));
            e.printStackTrace();
        }
    }

    @Override
    public void rename(int playlistID, String newName) {
        try {
            Connection conn = dbConnection.getConnection();
            PreparedStatement statement = conn.prepareStatement("UPDATE Playlists SET Name = ? WHERE ID = ?");
            statement.setString(1, newName);
            statement.setInt(2, playlistID);
            statement.execute();
            conn.close();
        } catch (SQLException e) {
            logger.warning(MessageFormat.format("Failed to update name of playlist with id {0} to \"{1}\"", playlistID, newName));
            e.printStackTrace();
        }
    }

    @Override
    public void remove(int playlistID) {
        try {
            Connection conn = dbConnection.getConnection();
            PreparedStatement statement = conn.prepareStatement("DELETE FROM Playlists WHERE ID = ?");
            statement.setInt(1, playlistID);
            statement.execute();
            conn.close();
        } catch (SQLException e) {
            logger.warning(MessageFormat.format("Failed to remove playlist with id {0} from database", playlistID));
            e.printStackTrace();
        }
    }

    private ArrayList<IPlaylist> getPlaylistsFromDB(PreparedStatement statement) throws SQLException {
        ArrayList<IPlaylist> playlists = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next())
        {
            playlists.add(getPlaylistFromResultSet(resultSet));
        }
        return playlists;
    }

    private IPlaylist getPlaylistFromResultSet(ResultSet resultSet) throws SQLException{
        return new Playlist(
                resultSet.getInt("ID"),
                resultSet.getString("Name"),
                resultSet.getString("Owner")
        );
    }
}

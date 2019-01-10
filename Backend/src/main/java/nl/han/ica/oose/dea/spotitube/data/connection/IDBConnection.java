package nl.han.ica.oose.dea.spotitube.data.connection;

import java.sql.Connection;
import java.sql.SQLException;

public interface IDBConnection {
    Connection getConnection() throws SQLException;
    void closeConnection(Connection connection) throws SQLException;
}

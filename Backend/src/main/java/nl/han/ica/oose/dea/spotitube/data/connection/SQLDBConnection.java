package nl.han.ica.oose.dea.spotitube.data.connection;

import javax.enterprise.inject.Default;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Default
public class SQLDBConnection implements IDBConnection {
    private DBConnectionCredentials dbcc;

    public SQLDBConnection() {
        dbcc = new DBConnectionCredentials("database");
        try{
            Class.forName(dbcc.driver());
        }catch (ClassNotFoundException e){
            Logger.getLogger(DBConnectionCredentials.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbcc.host() +  "/" + dbcc.database() + "?user=" + dbcc.user() + "&password=" + dbcc.password() + "&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
    }

    public void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }
}

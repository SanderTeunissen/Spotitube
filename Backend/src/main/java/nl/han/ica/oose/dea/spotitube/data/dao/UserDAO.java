package nl.han.ica.oose.dea.spotitube.data.dao;

import nl.han.ica.oose.dea.spotitube.data.connection.IDBConnection;
import nl.han.ica.oose.dea.spotitube.data.dao.interfaces.IUserDAO;
import nl.han.ica.oose.dea.spotitube.domain.User;
import nl.han.ica.oose.dea.spotitube.domain.interfaces.IUser;

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

public class UserDAO implements IUserDAO {
    @Inject private IDBConnection dbConnection;
    private Logger logger = Logger.getLogger(UserDAO.class.getName());

    @Override
    public Optional<IUser> getUser(String username) {
        try{
            Connection conn = dbConnection.getConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM Users WHERE Username = ?");
            statement.setString(1, username);
            ArrayList<IUser> foundUsers = getUsersFromDB(statement);
            if(foundUsers.size() == 1){
                return Optional.of(foundUsers.get(0));
            }else{
                return Optional.empty();
            }
        }catch (SQLException e){
            logger.log(Level.SEVERE, MessageFormat.format("SQL exception thrown: {0}", e.getMessage()));
            return Optional.empty();
        }
    }

    private ArrayList<IUser> getUsersFromDB(PreparedStatement statement) throws SQLException{
        ArrayList<IUser> users = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next())
        {
            users.add(getUserFromResultSet(resultSet));
        }
        return users;
    }

    private IUser getUserFromResultSet(ResultSet resultSet) throws SQLException{
        return new User(
                resultSet.getString("Username"),
                resultSet.getString("Password")
        );
    }
}

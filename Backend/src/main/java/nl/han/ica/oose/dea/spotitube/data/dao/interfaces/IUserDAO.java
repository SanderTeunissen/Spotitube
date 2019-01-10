package nl.han.ica.oose.dea.spotitube.data.dao.interfaces;

import nl.han.ica.oose.dea.spotitube.domain.interfaces.IUser;

import java.util.Optional;

public interface IUserDAO {
    Optional<IUser> getUser(String username);
}

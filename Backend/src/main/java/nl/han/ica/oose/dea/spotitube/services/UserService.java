package nl.han.ica.oose.dea.spotitube.services;

import nl.han.ica.oose.dea.spotitube.data.dao.UserDAO;
import nl.han.ica.oose.dea.spotitube.data.dao.interfaces.IUserDAO;
import nl.han.ica.oose.dea.spotitube.domain.User;
import nl.han.ica.oose.dea.spotitube.domain.interfaces.IUser;
import nl.han.ica.oose.dea.spotitube.exceptions.EntityNotFoundException;
import nl.han.ica.oose.dea.spotitube.services.interfaces.IUserService;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.Optional;

@Default
public class UserService implements IUserService {
    @Inject private IUserDAO userDAO = new UserDAO();

    public IUser getUser(String username) throws EntityNotFoundException {
        Optional<IUser> optionalUser = userDAO.getUser(username);
        return optionalUser.orElseThrow(() -> new EntityNotFoundException(User.class));
    }
}

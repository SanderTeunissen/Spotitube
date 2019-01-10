package nl.han.ica.oose.dea.spotitube.services.interfaces;

import nl.han.ica.oose.dea.spotitube.domain.interfaces.IUser;
import nl.han.ica.oose.dea.spotitube.exceptions.EntityNotFoundException;

public interface IUserService {
    IUser getUser(String username) throws EntityNotFoundException;
}

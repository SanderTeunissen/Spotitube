package nl.han.ica.oose.dea.spotitube.services.interfaces;

import nl.han.ica.oose.dea.spotitube.domain.interfaces.IAuthenticationToken;
import nl.han.ica.oose.dea.spotitube.domain.interfaces.IUser;

import java.util.Optional;

public interface IAuthenticationService {
    IAuthenticationToken getTokenForUser(IUser user);
    Optional<IAuthenticationToken> getByToken(String token);
}

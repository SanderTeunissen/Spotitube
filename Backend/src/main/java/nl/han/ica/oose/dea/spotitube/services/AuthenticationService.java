package nl.han.ica.oose.dea.spotitube.services;

import nl.han.ica.oose.dea.spotitube.domain.AuthenticationToken;
import nl.han.ica.oose.dea.spotitube.domain.interfaces.IAuthenticationToken;
import nl.han.ica.oose.dea.spotitube.domain.interfaces.IUser;
import nl.han.ica.oose.dea.spotitube.services.interfaces.IAuthenticationService;

import javax.enterprise.inject.Default;
import java.util.ArrayList;
import java.util.Optional;

@Default
public class AuthenticationService implements IAuthenticationService {
    private static ArrayList<IAuthenticationToken> tokens = new ArrayList<>();

    public IAuthenticationToken getTokenForUser(IUser user){
        Optional<IAuthenticationToken> optionalAuthenticationToken = getOptionalExistingTokeForUser(user);
        return optionalAuthenticationToken.orElse(createTokenForUser(user));
    }

    public Optional<IAuthenticationToken> getByToken(String token){
        for(IAuthenticationToken authToken : AuthenticationService.tokens){
            if(authToken.getToken().equals(token)){
                return Optional.of(authToken);
            }
        }
        return Optional.empty();
    }

    private Optional<IAuthenticationToken> getOptionalExistingTokeForUser(IUser user){
        for(IAuthenticationToken token : AuthenticationService.tokens){
            if(token.getUsername().equals(user.getUsername())){
                return Optional.of(token);
            }
        }
        return Optional.empty();
    }

    private IAuthenticationToken createTokenForUser(IUser user){
        IAuthenticationToken newToken = new AuthenticationToken(user.getUsername());
        AuthenticationService.tokens.add(newToken);
        return newToken;
    }
}

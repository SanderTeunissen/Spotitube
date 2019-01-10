package nl.han.ica.oose.dea.spotitube.services;

import nl.han.ica.oose.dea.spotitube.domain.User;
import nl.han.ica.oose.dea.spotitube.domain.interfaces.IAuthenticationToken;
import nl.han.ica.oose.dea.spotitube.domain.interfaces.IUser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class AuthenticationServiceTest {
    private final IUser testUser = new User("testUser","testPassword");
    private AuthenticationService authenticationService = new AuthenticationService();

    @Test
    public void testAuthenticationToken(){
        IAuthenticationToken token = authenticationService.getTokenForUser(testUser);
        IAuthenticationToken byToken = authenticationService.getByToken(token.getToken()).orElse(null);
        assertEquals("Token has correct length", 50, token.getToken().length());
        assertEquals("Returns same token everytime for user", token.getToken(), authenticationService.getTokenForUser(testUser).getToken());
        assertEquals("Returns token object by token", token, byToken);
    }

    @Test
    public void testEmptyOptionalOnUnknownGetByToken(){
        assertNull("Empty optional on unknown token", authenticationService.getByToken("unknownToken").orElse(null));
    }
}

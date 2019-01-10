package nl.han.ica.oose.dea.spotitube.controllers;

import nl.han.ica.oose.dea.spotitube.controllers.dto.LoginCredentials;
import nl.han.ica.oose.dea.spotitube.controllers.dto.SignInUserResponse;
import nl.han.ica.oose.dea.spotitube.controllers.rest.LoginRestController;
import nl.han.ica.oose.dea.spotitube.domain.AuthenticationToken;
import nl.han.ica.oose.dea.spotitube.domain.User;
import nl.han.ica.oose.dea.spotitube.domain.interfaces.IUser;
import nl.han.ica.oose.dea.spotitube.exceptions.EntityNotFoundException;
import nl.han.ica.oose.dea.spotitube.services.interfaces.IAuthenticationService;
import nl.han.ica.oose.dea.spotitube.services.interfaces.IUserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.ws.rs.core.Response;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class LoginRESTControllerTest {
    @Mock
    private IUserService userService;

    @Mock
    private IAuthenticationService authenticationService;

    @InjectMocks
    private LoginRestController loginRestController;

    @Before
    public void setUp(){
        loginRestController = new LoginRestController();
        initMocks(this);
    }

    @Test
    public void testValidLogin() throws Exception{
        IUser user = mock(User.class);
        when(user.getUsername()).thenReturn("testUser");
        when(user.getPassword()).thenReturn("testPassword");

        AuthenticationToken token = mock(AuthenticationToken.class);
        when(token.getUsername()).thenReturn("testUser");
        when(token.getToken()).thenReturn("secretToken");

        when(userService.getUser("testUser")).thenReturn(user);
        when(authenticationService.getTokenForUser(user)).thenReturn(token);

        LoginCredentials loginCredentials = new LoginCredentials("testUser","testPassword");

        Response response = loginRestController.SignIn(loginCredentials);
        assertEquals("Response status code equals 200",200, response.getStatus());
        assertEquals("Correct username in response", "testUser", ((SignInUserResponse)response.getEntity()).getUser());
        assertEquals("Correct token in response", "secretToken", ((SignInUserResponse)response.getEntity()).getToken());
    }

    @Test
    public void testUnvalidLogin() throws Exception{
        IUser user = mock(User.class);
        when(user.getUsername()).thenReturn("testUser");
        when(user.getPassword()).thenReturn("testPassword");

        when(userService.getUser("testUser")).thenReturn(user);

        LoginCredentials loginCredentials = new LoginCredentials("testUser","notValidPassword");

        Response response = loginRestController.SignIn(loginCredentials);
        assertEquals("Response status code equals 403",403, response.getStatus());
    }

    @Test
    public void testUnknownUser() throws Exception{
        when(userService.getUser("testUnknownUser")).thenThrow(EntityNotFoundException.class);

        LoginCredentials loginCredentials = new LoginCredentials("testUnknownUser","testPassword");

        Response response = loginRestController.SignIn(loginCredentials);
        assertEquals("Response status code equals 404",404, response.getStatus());
    }
}

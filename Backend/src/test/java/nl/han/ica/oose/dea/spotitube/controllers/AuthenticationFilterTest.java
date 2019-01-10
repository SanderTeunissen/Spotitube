package nl.han.ica.oose.dea.spotitube.controllers;

import nl.han.ica.oose.dea.spotitube.annotations.authentication.AuthenticatedFilter;
import nl.han.ica.oose.dea.spotitube.domain.AuthenticationToken;
import nl.han.ica.oose.dea.spotitube.services.AuthenticationService;
import nl.han.ica.oose.dea.spotitube.services.interfaces.IAuthenticationService;
import org.junit.*;
import org.mockito.*;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response;

import java.util.Optional;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthenticationFilterTest {
    private final String userToken = "gd7t64n4jg6j5r6lq3vhwqpt1xcsykygd1cadtc2st8k8dlbve";

    @Mock
    private IAuthenticationService authenticationTokenService;

    @Mock
    private ContainerRequestContext containerRequestContext;

    @InjectMocks
    private AuthenticatedFilter authenticatedFilter;

    @Before
    public void setUp() {
        authenticatedFilter = new AuthenticatedFilter();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAuthenticatedFilter() {
        UriInfo uriInfo = mock(UriInfo.class);
        MultivaluedMap map = mock(MultivaluedMap.class);
        when(containerRequestContext.getUriInfo()).thenReturn(uriInfo);
        when(uriInfo.getQueryParameters()).thenReturn(map);
        when(map.getFirst("token")).thenReturn(userToken);

        AuthenticationToken token = mock(AuthenticationToken.class);
        when(token.getUsername()).thenReturn("testUser");

        when(authenticationTokenService.getByToken(userToken)).thenReturn(Optional.of(token));

        authenticatedFilter.filter(containerRequestContext);
        verify(containerRequestContext).setProperty("currentUserName", "testUser");
    }

    @Test
    public void testNotAuthenticatedFilter() {
        UriInfo uriInfo = mock(UriInfo.class);
        MultivaluedMap map = mock(MultivaluedMap.class);
        when(containerRequestContext.getUriInfo()).thenReturn(uriInfo);
        when(uriInfo.getQueryParameters()).thenReturn(map);
        when(map.getFirst("token")).thenReturn(userToken);

        when(authenticationTokenService.getByToken(userToken)).thenReturn(Optional.empty());

        authenticatedFilter.filter(containerRequestContext);
        verify(containerRequestContext).abortWith(any(Response.class));
    }
}

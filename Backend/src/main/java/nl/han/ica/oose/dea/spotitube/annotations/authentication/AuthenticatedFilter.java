package nl.han.ica.oose.dea.spotitube.annotations.authentication;

import nl.han.ica.oose.dea.spotitube.domain.interfaces.IAuthenticationToken;
import nl.han.ica.oose.dea.spotitube.services.interfaces.IAuthenticationService;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Provider
@Authenticated
@Priority(Priorities.AUTHENTICATION)
public class AuthenticatedFilter implements ContainerRequestFilter {
    @Inject
    private IAuthenticationService authenticationTokenService;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) {
        String requestToken = containerRequestContext.getUriInfo().getQueryParameters().getFirst("token");
        Optional<IAuthenticationToken> optionalAuthenticationToken = authenticationTokenService.getByToken(requestToken);
        if (!optionalAuthenticationToken.isPresent()) {
            unAuthorizeRequest(containerRequestContext);
            return;
        }
        String currentUserName = optionalAuthenticationToken.get().getUsername();
        containerRequestContext.setProperty("currentUserName", currentUserName);
    }

    private void unAuthorizeRequest(ContainerRequestContext containerRequestContext) {
        Response response = Response.status(401).build();
        containerRequestContext.abortWith(response);
    }
}

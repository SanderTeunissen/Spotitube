package nl.han.ica.oose.dea.spotitube.controllers.rest;

import nl.han.ica.oose.dea.spotitube.controllers.dto.LoginCredentials;
import nl.han.ica.oose.dea.spotitube.controllers.dto.SignInUserResponse;
import nl.han.ica.oose.dea.spotitube.domain.User;
import nl.han.ica.oose.dea.spotitube.domain.interfaces.IUser;
import nl.han.ica.oose.dea.spotitube.exceptions.EntityNotFoundException;
import nl.han.ica.oose.dea.spotitube.services.AuthenticationService;
import nl.han.ica.oose.dea.spotitube.services.interfaces.IAuthenticationService;
import nl.han.ica.oose.dea.spotitube.services.interfaces.IUserService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
@Singleton
public class LoginRestController {
    @Inject
    private IUserService userService;

    @Inject
    private IAuthenticationService authenticationService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response SignIn(LoginCredentials loginCredentials){
        try{
            IUser user = userService.getUser(loginCredentials.getUser());

            if(!user.getPassword().equals(loginCredentials.getPassword())){
                return Response.status(403).build();
            }

            return Response.status(200).entity(new SignInUserResponse(user.getUsername(), authenticationService.getTokenForUser(user).getToken())).build();
        }catch (EntityNotFoundException e){
            return Response.status(404).build();
        }
    }
}

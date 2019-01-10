package nl.han.ica.oose.dea.spotitube.services;

import nl.han.ica.oose.dea.spotitube.data.dao.interfaces.IUserDAO;
import nl.han.ica.oose.dea.spotitube.domain.User;
import nl.han.ica.oose.dea.spotitube.exceptions.EntityNotFoundException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class UserServiceTest {
    @Mock
    private IUserDAO userDAO;

    @InjectMocks
    private UserService userService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindingExistingUser(){
        try{
            when(userDAO.getUser("testUser")).thenReturn(Optional.of(new User("testUser", "testPassword")));
            assertEquals("Optional contains correct user","testUser",userService.getUser("testUser").getUsername());
        }
        catch(EntityNotFoundException e){
            fail("EntityNotFound exception thrown");
        }
    }

    @Test(expected = EntityNotFoundException.class)
    public void testFindingNonExistingUser() throws EntityNotFoundException{
        when(userDAO.getUser("testUnknownUser")).thenReturn(Optional.empty());
        userService.getUser("testUnknownUser");
    }
}

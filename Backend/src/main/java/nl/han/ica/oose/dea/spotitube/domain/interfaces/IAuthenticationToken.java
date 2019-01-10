package nl.han.ica.oose.dea.spotitube.domain.interfaces;

public interface IAuthenticationToken {
    String getUsername();
    void setUsername(String username);
    String getToken();
    void setToken(String token);
}

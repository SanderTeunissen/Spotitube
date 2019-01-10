package nl.han.ica.oose.dea.spotitube.controllers.dto;

public class LoginCredentials {
    private String user;
    private String password;

    public LoginCredentials() {}

    public LoginCredentials(String user, String password) {
        setUser(user);
        setPassword(password);
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package nl.han.ica.oose.dea.spotitube.controllers.dto;

public class SignInUserResponse {
    private String user;
    private String token;

    public SignInUserResponse() {}

    public SignInUserResponse(String user, String token) {
        this.user = user;
        this.token = token;
    }

    public String getUser() {
        return user;
    }

    public String getToken() {
        return token;
    }
}

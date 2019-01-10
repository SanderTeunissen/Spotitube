package nl.han.ica.oose.dea.spotitube.domain;

import nl.han.ica.oose.dea.spotitube.domain.interfaces.IAuthenticationToken;

public class AuthenticationToken implements IAuthenticationToken {
    private String username;
    private String token;

    public AuthenticationToken(String username){
        setUsername(username);
        generateToken();
    }

    public AuthenticationToken(String username, String token) {
        setUsername(username);
        setToken(token);
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public void setToken(String token) {
        this.token = token;
    }

    private void generateToken(){
        String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder builder = new StringBuilder();
        int count = 50;
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        this.token = builder.toString();
    }
}

package com.jonas.jonasbank.auth;

public class AuthenticationResponse {

    private String token;
    private String user;

    public AuthenticationResponse() {
    }

    public AuthenticationResponse(String token, String user) {
        this.token = token;
        this.user = user;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

//    public static AuthenticationResponse of (String token, User user) {
//        return new AuthenticationResponse(token, user);
//    }

}
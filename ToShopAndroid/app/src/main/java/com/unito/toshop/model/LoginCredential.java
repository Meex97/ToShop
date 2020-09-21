package com.unito.toshop.model;

import java.io.Serializable;

public class LoginCredential implements Serializable {

    private String username;
    private String password;
    private String gooogleIdToken;

    public LoginCredential(String username, String password) {
        this.username = username;
        this.password = password;
        this.gooogleIdToken = null;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGooogleIdToken() {
        return gooogleIdToken;
    }

    public void setGooogleIdToken(String gooogleIdToken) {
        this.gooogleIdToken = gooogleIdToken;
    }
}

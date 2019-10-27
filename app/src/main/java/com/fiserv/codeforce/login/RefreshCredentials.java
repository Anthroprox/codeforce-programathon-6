package com.fiserv.codeforce.login;


public class RefreshCredentials {

    String refreshToken;

    public RefreshCredentials() {
    }

    public RefreshCredentials(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}

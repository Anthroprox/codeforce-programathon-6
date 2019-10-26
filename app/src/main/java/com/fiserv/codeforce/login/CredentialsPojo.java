package com.fiserv.codeforce.login;

import org.androidannotations.annotations.EBean;

@EBean
public class CredentialsPojo {
    String Username;
    String Password;

    public String getUsername() {
        return Username;
    }

    public CredentialsPojo setUsername(String username) {
        Username = username;
        return this;
    }

    public String getPassword() {
        return Password;
    }

    public CredentialsPojo setPassword(String password) {
        Password = password;
        return this;
    }
}

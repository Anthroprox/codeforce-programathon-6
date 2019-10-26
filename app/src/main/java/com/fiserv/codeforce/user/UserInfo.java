package com.fiserv.codeforce.user;

import org.androidannotations.annotations.EBean;

@EBean
public class UserInfo {

    Integer uid;
    String givenName;
    String email;
    String role;

    public Integer getUid() {
        return uid;
    }

    public UserInfo setUid(Integer uid) {
        this.uid = uid;
        return this;
    }

    public String getGivenName() {
        return givenName;
    }

    public UserInfo setGivenName(String givenName) {
        this.givenName = givenName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserInfo setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getRole() {
        return role;
    }

    public UserInfo setRole(String role) {
        this.role = role;
        return this;
    }
}

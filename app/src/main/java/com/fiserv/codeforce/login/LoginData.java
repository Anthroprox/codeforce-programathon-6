package com.fiserv.codeforce.login;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.androidannotations.annotations.EBean;

@EBean
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginData {

    String access_token;
    Integer expires_in;
    String token_type;
    String refresh_token;
    String scope;

    public LoginData clone() {
        return new LoginData()
                .setAccess_token(this.access_token)
                .setExpires_in(this.expires_in)
                .setToken_type(this.token_type)
                .setRefresh_token(this.refresh_token)
                .setScope(this.scope)
                ;
    }

    public String getAccess_token() {
        return access_token;
    }

    public LoginData setAccess_token(String access_token) {
        this.access_token = access_token;
        return this;
    }

    public Integer getExpires_in() {
        return expires_in;
    }

    public LoginData setExpires_in(Integer expires_in) {
        this.expires_in = expires_in;
        return this;
    }

    public String getToken_type() {
        return token_type;
    }

    public LoginData setToken_type(String token_type) {
        this.token_type = token_type;
        return this;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public LoginData setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
        return this;
    }

    public String getScope() {
        return scope;
    }

    public LoginData setScope(String scope) {
        this.scope = scope;
        return this;
    }
}

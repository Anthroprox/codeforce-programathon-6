package com.fiserv.codeforce.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiserv.codeforce.user.UserInfo;

import org.androidannotations.annotations.EBean;

@EBean
public class ResponseLogin {

    @JsonProperty("LoginData")
    LoginData loginData;
    @JsonProperty("UserInfo")
    UserInfo userInfo;

    public LoginData getLoginData() {
        return loginData;
    }

    public ResponseLogin setLoginData(LoginData loginData) {
        this.loginData = loginData;
        return this;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public ResponseLogin setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
        return this;
    }
}


//    String accessToken;
//    String identityToken;
//    String tokenType;
//    String refreshToken;
//    String errorDescription;
//    Integer expiresIn;
//    String raw;
//    String json;
//    String exception;
//    Boolean isError;
//    String errorType;
//    String httpStatusCode;
//    String httpErrorReason;
//    String error;
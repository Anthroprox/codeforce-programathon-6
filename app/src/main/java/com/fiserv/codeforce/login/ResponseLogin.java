package com.fiserv.codeforce.login;

import org.androidannotations.annotations.EBean;

@EBean
public class ResponseLogin {
    String accessToken;
    String identityToken;
    String tokenType;
    String refreshToken;
    String errorDescription;
    Integer expiresIn;
    String raw;
    String json;
    String exception;
    Boolean isError;
    String errorType;
    String httpStatusCode;
    String httpErrorReason;
    String error;

}

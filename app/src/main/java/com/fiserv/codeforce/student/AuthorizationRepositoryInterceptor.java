package com.fiserv.codeforce.student;

import com.fiserv.codeforce.GlobalContainer;
import com.fiserv.codeforce.login.LoginData;
import com.fiserv.codeforce.login.LoginRepository;
import com.fiserv.codeforce.login.RefreshCredentials;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.rest.spring.annotations.RestService;
import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

@EBean(scope = EBean.Scope.Singleton)
public class AuthorizationRepositoryInterceptor implements ClientHttpRequestInterceptor {

    @Bean
    GlobalContainer container;

    @RestService
    LoginRepository loginRepository;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        HttpHeaders headers = request.getHeaders();
        HttpAuthentication auth = new HttpAuthentication() {
            @Override
            public String getHeaderValue() {
                return "Bearer " + container.getLoginData().getAccess_token();
               // return "Bearer " + "eyJhbGciOiJSUzI1NiIsImtpZCI6IjlCQ0U2OTc1MDUzMkU3QjNEOUU3MkU4ODcwOTZENDk2RUEyNzdBOEIiLCJ0eXAiOiJKV1QiLCJ4NXQiOiJtODVwZFFVeTU3UFo1eTZJY0piVWx1b25lb3MifQ.eyJuYmYiOjE1NzIxODkyNTcsImV4cCI6MTU3MjE5Mjg1NywiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDoyNTI4MS9hdXRoc2VydmVyIiwiYXVkIjpbImh0dHA6Ly9sb2NhbGhvc3Q6MjUyODEvYXV0aHNlcnZlci9yZXNvdXJjZXMiLCJEZWh2aUFQSSJdLCJjbGllbnRfaWQiOiJkZWh2aV9hcGkiLCJzdWIiOiIxIiwiYXV0aF90aW1lIjoxNTcyMTg5MjU3LCJpZHAiOiJsb2NhbCIsImdpdmVuX25hbWUiOiJZb25hdGFuIExlaXRvbiIsImVtYWlsIjoiamFxbG91aTFAZ21haWwuY29tIiwicm9sZSI6IjUiLCJ6b25laW5mbyI6IjEiLCJzY29wZSI6WyJlbWFpbCIsIm9wZW5pZCIsInJvbGUiLCJhcGkucmVzb3VyY2UiLCJvZmZsaW5lX2FjY2VzcyJdLCJhbXIiOlsicHdkIl19.d2xewU2Ks9WUORmyzEBXmxhCncIXNeWiKMeEAP0B4xwgAM44slCKuvNFz7bv-mCawPxItDxaBwfhBP3nIdkRfpnkHxamPmfwYnUmBhtNQcfWhiBDpREbvy8b56XNMfU2QqnIsA9hINAn9wLG1kl4VQlip1oQWaDwUg_WYZbGidSIBzEKFp-WotnPzrW_edTxsi8aztezqV9aYTVOpWlTBu2RGXfQY0L1fRQGw0BKc9imSstQl9OggGXvozRa1q2bVA8mYt9QRKz7i0MsVE4uYh55ofEi1z06CQGVO6Da-MANTWrWlVzB7IyUTbVevVu2x9dUAlaMdHfqOnxfoldbzQ";
            }
        };
        headers.setAuthorization(auth);
        try {
            return execution.execute(request, body);
        } catch (IOException e) {
            ResponseEntity<LoginData> refresh = loginRepository.refresh(new RefreshCredentials(container.getLoginData().getRefresh_token()));
            container.setLoginData(refresh.getBody());

            HttpAuthentication authRefresh = new HttpAuthentication() {
                @Override
                public String getHeaderValue() {
                    return "Bearer " + container.getLoginData().getAccess_token();
                }
            };
            headers.setAuthorization(auth);

            return execution.execute(request, body);
        }
    }
}

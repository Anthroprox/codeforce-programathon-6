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

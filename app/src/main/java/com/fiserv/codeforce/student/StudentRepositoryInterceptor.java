package com.fiserv.codeforce.student;

import org.androidannotations.annotations.EBean;
import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

@EBean(scope = EBean.Scope.Singleton)
public class StudentRepositoryInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        HttpHeaders headers = request.getHeaders();
        HttpAuthentication auth = new HttpAuthentication(){
            @Override
            public String getHeaderValue() {
                return "Bearer " +"eyJhbGciOiJSUzI1NiIsImtpZCI6IjlCQ0U2OTc1MDUzMkU3QjNEOUU3MkU4ODcwOTZENDk2RUEyNzdBOEIiLCJ0eXAiOiJKV1QiLCJ4NXQiOiJtODVwZFFVeTU3UFo1eTZJY0piVWx1b25lb3MifQ.eyJuYmYiOjE1NzIxNDEyNDQsImV4cCI6MTU3MjE0NDg0NCwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDoyNTI4MS9hdXRoc2VydmVyIiwiYXVkIjpbImh0dHA6Ly9sb2NhbGhvc3Q6MjUyODEvYXV0aHNlcnZlci9yZXNvdXJjZXMiLCJEZWh2aUFQSSJdLCJjbGllbnRfaWQiOiJkZWh2aV9hcGkiLCJzdWIiOiIxIiwiYXV0aF90aW1lIjoxNTcyMTQxMjQ0LCJpZHAiOiJsb2NhbCIsImdpdmVuX25hbWUiOiJZb25hdGFuIExlaXRvbiIsImVtYWlsIjoiamFxbG91aTFAZ21haWwuY29tIiwicm9sZSI6IjUiLCJ6b25laW5mbyI6IjEiLCJzY29wZSI6WyJlbWFpbCIsIm9wZW5pZCIsInJvbGUiLCJhcGkucmVzb3VyY2UiLCJvZmZsaW5lX2FjY2VzcyJdLCJhbXIiOlsicHdkIl19.FP8GAEQzq6rs9YSEH1kUW4afVJyPvoZXWeKGfqOXx2Tep9ofDbVzAmXjU706N3Se9FnfR-IrUtEPxqoDst5JcUTmiR8oidhCVDMmncrfR_IQ_RrVntNj1M4iP-AGiLei0aHWeAoVXuXRHjPqdcM9NbV5uqis5n3N-i4uvtkP3vcC_DzuX3Ze_6pTKmNCMCenAfjYYSLYOPekpuRVCtxFmiBcTqVoFcCYUSRGis_6nNw39S1vLjphNa2daCWyboQLn_ZcJcAte4MqjGRHTTAnRChQidnEVz9MXExLNqgl9OqzP6yE7mBJdjJdRWw5QLwzX_XB9_GKrVIBleKaS1xJsA";
            }
        };
        headers.setAuthorization(auth);
        return execution.execute(request, body);
    }
}

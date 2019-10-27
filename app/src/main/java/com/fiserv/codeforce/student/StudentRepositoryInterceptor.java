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
                return "Bearer " +"eyJhbGciOiJSUzI1NiIsImtpZCI6IjlCQ0U2OTc1MDUzMkU3QjNEOUU3MkU4ODcwOTZENDk2RUEyNzdBOEIiLCJ0eXAiOiJKV1QiLCJ4NXQiOiJtODVwZFFVeTU3UFo1eTZJY0piVWx1b25lb3MifQ.eyJuYmYiOjE1NzIxMzcyMzQsImV4cCI6MTU3MjE0MDgzNCwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDoyNTI4MS9hdXRoc2VydmVyIiwiYXVkIjpbImh0dHA6Ly9sb2NhbGhvc3Q6MjUyODEvYXV0aHNlcnZlci9yZXNvdXJjZXMiLCJEZWh2aUFQSSJdLCJjbGllbnRfaWQiOiJkZWh2aV9hcGkiLCJzdWIiOiIxIiwiYXV0aF90aW1lIjoxNTcyMTM3MjM0LCJpZHAiOiJsb2NhbCIsImdpdmVuX25hbWUiOiJZb25hdGFuIExlaXRvbiIsImVtYWlsIjoiamFxbG91aTFAZ21haWwuY29tIiwicm9sZSI6IjUiLCJ6b25laW5mbyI6IjEiLCJzY29wZSI6WyJlbWFpbCIsIm9wZW5pZCIsInJvbGUiLCJhcGkucmVzb3VyY2UiLCJvZmZsaW5lX2FjY2VzcyJdLCJhbXIiOlsicHdkIl19.bIDJIfUGW88kxVfgRDlM-1U9lMGwBzwAsRgmmUhnblg-KLSokGqE_XDH3yLe2zylmVeaZG2sIJZoKeGRdBOIJ9KmnHwsSKT6ld1KwzeDgTyxlZ16fyj3Pb7Ds4jqIDBGZDKCt5FAb0Z2R2AamRgR8eRaTm8hB8atdYbVTOpwIq-xP0mRuK-1ckKQ_CcRHOhMnBlUbN0tgCHxlEJvXcMMV-L2oWOa3-jqomERM49-abQKvDbpI-AbfYP6VXXuyg_VnyHPseBFZv0rFb60Y8dE4T1oavamj_pP7SIuOT96ND_RScG3Rm4UDacEFhkPnGbE3TkhqWjA9dU5jCI7gRAdRg";
            }
        };
        headers.setAuthorization(auth);
        return execution.execute(request, body);
    }
}

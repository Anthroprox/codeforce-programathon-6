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
                return "Bearer " +"eyJhbGciOiJSUzI1NiIsImtpZCI6IjlCQ0U2OTc1MDUzMkU3QjNEOUU3MkU4ODcwOTZENDk2RUEyNzdBOEIiLCJ0eXAiOiJKV1QiLCJ4NXQiOiJtODVwZFFVeTU3UFo1eTZJY0piVWx1b25lb3MifQ.eyJuYmYiOjE1NzIxMzE0NTUsImV4cCI6MTU3MjEzNTA1NSwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDoyNTI4MS9hdXRoc2VydmVyIiwiYXVkIjpbImh0dHA6Ly9sb2NhbGhvc3Q6MjUyODEvYXV0aHNlcnZlci9yZXNvdXJjZXMiLCJEZWh2aUFQSSJdLCJjbGllbnRfaWQiOiJkZWh2aV9hcGkiLCJzdWIiOiIxIiwiYXV0aF90aW1lIjoxNTcyMTMxNDU1LCJpZHAiOiJsb2NhbCIsImdpdmVuX25hbWUiOiJZb25hdGFuIExlaXRvbiIsImVtYWlsIjoiamFxbG91aTFAZ21haWwuY29tIiwicm9sZSI6IjUiLCJ6b25laW5mbyI6IjEiLCJzY29wZSI6WyJlbWFpbCIsIm9wZW5pZCIsInJvbGUiLCJhcGkucmVzb3VyY2UiLCJvZmZsaW5lX2FjY2VzcyJdLCJhbXIiOlsicHdkIl19.PgNouEIxOX4eAWKjBZWITuaut91SeQhdUMrSN-PZdmu4N6muUeGlxKSNfDEWydLjoj8KVIrOach7l6-cRi5pjss91g_PncztVhjQ-eu-487LrG2jtUtZ-aMOhOSaNhSKHgqMSbKFmT2bD_xvJf5mIO9qv-SZAamE6CziqKBkVeqkWqLObvJrxg_2TVIiz8tug6j-tf5xlme7TPi_exVqw0f9BQOWZOhrf-xMCP9cJ17dW5Vt6rWcT99kkYKDSU2ta6k_vtj8P2e5Xnytiy1E5ASYECm1IZRscaOpBaTq7IQSzfweA4qo8WYyfNt1rBpkE4t6sgcxnDXxSlDLPHVM0g";
            }
        };
        headers.setAuthorization(auth);
        return execution.execute(request, body);
    }
}

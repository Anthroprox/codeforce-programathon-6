package com.fiserv.codeforce.form;

import com.fiserv.codeforce.student.AuthorizationRepositoryInterceptor;
import com.fiserv.codeforce.student.Form;

import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.Rest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Rest(rootUrl = "http://192.168.128.21:8171/ApiServer/api/", converters = {MappingJackson2HttpMessageConverter.class}, interceptors = AuthorizationRepositoryInterceptor.class)
public interface FormRepository {

    @Get("/Form/GetById?formHeaderId={id}")
    ResponseEntity<FullFormData> getById(@Path("id") int id);
}

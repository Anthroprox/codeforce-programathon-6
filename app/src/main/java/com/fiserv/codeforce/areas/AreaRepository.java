package com.fiserv.codeforce.areas;

import com.fiserv.codeforce.student.AuthorizationRepositoryInterceptor;

import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.Rest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Rest(rootUrl = "http://192.168.128.22:25281/ApiServer/api/", converters = {MappingJackson2HttpMessageConverter.class}, interceptors = AuthorizationRepositoryInterceptor.class)
public interface AreaRepository {

    @Get("/Areas/GetById?id={id}")
    public ResponseEntity<Area> getById(@Path("id") Integer id);

}

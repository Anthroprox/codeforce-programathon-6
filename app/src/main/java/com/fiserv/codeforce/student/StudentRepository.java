package com.fiserv.codeforce.student;

import android.Manifest;

import androidx.annotation.RequiresPermission;

import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.Rest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Rest(rootUrl = "http://192.168.128.21:8171/ApiServer/api/", converters = {MappingJackson2HttpMessageConverter.class}, interceptors = AuthorizationRepositoryInterceptor.class)
public interface StudentRepository {

    @RequiresPermission(Manifest.permission.INTERNET)
    @Get("/Student/GetMyStudents")
    ResponseEntity<ListStudent> GetMyStudents();


    @RequiresPermission(Manifest.permission.INTERNET)
    @Get("/Student/GetByDni?dni={val}")
    ResponseEntity<StudentPojo> GetByDni(@Path("val") int val);


}

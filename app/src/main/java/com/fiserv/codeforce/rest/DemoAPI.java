package com.fiserv.codeforce.rest;

import android.Manifest;

import androidx.annotation.RequiresPermission;

import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.Rest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Rest(rootUrl = "https://jsonplaceholder.typicode.com", converters = {MappingJackson2HttpMessageConverter.class})
public interface DemoAPI {

    @RequiresPermission(Manifest.permission.INTERNET)
    @Get("/todos/{id}")
    ResponseEntity<Response> test(@Path int id);
}

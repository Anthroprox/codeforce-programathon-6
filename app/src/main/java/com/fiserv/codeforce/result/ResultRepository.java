package com.fiserv.codeforce.result;

import android.Manifest;

import androidx.annotation.RequiresPermission;


import com.fiserv.codeforce.login.ResponseLogin;

import org.androidannotations.rest.spring.annotations.Body;
import org.androidannotations.rest.spring.annotations.Post;
import org.androidannotations.rest.spring.annotations.Rest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Rest(rootUrl = "http://192.168.128.22:25281/ApiServer/api/", converters = {MappingJackson2HttpMessageConverter.class})
public interface ResultRepository {

    @RequiresPermission(Manifest.permission.INTERNET)
    @Post("/Result/AddResults")
    ResponseEntity addResults(@Body ResultMatrixParameter resultMatrixParameter);
}

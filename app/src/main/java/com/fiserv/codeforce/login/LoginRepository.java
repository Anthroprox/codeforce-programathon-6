package com.fiserv.codeforce.login;

import android.Manifest;

import androidx.annotation.RequiresPermission;

import com.fiserv.codeforce.rest.Response;

import org.androidannotations.rest.spring.annotations.Body;
import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.Post;
import org.androidannotations.rest.spring.annotations.Rest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Rest(rootUrl = "http://192.168.128.22:25281/ApiServer/api/", converters = {MappingJackson2HttpMessageConverter.class})
public interface LoginRepository {

    @RequiresPermission(Manifest.permission.INTERNET)
    @Post("/LogIn")
    ResponseEntity<ResponseLogin> login(@Body CredentialsPojo credentials);


    @RequiresPermission(Manifest.permission.INTERNET)
    @Post("/LogIn/RefreshToken")
    ResponseEntity<LoginData> refresh(@Body RefreshCredentials credentials);
}

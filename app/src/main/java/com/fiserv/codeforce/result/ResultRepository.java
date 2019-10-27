package com.fiserv.codeforce.result;

import android.Manifest;

import androidx.annotation.RequiresPermission;


import com.fiserv.codeforce.login.ResponseLogin;
import com.fiserv.codeforce.student.AuthorizationRepositoryInterceptor;

import org.androidannotations.rest.spring.annotations.Body;
import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.Post;
import org.androidannotations.rest.spring.annotations.Rest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Rest(rootUrl = "http://192.168.128.21:8171/ApiServer/api/", converters = {MappingJackson2HttpMessageConverter.class}, interceptors = AuthorizationRepositoryInterceptor.class)
public interface ResultRepository {

    @RequiresPermission(Manifest.permission.INTERNET)
    @Post("/Result/AddResults")
    ResponseEntity<Integer> addResults(@Body ResultMatrixParameter resultMatrixParameter);

    @Get("/Result/GetResultByAttendanceId?attendanceId={id}")
    ResponseEntity<ResultMatrixParameter> GetResultByAttendanceId(@Path("id") Integer id);
}

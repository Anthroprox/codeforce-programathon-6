package com.fiserv.codeforce.areas;

import com.fiserv.codeforce.attendance.Attendance;
import com.fiserv.codeforce.student.AuthorizationRepositoryInterceptor;

import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.Rest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.List;

@Rest(rootUrl = "http://192.168.128.21:8171/ApiServer/api/", converters = {MappingJackson2HttpMessageConverter.class}, interceptors = AuthorizationRepositoryInterceptor.class)
public interface AreasRepository {

    @Get("/Areas/GetAreaLimitsByFormId?formId={formId}")
    ResponseEntity<List<AreaLimit>> getByStudentId(@Path("formId") Integer formId);

}

package com.fiserv.codeforce.action_plan;

import com.fiserv.codeforce.areas.AreaLimit;
import com.fiserv.codeforce.student.AuthorizationRepositoryInterceptor;

import org.androidannotations.rest.spring.annotations.Body;
import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.Post;
import org.androidannotations.rest.spring.annotations.Put;
import org.androidannotations.rest.spring.annotations.Rest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.List;

@Rest(rootUrl = "http://192.168.128.21:8171/ApiServer/api/", converters = {MappingJackson2HttpMessageConverter.class}, interceptors = AuthorizationRepositoryInterceptor.class)
public interface ActionRepository {

    @Get("/ActionPlan")
    ResponseEntity<List<ActionPlan>> getActionPlan();

//    @Post("/ActionPlan/AddActionPlan")
//    ResponseEntity<Integer> AddActionPlan();

    @Put("/Attendance/AssignActionPlan?attendanceId={attendanceId}&&actionPlanId={actionPlanId}")
    ResponseEntity<Integer> assignActionPlan(@Path("attendanceId") Integer attendanceId, @Path("actionPlanId") Integer actionPlanId);

    @Post("/CustomAction/Add")
    ResponseEntity<Integer> addCustomAction(@Body CustomAction customAction);

    @Put("/CustomAction/Update")
    ResponseEntity<Integer> updateCustomAction(@Body CustomAction customAction);

}

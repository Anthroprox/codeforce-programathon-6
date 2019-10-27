package com.fiserv.codeforce.attendance;

import android.annotation.SuppressLint;
import com.fiserv.codeforce.action_plan.ActionPlan;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.fiserv.codeforce.student.AuthorizationRepositoryInterceptor;

import org.androidannotations.rest.spring.annotations.Body;
import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.Post;
import org.androidannotations.rest.spring.annotations.Rest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.List;
import java.util.NoSuchElementException;

@Rest(rootUrl = "http://192.168.128.21:8171/ApiServer/api/", converters = {MappingJackson2HttpMessageConverter.class}, interceptors = AuthorizationRepositoryInterceptor.class)
public interface AttendanceRepository {

    @Get("/Attendance/GetByStudentId?studentId={id}")
    ResponseEntity<List<Attendance>> getByStudentId(@Path("id") Integer id);

    @Post("/Attendance/AddAttendance")
    ResponseEntity<Integer> addAttendance(@Body Attendance attendance);
    @Get("/Attendance/GetActionPlans?attendanceId={id}")
    ResponseEntity<List<ActionPlan>> getActionByAttendanceId(@Path("id") Integer id);
}

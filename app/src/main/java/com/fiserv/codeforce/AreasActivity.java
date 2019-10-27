package com.fiserv.codeforce;

import androidx.annotation.ColorLong;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.fiserv.codeforce.areas.AreaResult;
import com.fiserv.codeforce.areas.AreasAdapter;
import com.fiserv.codeforce.attendance.Attendance;
import com.fiserv.codeforce.attendance.AttendanceRepository;
import com.fiserv.codeforce.form.FullFormData;
import com.fiserv.codeforce.result.ListColumnResults;
import com.fiserv.codeforce.result.ListResultRow;
import com.fiserv.codeforce.result.ResultCell;
import com.fiserv.codeforce.result.ResultMatrixParameter;
import com.fiserv.codeforce.result.ResultRepository;
import com.fiserv.codeforce.result.ResultRow;
import com.fiserv.codeforce.user.UserInfo;

import org.androidannotations.annotations.AfterExtras;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@EActivity(R.layout.activity_areas)
public class AreasActivity extends AppCompatActivity {

    @Bean
    AreasAdapter adapter;

    @Bean
    UserInfo userInfo;

    @Extra("Result")
    AreaResult result;

    @Extra("form")
    FullFormData fullFormData;

    @Extra("studentId")
    Integer studentID;

    @ViewById
    TextView txtDate;

    @RestService
    ResultRepository resultRepository;

    @RestService
    AttendanceRepository attendanceRepository;

    @AfterViews
    public void setDate() {
        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        txtDate.setText("FECHA APLICACION: " + date);
    }

    @Click(R.id.txtCom)
    void clickCom() {
        Intent i = new Intent(AreasActivity.this, QuestionAnswersActivity_.class);
        i.putExtra("result", adapter.getAreaResultByArea("Comunicación"));
        startActivity(i);
    }

    @Click(R.id.txtMG)
    void clickMG() {
        Intent i = new Intent(AreasActivity.this, QuestionAnswersActivity_.class);
        i.putExtra("result", adapter.getAreaResultByArea("Motora Gruesa"));
        startActivity(i);
    }

    @Click(R.id.txtMF)
    void clickMF() {
        Intent i = new Intent(AreasActivity.this, QuestionAnswersActivity_.class);
        i.putExtra("result", adapter.getAreaResultByArea("Motora Fina"));
        startActivity(i);
    }

    @Click(R.id.txtRP)
    void clickRP() {
        Intent i = new Intent(AreasActivity.this, QuestionAnswersActivity_.class);
        i.putExtra("result", adapter.getAreaResultByArea("Resolución de problemas"));
        startActivity(i);
    }

    @Click(R.id.txtSocial)
    void clickSocial() {
        Intent i = new Intent(AreasActivity.this, QuestionAnswersActivity_.class);
        i.putExtra("result", adapter.getAreaResultByArea("Socio-individual"));
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_areas);
    }

    @AfterExtras
    void chargeExtras() {
        if (result != null)
            adapter.getAreaResultByArea(result.getAreaName()).setValues(result.getValues());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Click(R.id.btnSaveAll)
    void clickSaveAll() {
        ResultMatrixParameter resultMatrixParameter = mapQuestionsToMatrixParameter();

        Attendance attendance = new Attendance()
//                .setId(0)
                .setDate(txtDate.getText().toString())
                .setFormId(fullFormData.getId())
                .setStudentId(studentID)
                .setApplicatorId(userInfo.getUid())
                .setStatus("None")
                .setForm(fullFormData)
                ;


        attendanceRepository.addAttendance(attendance);
        ResponseEntity<List<Attendance>> r = attendanceRepository.getByStudentId(studentID);
        if(r.getStatusCode() == HttpStatus.OK){
            List<Attendance> l = r.getBody();
            Integer attendaceID = attendanceRepository.maxAttendaceID(l);
            saveAllToAPI(
                    resultMatrixParameter
                            .setAttendanceId(attendaceID)
            );
        }

    }

    @Background
    public void saveAllToAPI(ResultMatrixParameter resultMatrixParameter){
        ResponseEntity r = resultRepository.addResults(resultMatrixParameter);
        if(!r.getStatusCode().equals(200))
            toastMessage("Datos insertados correctamente");
        else
            toastMessage("Error Insertando Datos");
    }

    @UiThread
    public void toastMessage(String message){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }

    @SuppressLint("NewApi")
    private ResultMatrixParameter mapQuestionsToMatrixParameter() {
        ListResultRow resultRows = new ListResultRow();
        AtomicInteger index = new AtomicInteger(0);

        resultRows.addAll(adapter
                .getResults()
                .stream()
                .map(result1 -> {
                    ListColumnResults l = new ListColumnResults();
                    for (int i = 0; i < result1.getValues().length; i++) {
                        ResultCell cell = new ResultCell()
                                .setId(index.getAndIncrement())
                                .setIndex(index.getAndIncrement())
                                .setValue(result1.getValues()[i]);
                        l.add(cell);
                    }

                    ResultRow r = new ResultRow()
                            .setAreaId(result1.getId())
                            .setResults(l);

                    return r;
                }).collect(Collectors.toList()))
        ;

        ResultMatrixParameter resultMatrixParameter = new ResultMatrixParameter()
//                .setAttendanceId(attendance)
                .setResultList(resultRows);

        return resultMatrixParameter;
    }


}

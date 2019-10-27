package com.fiserv.codeforce;

import android.annotation.SuppressLint;
import android.content.Context;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fiserv.codeforce.areas.Area;
import com.fiserv.codeforce.areas.AreaRepository;
import com.fiserv.codeforce.attendance.Attendance;
import com.fiserv.codeforce.attendance.AttendanceRepository;
import com.fiserv.codeforce.form.FormRepository;
import com.fiserv.codeforce.form.FullFormData;
import com.fiserv.codeforce.result.ResultMatrixParameter;
import com.fiserv.codeforce.result.ResultRepository;
import com.fiserv.codeforce.student.StudentPojo;
import com.fiserv.codeforce.student.StudentRepository;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.stream.Collectors.toList;

@EActivity(R.layout.activity_result_history)
public class ResultHistory extends AppCompatActivity {

    @RestService
    protected ResultRepository resultRepository;

    @RestService
    protected AreaRepository areaRepository;

    @RestService
    protected FormRepository formRepository;

    @RestService
    protected AttendanceRepository attendanceRepository;


    @RestService
    protected StudentRepository studentRepository;

    @Extra("examId")
    protected Integer examId;

    @Extra("studentDni")
    protected Integer studentDni;

    @ViewById(R.id.spinner2)
    protected Spinner spinner;

    @ViewById(R.id.editText_student_history)
    protected EditText name;
    @ViewById(R.id.editText_dbo_history)
    protected EditText dob;

    @ViewById(R.id.history_data)
    protected TableLayout table;

    private StudentPojo student;
    private List<Attendance> attendanceList;
    private HashMap<Integer, String> areas;

    @AfterViews
    public void afterView() {
        cleanData();
        loadData();
    }

    private void cleanData() {
        student = null;
        attendanceList = null;
        areas = new HashMap<>();
        cleanTable();
    }

    @SuppressLint("NewApi")
    @Background
    protected void loadData() {
        try {
            ResponseEntity<StudentPojo> studentResponse = studentRepository.GetByDni(studentDni);
            if (studentResponse.getStatusCode() == HttpStatus.OK)
                student = studentResponse.getBody();

            ResponseEntity<List<Attendance>> examResponse = attendanceRepository.getByStudentId(student.getId());

            if (examResponse.getStatusCode() == HttpStatus.OK)
                attendanceList = examResponse.getBody();

            ResponseEntity<List<Area>> areaResponse = areaRepository.getAllAreas();
            if (areaResponse.getStatusCode() == HttpStatus.OK)
                areaResponse.getBody().forEach(a -> {
                    areas.put(a.getId(), a.getName());
                });

        } catch (Exception e) {
            e.printStackTrace();
            toastMessage("Error Pulling Data!");

        }
        if (attendanceList == null || attendanceList.isEmpty())
            toastMessage("No se encuentran resultados para el usuario!");
        else try {
            Thread.sleep(1000);
            loadUI();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @SuppressLint("NewApi")
    @UiThread
    protected void loadUI() {

        name.setText(String.format("%s %s", student.getFirstName(), student.getLastName()));

        List<String> list = attendanceList.stream().map(Attendance::getForm).map(FullFormData::getName).collect(toList());

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dob.setText(attendanceList.get(position).getDate());

                populateTable(attendanceList.get(position));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @UiThread
    protected void cleanTable() {

        int childCount = table.getChildCount();

        // Remove all rows except the first one
        if (childCount > 1) {
            table.removeViews(1, childCount - 1);
        }
    }

    @SuppressLint("NewApi")
    @Background
    protected void populateTable(Attendance attendance) {
        cleanTable();
        ResponseEntity<ResultMatrixParameter> matrix = resultRepository.GetResultByAttendanceId(attendance.getId());
        if (matrix.getStatusCode() == HttpStatus.OK) {
            ResultMatrixParameter results = matrix.getBody();
            if (results.getResultList().isEmpty()) {
                toastMessage("No se encuentran resultados para el usuario!");
                return;
            }
            results.getResultList().forEach(res -> {
                TableRow row = new TableRow(this.getApplicationContext());
                TextView cell = new TextView(this.getApplicationContext());
                cell.setText(areas.get(res.getAreaId()));
                row.addView(cell);
                TextView cellTotal = new TextView(this.getApplicationContext());
                AtomicInteger i = new AtomicInteger();
                res.getResults().forEach(c -> {
                    TextView cellData = new TextView(this.getApplicationContext());

//                        cell.setText("(" + i + ", " + j + ")");
                    cellData.setText(String.valueOf(c.getValue()));
                    i.addAndGet(c.getValue());
                    row.addView(cellData);
                });
                cellTotal.setText(String.valueOf(i.get()));
                row.addView(cellTotal);
                updateTableSQ3(row);
            });
            setTableSpacing();
        }

    }

    private void setTableSpacing() {
        table.setStretchAllColumns(true);
    }


    @Click(R.id.button_history_back)
    public void back() {
        Intent intent = new Intent(ResultHistory.this, MyStudentList_.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        back();
    }

    @UiThread
    public void updateTableSQ3(TableRow row) {
        table.addView(row);
    }

    @org.androidannotations.annotations.UiThread
    public void toastMessage(String message) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }


}

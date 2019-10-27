package com.fiserv.codeforce;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.fiserv.codeforce.action_plan.ActionPlan;
import com.fiserv.codeforce.action_plan.ActionRepository;
import com.fiserv.codeforce.areas.AreaLimit;
import com.fiserv.codeforce.areas.AreasAdapter;
import com.fiserv.codeforce.areas.AreasRepository;
import com.fiserv.codeforce.result.ListResultRow;
import com.fiserv.codeforce.result.ResultCell;
import com.fiserv.codeforce.result.ResultMatrixParameter;
import com.fiserv.codeforce.result.ResultRepository;

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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@EActivity(R.layout.activity_create_action_plan_for_asq3)
public class CreateActionPlanForASQ3 extends Activity {

    @ViewById(R.id.txt_kid_name)
    TextView textViewKidName;

    @ViewById(R.id.txt_creation_date)
    TextView textViewCreationDate;

    @ViewById(R.id.txt_date_applied_asq3)
    TextView textViewAppliedASQ3;

    @ViewById(R.id.txt_asq3_name)
    TextView textViewASQ3Name;

    @ViewById(R.id.table_sq3_result)
    TableLayout tableSQ3Result;

    @ViewById(R.id.table_area_desarrollo)
    TableLayout tableAreaDesarrollo;

    @ViewById(R.id.spinner_estrategias)
    Spinner spinner;

    @RestService
    AreasRepository areasRepository;

    @RestService
    ActionRepository actionRepository;

    @RestService
    ResultRepository resultRepository;

    @Extra("kid_name")
    String kid_name;

    @Extra("asq3_name")
    String Asq3Name;

//    @Extra("ResultMatrixParameter")
//    ResultMatrixParameter resultMatrixParameter;

    Integer attendance = 24;
    Integer formId = 1;

    List<ActionPlan> actionPlanList;

    @AfterViews
    public void afterViews() {

        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());

        textViewKidName.setText(kid_name);
        textViewCreationDate.setText(date);
        textViewAppliedASQ3.setText(date);
        textViewASQ3Name.setText(Asq3Name);

        createResultTable();
        createEstrategias();
        createAreaDesarrollo();
    }

    @Background
    public void createResultTable() {
        try {
            ResponseEntity<ResultMatrixParameter> r = resultRepository.GetResultByAttendanceId(attendance);
            if (r.getStatusCode() == HttpStatus.OK) {
//                resultMatrixParameter = r.getBody().getResultList();
                ListResultRow l = r.getBody().getResultList();

                int rows = l.size();
                int columns = l.get(0).getResults().size();

                for (int i = 0; i < rows; i++) {
                    TableRow row = new TableRow(this);
                    for (int j = 0; j < columns; j++) {
                        TextView cell = new TextView(this);
//                        cell.setText("(" + i + ", " + j + ")");
                        cell.setText(String.valueOf(l.get(i).getResults().get(j).getValue()));
                        row.addView(cell);
                    }
                    updateTableSQ3(row);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @UiThread
    public void updateTableSQ3(TableRow row) {
        tableSQ3Result.addView(row);
    }


    @SuppressLint("NewApi")
    @Background
    public void createEstrategias() {
        try {
            ResponseEntity<List<ActionPlan>> r = actionRepository.getActionPlan();
            if (r.getStatusCode() == HttpStatus.OK) {
                actionPlanList = r.getBody();

                ActionPlan[] arraySpinner = actionPlanList.toArray(new ActionPlan[0]);

                ArrayAdapter<ActionPlan> adapter = new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_item,
                        arraySpinner
                );
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                updateSpinner(adapter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @UiThread
    public void updateSpinner(ArrayAdapter<ActionPlan> adapter) {
        spinner.setAdapter(adapter);
    }

    @SuppressLint("NewApi")
    @Background
    public void createAreaDesarrollo() {
        HashMap<Integer, AreaLimit> hashLimit = new HashMap<>();
        HashMap<Integer, Double> hashAverage = new HashMap<>();
        ResponseEntity<List<AreaLimit>> r = areasRepository.getByStudentId(formId);
        if (r.getStatusCode() == HttpStatus.OK) {
            r.getBody().forEach(areaLimit -> {
                hashLimit.put(areaLimit.getAreaId(), areaLimit);
            });
        }

        ResponseEntity<ResultMatrixParameter> r1 = resultRepository.GetResultByAttendanceId(attendance);
        if (r1.getStatusCode() == HttpStatus.OK) {
            ResultMatrixParameter rm = r1.getBody();
            rm
                    .getResultList()
                    .forEach(resultRow -> {
                        double average = resultRow.getResults()
                                .stream()
                                .mapToDouble(ResultCell::getValue)
                                .average()
                                .orElse(Double.NaN);
                        hashAverage.put(resultRow.getAreaId(), average);
                    });

        }

        hashAverage.keySet().forEach(area -> {
            double average = hashAverage.get(area);
            if (average <= hashLimit.get(area).getMaxValue()) {
                TableRow row = new TableRow(this);

                TextView cellArea = new TextView(this);
                TextView cellAverage = new TextView(this);

                cellArea.setText(AreasAdapter.AREAS.get(area));
                cellAverage.setText(String.valueOf(average));

                row.addView(cellArea);
                row.addView(cellAverage);

                updateTableAreaDesarrollo(row);
            }
        });
    }

    @UiThread
    public void updateTableAreaDesarrollo(TableRow row) {
        tableAreaDesarrollo.addView(row);
    }

    @Click(R.id.btn_accept)
    public void btnAccept() {
        Log.d(this.getClass().getName(), "Accept");
    }

    @Click(R.id.btn_cancel)
    public void btnCancel() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Confirmar");
        builder.setMessage("¿Desea salir sin guardar?");

        builder.setPositiveButton("Si", (dialog, which) -> {
            dialog.dismiss();
//            MainActivity_.intent(getApplicationContext()).flags(Intent.FLAG_ACTIVITY_NEW_TASK).start();
        });

        builder.setNegativeButton("No", (dialog, which) -> {
            dialog.dismiss();
        });

        AlertDialog alert = builder.create();
        alert.show();
//        Log.d(this.getClass().getName(), "Cancel");
    }
}

package com.fiserv.codeforce;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.fiserv.codeforce.action_plan.ActionPlan;
import com.fiserv.codeforce.action_plan.ActionRepository;
import com.fiserv.codeforce.areas.AreaLimit;
import com.fiserv.codeforce.areas.AreasRepository;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @Extra("kid_name")
    String kid_name;

    @Extra("asq3_name")
    String Asq3Name;

    List<ActionPlan> actionPlanList;

    @AfterViews
    public void afterViews(){
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


    public void createResultTable(){
        int rows = 5;
        int columns = 7;
//        TableLayout tableSQ3Result = findViewById(R.id.table_sq3_result);
//        TableLayout tableSQ3Result = new TableLayout(this);
        for (int i = 0; i < rows; i++) {
            TableRow row = new TableRow(this);
            for (int j = 0; j < columns; j++) {
                TextView cell = new TextView(this);
                cell.setText("(" + i + ", " + j + ")");
                row.addView(cell);
            }
            tableSQ3Result.addView(row);
        }
    }

    @SuppressLint("NewApi")
    public void createEstrategias(){
        actionPlanList = Arrays.asList(
                new ActionPlan()
                        .setId(0)
                        .setAreaId(0)
                        .setName("Actividad 1")
                        .setDescription("")
                        .setStatus(""),
                new ActionPlan()
                        .setId(1)
                        .setAreaId(0)
                        .setName("Actividad 2")
                        .setDescription("")
                        .setStatus(""),
                new ActionPlan()
                        .setId(2)
                        .setAreaId(0)
                        .setName("Actividad 3")
                        .setDescription("")
                        .setStatus("")
        );

        ActionPlan[] arraySpinner = actionPlanList.toArray(new ActionPlan[0]);

        ArrayAdapter<ActionPlan> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                arraySpinner
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @SuppressLint("NewApi")
    public void createAreaDesarrollo(){
        List<AreaLimit> a = Arrays.asList(
                new AreaLimit()
                        .setAreaId(0)
                        .setFormId(0)
                        .setMaxValue(100)
                        .setMinValue(0),
                new AreaLimit()
                        .setAreaId(1)
                        .setFormId(0)
                        .setMaxValue(100)
                        .setMinValue(0),
                new AreaLimit()
                        .setAreaId(2)
                        .setFormId(0)
                        .setMaxValue(100)
                        .setMinValue(0)
        );

        a.forEach(areaLimit -> {
            TableRow row = new TableRow(this);

            TextView cellAreaId = new TextView(this);
            TextView cellFormId = new TextView(this);
            TextView cellMaxValue = new TextView(this);
            TextView cellMinValue = new TextView(this);

            cellAreaId.setText(String.valueOf(areaLimit.getAreaId()));
            cellFormId.setText(String.valueOf(areaLimit.getFormId()));
            cellMaxValue.setText(String.valueOf(areaLimit.getMaxValue()));
            cellMinValue.setText(String.valueOf(areaLimit.getMinValue()));

            row.addView(cellAreaId);
            row.addView(cellFormId);
            row.addView(cellMaxValue);
            row.addView(cellMinValue);

            tableAreaDesarrollo.addView(row);
        });

    }

    @Click(R.id.btn_accept)
    public void btnAccept(){
        Log.d(this.getClass().getName(), "Accept");
    }

    @Click(R.id.btn_cancel)
    public void btnCancel(){
        Log.d(this.getClass().getName(), "Cancel");
    }
}

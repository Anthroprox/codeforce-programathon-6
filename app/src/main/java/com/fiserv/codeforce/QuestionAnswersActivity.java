package com.fiserv.codeforce;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.camera2.TotalCaptureResult;
import android.os.Bundle;
import android.os.ParcelFormatException;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fiserv.codeforce.areas.AreaBean;
import com.fiserv.codeforce.areas.AreaResult;

import org.androidannotations.annotations.AfterExtras;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.TextChange;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.text.ParseException;

@EActivity(R.layout.activity_question_answers)
public class QuestionAnswersActivity extends AppCompatActivity {

    @Extra("result")
    AreaResult areaResult;

    @Bean
    AreaBean bean;

    private int total;

    @ViewById
    TextView txtQAArea;

    @ViewById
    EditText txtP1;

    @ViewById
    EditText txtP2;

    @ViewById
    EditText txtP3;

    @ViewById
    EditText txtP4;

    @ViewById
    EditText txtP5;

    @ViewById
    EditText txtP6;

    @ViewById
    TextView txtTotal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_answers);
    }

    @TextChange({R.id.txtP1, R.id.txtP2, R.id.txtP3, R.id.txtP4, R.id.txtP5, R.id.txtP6})
    void onTextChangesOnSomeTextViews(TextView tv, CharSequence text) {
        try {
            bindInputData();
            calculateTotal();
        } catch (Exception e) {

        }
    }

    @Click(R.id.btnSave)
    public void clickSave() {

        if (!validateAns())
            toastMessage("Los campos deben estar entre: 0,5,10");
        else
            finishActivity(RESULT_FIRST_USER);

    }

    ;

    @Click(R.id.btnCancel)
    public void clickCancel() {
        finishActivity(RESULT_CANCELED);
    }

    @AfterViews
    public void bindData(){
        txtQAArea.setText(areaResult.getAreaName());
        if(areaResult.getValues()[0] != -1) {
            try {

                txtP1.setText(String.valueOf(areaResult.getValue(0)));
                txtP2.setText(String.valueOf(areaResult.getValue(1)));
                txtP3.setText(String.valueOf(areaResult.getValue(2)));
                txtP4.setText(String.valueOf(areaResult.getValue(3)));
                txtP5.setText(String.valueOf(areaResult.getValue(4)));
                txtP6.setText(String.valueOf(areaResult.getValue(5)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean validateAns() {
        try {
            bindInputData();
        } catch (NumberFormatException e) {
            return false;
        }
        for (int areaResult : areaResult.getValues()) {
            if (areaResult != 0 && areaResult != 5 && areaResult != 10)
                return false;
        }
        return true;
    }

    public void bindInputData() throws NumberFormatException {
        areaResult.setValue(0, Integer.parseInt(txtP1.getText().toString()));
        areaResult.setValue(1, Integer.parseInt(txtP2.getText().toString()));
        areaResult.setValue(2, Integer.parseInt(txtP3.getText().toString()));
        areaResult.setValue(3, Integer.parseInt(txtP4.getText().toString()));
        areaResult.setValue(4, Integer.parseInt(txtP5.getText().toString()));
        areaResult.setValue(5, Integer.parseInt(txtP6.getText().toString()));
    }

    @UiThread
    public void toastMessage(String message) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }

    public void calculateTotal() {
        this.total = 0;
        for (int areaResult : areaResult.getValues()) {
            total += areaResult;
        }
        setStatus();
        txtTotal.setText("TOTAL: " + total);

    }

    public void finishActivity(int result) {

        Intent intent = new Intent(this,AreasActivity_.class);
        intent.putExtra("results", areaResult);
        setResult(RESULT_OK,intent);
        bean.hash.put("result",areaResult);
        finish();


    }

    public void setStatus() {
        if (areaResult.getAreaName().equals("Comunicación")) {
            if (total < 30.72)
                txtTotal.setBackgroundColor(Color.RED);
            else if (total < 40)
                txtTotal.setBackgroundColor(Color.YELLOW);
            else
                txtTotal.setBackgroundColor(Color.GREEN);

        } else if (areaResult.getAreaName().equals("Motora Gruesa")) {
            if (total < 32.78)
                txtTotal.setBackgroundColor(Color.RED);
            else if (total < 43)
                txtTotal.setBackgroundColor(Color.YELLOW);
            else
                txtTotal.setBackgroundColor(Color.GREEN);
        } else if (areaResult.getAreaName().equals("Motora Fina")) {
            if (total < 15.81)
                txtTotal.setBackgroundColor(Color.RED);
            else if (total < 30)
                txtTotal.setBackgroundColor(Color.YELLOW);
            else
                txtTotal.setBackgroundColor(Color.GREEN);
        } else if (areaResult.getAreaName().equals("Resolución de problemas")) {
            if (total < 31.30)
                txtTotal.setBackgroundColor(Color.RED);
            else if (total < 41)
                txtTotal.setBackgroundColor(Color.YELLOW);
            else
                txtTotal.setBackgroundColor(Color.GREEN);
        } else if (areaResult.getAreaName().equals("Socio-individual")) {
            if (total < 26.6)
                txtTotal.setBackgroundColor(Color.RED);
            else if (total < 38)
                txtTotal.setBackgroundColor(Color.YELLOW);
            else
                txtTotal.setBackgroundColor(Color.GREEN);
        }
    }

    public String validateInputs(){
        return "";
    }
}


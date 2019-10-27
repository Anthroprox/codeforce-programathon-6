package com.fiserv.codeforce;

import android.app.Activity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_consult_form_asq3__temp)
public class ConsultFormASQ3_Temp extends Activity {

    @AfterViews
    public void afterView(){
        String dni = getIntent().getExtras().get("STUDENT_DNI").toString();
        System.out.println(dni);
    }

}

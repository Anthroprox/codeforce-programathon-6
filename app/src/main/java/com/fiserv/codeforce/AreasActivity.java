package com.fiserv.codeforce;

import androidx.annotation.ColorLong;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.fiserv.codeforce.areas.AreasAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.text.SimpleDateFormat;
import java.util.Date;

@EActivity(R.layout.activity_areas)
public class AreasActivity extends AppCompatActivity {

    @Bean
    AreasAdapter adapter;

    @ViewById
    TextView txtDate;

    @AfterViews
    public void setDate(){
        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        txtDate.setText("FECHA APLICACION: "+ date);
    }

    @Click(R.id.txtCom)
    void clickCom(){
        Intent i = new Intent(AreasActivity.this,QuestionAnswersActivity_.class);
        i.putExtra("result",adapter.getAreaResultByArea("Comunicación"));
        startActivity(i);
    }

    @Click(R.id.txtMG)
    void clickMG(){
        Intent i = new Intent(AreasActivity.this,QuestionAnswersActivity_.class);
        i.putExtra("result",adapter.getAreaResultByArea("Motora Gruesa"));
        startActivity(i);
    }

    @Click(R.id.txtMF)
    void clickMF(){
        Intent i = new Intent(AreasActivity.this,QuestionAnswersActivity_.class);
        i.putExtra("result",adapter.getAreaResultByArea("Motora Fina"));
        startActivity(i);
    }

    @Click(R.id.txtRP)
    void clickRP(){
        Intent i = new Intent(AreasActivity.this,QuestionAnswersActivity_.class);
        i.putExtra("result",adapter.getAreaResultByArea("Resolución de problemas"));
        startActivity(i);
    }

    @Click(R.id.txtSocial)
    void clickSocial(){
        Intent i = new Intent(AreasActivity.this,QuestionAnswersActivity_.class);
        i.putExtra("result",adapter.getAreaResultByArea("Socio-individual"));
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_areas);
    }


}

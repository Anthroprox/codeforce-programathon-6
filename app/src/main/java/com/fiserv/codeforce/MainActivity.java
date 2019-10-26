package com.fiserv.codeforce;

import android.app.Activity;
import android.widget.TextView;

import com.fiserv.codeforce.rest.DemoAPI;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {

    @ViewById
    TextView textView;

    @RestService
    DemoAPI api;

    private int i = 1;

    @Click(R.id.button)
    public void click() {
        setText();
    }

    @Background
    public void setText() {
        try {
            textView.setText(api.test(i++).getBody().toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

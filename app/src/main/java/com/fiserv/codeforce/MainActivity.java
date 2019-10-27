package com.fiserv.codeforce;

import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;

import com.fiserv.codeforce.rest.DemoAPI;
import com.fiserv.codeforce.student.ListStudent;
import com.fiserv.codeforce.student.StudentRepository;
import com.fiserv.codeforce.student.StudentRepositoryInterceptor;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {

    @ViewById
    TextView textView;

    @RestService
    DemoAPI api;

    @Bean
    StudentRepositoryInterceptor studentRepositoryInterceptor;


    @Click(R.id.button)
    public void click() {
        loadView();
    }

    @Background
    public void setText() {
        try {
            System.out.print("Hello!");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @UiThread
    public void loadView() {
        Intent sendIntent = new Intent(MainActivity.this, MyStudentList_.class);
        sendIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(sendIntent);
//        MyStudentList_.intent(getApplicationContext()).start();

    }

//    @AfterInject
//    void initAuth() {
//        RestTemplate template = studentRepository.getRestTemplate();
//        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
//        interceptors.add(authInterceptor);
//        template.setInterceptors(interceptors);
//    }
}

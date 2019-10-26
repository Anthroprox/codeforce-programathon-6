package com.fiserv.codeforce;

import android.app.Activity;
import android.provider.Settings;
import android.widget.TextView;

import com.fiserv.codeforce.rest.DemoAPI;
import com.fiserv.codeforce.student.ListStudent;
import com.fiserv.codeforce.student.StudentRepository;
import com.fiserv.codeforce.student.StudentRepositoryInterceptor;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {

    @ViewById
    TextView textView;

    @RestService
    DemoAPI api;



    @RestService
    StudentRepository studentRepository;

    @Bean
    StudentRepositoryInterceptor studentRepositoryInterceptor;


    @Click(R.id.button)
    public void click() {
        setText();
    }

    @Background
    public void setText() {
        try {
            ListStudent list = studentRepository.GetMyStudents().getBody();
            System.out.print(list.toString());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @UiThread
    public void loadView(){
        MyStudentList_.intent(getApplicationContext()).start();
    }

//    @AfterInject
//    void initAuth() {
//        RestTemplate template = studentRepository.getRestTemplate();
//        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
//        interceptors.add(authInterceptor);
//        template.setInterceptors(interceptors);
//    }
}

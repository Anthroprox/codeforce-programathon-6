package com.fiserv.codeforce;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.fiserv.codeforce.student.ListStudent;
import com.fiserv.codeforce.student.StudentPojo;
import com.fiserv.codeforce.student.StudentRepository;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.ArrayList;

@EActivity(R.layout.activity_my_student_list)
public class MyStudentList extends Activity {

    @RestService
    StudentRepository studentRepository;

    @ViewById(R.id.recyclerView)
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    ArrayList<RecyclerViewerCardObject> rowsArrayList = new ArrayList<>();
    ListStudent rawData = null;

    boolean isLoading = false;

    boolean flag = true;

    @AfterViews
    public void postCreate() {
        populateData();
        initAdapter();
        initScrollListener();
    }

    @Background
    protected void populateData() {

        int i = 0;

        rawData = studentRepository.GetMyStudents().getBody();


        for (i = 0; i < Math.min(10, rawData.size()); i++) {
            StudentPojo currentStudent = rawData.get(i);
            rowsArrayList.add(new RecyclerViewerCardObject(
                    currentStudent.getFirstName() + " " + currentStudent.getLastName(),
                    currentStudent.getForm().getName(),
                    currentStudent.getDni()));
        }

        flag = false;
    }

    private void initAdapter() {
        while (flag) ;
        recyclerViewAdapter = new RecyclerViewAdapter(rowsArrayList, (studentDni) -> (v) -> {
            Intent t = new Intent(MyStudentList.this, ConsultFormASQ3_Temp_.class);
            t.putExtra("STUDENT_DNI", studentDni);
            startActivity(t);
        });
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void initScrollListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == rowsArrayList.size() - 1) {
                        //bottom of list!
                        loadMore();
                        isLoading = true;
                    }
                }
            }
        });


    }

    @UiThread
    private void loadMore() {
        rowsArrayList.add(null);
        recyclerViewAdapter.notifyItemInserted(rowsArrayList.size() - 1);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                rowsArrayList.remove(rowsArrayList.size() - 1);
                int scrollPosition = rowsArrayList.size();
                recyclerViewAdapter.notifyItemRemoved(scrollPosition);
                int currentSize = scrollPosition;
                int nextLimit = Math.min(currentSize + 10, rawData.size() - 1);

                while (currentSize - 1 < nextLimit) {
                    StudentPojo currentStudent = rawData.get(currentSize);
                    rowsArrayList.add(new RecyclerViewerCardObject(
                            currentStudent.getFirstName() + " " + currentStudent.getLastName(),
                            currentStudent.getForm().getName(),
                            currentStudent.getDni()));
                    currentSize++;
                }

                recyclerViewAdapter.notifyDataSetChanged();
                isLoading = false;
            }
        }, 2000);


    }
}

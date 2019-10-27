package com.fiserv.codeforce;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.fiserv.codeforce.action_plan.ActionPlan;
import com.fiserv.codeforce.areas.Area;
import com.fiserv.codeforce.areas.AreaRepository;
import com.fiserv.codeforce.areas.AreaResult;
import com.fiserv.codeforce.attendance.Attendance;
import com.fiserv.codeforce.attendance.AttendanceRepository;
import com.fiserv.codeforce.student.ListStudent;
import com.fiserv.codeforce.student.StudentPojo;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


@EActivity(R.layout.activity_action_plans)
public class ActionPlans extends AppCompatActivity {

    @Extra("form")
    public Integer formId;
    @Extra("student")
    public Integer studentId;

    @Extra("studentName")
    public String studentName;

    @RestService
    protected AreaRepository areaRepository;

    @RestService
    protected AttendanceRepository attendanceRepository;

    @ViewById(R.id.recyclerViewActionPlan)
    RecyclerView recyclerView;
    ActionPlanRecyclerViewAdapter recyclerViewAdapter;
    ArrayList<ActionaPlanRecyclerViewerCardObject> rowsArrayList = new ArrayList<>();

    ArrayList<ActionaPlanRecyclerViewerCardObject> rawData = null;

    boolean isLoading = false;

    boolean flag = true;

    @Override
    protected void onPostResume() {
        super.onPostResume();
        flag = true;
        rowsArrayList.clear();
        populateData();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        initAdapter();
        initScrollListener();

    }

//    @AfterViews
    public void postCreate() {
        flag = true;
        rowsArrayList.clear();
        populateData();
        initAdapter();
        initScrollListener();
    }

    @Click(R.id.button2)
    public void back() {
        super.onBackPressed();
        Intent intent = new Intent(ActionPlans.this, MyStudentList_.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        back();
    }

    @SuppressLint("NewApi")
    @Background
    protected void populateData() {

        int i = 0;
        rawData = new ArrayList<>();

        try {
            ResponseEntity<List<Attendance>> attendaceResponse = attendanceRepository.getByStudentId(studentId);
            attendaceResponse.getBody().forEach(att -> {
                ResponseEntity<List<ActionPlan>> actionPlanResponse = attendanceRepository.getActionByAttendanceId(att.getId());
                List<Area> areas = new ArrayList<>();
                List<ActionPlan> actions = actionPlanResponse.getBody();
                actions.stream().mapToInt(ActionPlan::getAreaId).distinct().forEach(id -> {
                    Area area = areaRepository.getById(id).getBody();
                    areas.add(area);
                });
                HashMap<String, List<String>> map = new HashMap<>();
                areas.forEach(a -> {
                    List<String> list = actions.stream().filter(ac -> ac.getAreaId().equals(a.getId())).map(ActionPlan::toString).collect(Collectors.toList());
                    map.put(a.getName(), list);
                });
                if (!map.isEmpty())
                    rawData.add(new ActionaPlanRecyclerViewerCardObject(
                            studentName,
                            att.getForm().getName(),
                            att.getDate(),
                            areas.stream().map(Area::getName).collect(Collectors.toList()),
                            map

                    ));
            });
            if (rawData.isEmpty()) {
                toastMessage("No hay plan de accion!");
            } else {
                for (i = 0; i < Math.min(10, rawData.size()); i++) {
                    rowsArrayList.add(rawData.get(i));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            flag = false;
        }

//        flag = false;
    }

    @org.androidannotations.annotations.UiThread
    public void toastMessage(String message) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }

    private void initAdapter() {
        while (flag) ;
        if (!rowsArrayList.isEmpty()) {
            recyclerViewAdapter = new ActionPlanRecyclerViewAdapter(rowsArrayList);
            recyclerView.setAdapter(recyclerViewAdapter);
        }
    }

    private void initScrollListener() {
        if (!rowsArrayList.isEmpty())
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
        if(rawData.size() == rowsArrayList.size())
            return;
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
                    ActionaPlanRecyclerViewerCardObject currentStudent = rawData.get(currentSize);
                    rowsArrayList.add(rawData.get(currentSize));
                    currentSize++;
                }

                recyclerViewAdapter.notifyDataSetChanged();
                isLoading = false;
            }
        }, 2000);


    }
}

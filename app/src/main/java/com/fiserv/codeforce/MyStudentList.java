package com.fiserv.codeforce;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.fiserv.codeforce.student.ListStudent;
import com.fiserv.codeforce.student.StudentPojo;
import com.fiserv.codeforce.student.StudentRepository;
import com.google.android.material.navigation.NavigationView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Objects;

@EActivity(R.layout.activity_my_student_list)
public class MyStudentList extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @ViewById(R.id.nav_View)
    NavigationView navigationView;

    @ViewById(R.id.my_student_list_drawer)
    DrawerLayout drawer;

    @Bean
    GlobalContainer globalContainer;

    @RestService
    StudentRepository studentRepository;

    @ViewById(R.id.recyclerView)
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    ArrayList<RecyclerViewerCardObject> rowsArrayList = new ArrayList<>();
    ListStudent rawData = null;

    boolean isLoading = false;

    boolean flag = true;

    @Override
    protected void onStart() {
        super.onStart();
        navigationView.setNavigationItemSelectedListener(this);
    }


    @AfterViews
    public void postCreate() {
        rowsArrayList.clear();
        populateData();
        initAdapter();
        initScrollListener();
    }

    @Background
    protected void populateData() {

        int i = 0;

        ResponseEntity<ListStudent> studentsResponse = studentRepository.GetMyStudents();
        rawData = studentsResponse.getBody();

        if (studentsResponse.getStatusCode() == HttpStatus.NO_CONTENT || rawData == null || rawData.isEmpty())
            toastMessage("No tiene estudiantes asignados!");
        else {
            for (i = 0; i < Math.min(10, rawData.size()); i++) {
                StudentPojo currentStudent = rawData.get(i);
                rowsArrayList.add(new RecyclerViewerCardObject(
                        currentStudent.getFirstName() + " " + currentStudent.getLastName(),
                        currentStudent.getForm().getName(),
                        currentStudent.getDni(),
                        currentStudent.getForm().getId(),
                        currentStudent.getId()));
            }
        }
        flag = false;
    }

    @org.androidannotations.annotations.UiThread
    public void toastMessage(String message) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }

    private void initAdapter() {
        while (flag) ;
        if (!rowsArrayList.isEmpty()) {
            recyclerViewAdapter = new RecyclerViewAdapter(rowsArrayList, (studentDni, formId,studentId) -> (v) -> {
                Intent t = new Intent(MyStudentList.this, ConsultFormASQ3_Temp_.class);
                t.putExtra("studentDni", studentDni);
                t.putExtra("dni", studentDni);
                t.putExtra("formId", formId);
                t.putExtra("studentId", studentId);
                startActivity(t);
            });
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
                            currentStudent.getDni(),
                            currentStudent.getForm().getId(),
                            currentStudent.getId()));
                    currentSize++;
                }

                recyclerViewAdapter.notifyDataSetChanged();
                isLoading = false;
            }
        }, 2000);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.nav_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btn_logout:
                logout();
                break;
            default:
                Log.d(this.getClass().getName(), "");
        }
        drawer.closeDrawers();
        return true;
    }

    public void logout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Confirmar");
        builder.setMessage("¿Desea Salir de la aplicación?");

        builder.setPositiveButton("Aceptar", (dialog, which) -> {
            dialog.dismiss();
            globalContainer.logout();
            MainActivity_.intent(getApplicationContext()).flags(Intent.FLAG_ACTIVITY_NEW_TASK).start();
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> {
            dialog.dismiss();
        });

        AlertDialog alert = builder.create();
        alert.show();

//
    }
}

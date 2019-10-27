package com.fiserv.codeforce;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import androidx.appcompat.app.AppCompatActivity;

import com.fiserv.codeforce.attendance.Attendance;
import com.fiserv.codeforce.attendance.AttendanceRepository;
import com.fiserv.codeforce.form.FormRepository;
import com.fiserv.codeforce.form.FullFormData;
import com.fiserv.codeforce.student.Form;
import com.fiserv.codeforce.student.StudentPojo;
import com.fiserv.codeforce.student.StudentRepository;
import com.fiserv.codeforce.utils.CustomExpandableListAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@EActivity(R.layout.activity_consult_form_asq3__temp)
public class ConsultFormASQ3_Temp extends AppCompatActivity {

    @RestService
    protected StudentRepository studentRepository;

    @RestService
    protected FormRepository formRepository;

    @RestService
    protected AttendanceRepository attendanceRepository;

    @Extra
    protected Integer dni;

    @Extra
    protected Integer studentId;

    @Extra
    protected Integer formId;

//    @ViewById(R.id.detail_student_name)
//    protected EditText name;
//    @ViewById(R.id.detail_student_dob)
//    protected EditText dob;
//    @ViewById(R.id.detail_student_dni)
//    protected EditText dniText;
//    @ViewById(R.id.detail_student_gender)
//    protected EditText gender;

    @ViewById(R.id.expandableListView)
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;

    @AfterViews
    public void afterView() {
        clearText();
        requestData();

    }

    @Background
    protected void requestData() {
        ResponseEntity<StudentPojo> student = studentRepository.GetByDni(dni);
//        String dni = getIntent().getExtras().get("STUDENT_DNI").toString();
//        System.out.println(dni);
        if (student.getStatusCode() == HttpStatus.OK) {

            StudentPojo entity = student.getBody();
            ResponseEntity<FullFormData> form = formRepository.getById(formId);
            if (form.getStatusCode() == HttpStatus.OK) {
                fillData(entity, form.getBody());
            }

        }
    }

    @UiThread
    protected void fillData(StudentPojo entity, FullFormData form) {
//        name.setText(String.format("%s %s", entity.getFirstName(), entity.getLastName()));
//        dob.setText(entity.getDob());
//        dniText.setText(entity.getDni().toString());
//        gender.setText(entity.getGender());

        expandableListTitle = Arrays.asList(String.format("%s %s", entity.getFirstName(), entity.getLastName()), "ASQ 3");
        expandableListDetail = new HashMap<>();
        expandableListDetail.put(String.format("%s %s", entity.getFirstName(), entity.getLastName()), Arrays.asList(
                String.format("Name: %s %s", entity.getLastName(), entity.getFirstName()),
                String.format("Gender: %s", entity.getGender()),
                String.format("DNI: %d", entity.getDni())
        ));
        expandableListDetail.put("ASQ 3", Arrays.asList(
                String.format("Name: %s", form.getName()),
                String.format("Status: %s", form.getStatus()),
                String.format("Max Age (Months): %d", form.getMaxAgeMonths()),
                String.format("Max Age (Days): %d", form.getMaxAgeDays()),
                String.format("Min Age (Months): %d", form.getMinAgeMonths()),
                String.format("Min Age (Days): %d", form.getMinAgeDays())
        ));
        expandableListAdapter = new CustomExpandableListAdapter(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
    }

    private void clearText() {
//        name.setText("");
//        dob.setText("");
//        dniText.setText("");
//        gender.setText("");
    }

    @Click(R.id.areas_btn)
    public void clickAreas() {
        openAreas();
    }

    @Click(R.id.planes_btn)
    public void clickActionPlans() {
        openActionPlans();
    }

    @Click(R.id.history_result_btn)
    public void clickHistoricResults() {
        openHistoryResult();
    }

    @Background
    protected void openHistoryResult() {
        Intent i = new Intent(ConsultFormASQ3_Temp.this, ResultHistory_.class);
        i.putExtra("examId", formId);
        i.putExtra("studentDni", dni);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

    @Background
    protected void openActionPlans() {
        ResponseEntity<StudentPojo> student = studentRepository.GetByDni(dni);
        Intent i = new Intent(ConsultFormASQ3_Temp.this, ActionPlans_.class);
        i.putExtra("form", formId);
        i.putExtra("student", studentId);
        i.putExtra("studentName", student.getBody().getFirstName() + " " + student.getBody().getLastName());
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

    @SuppressLint("NewApi")
    @Background
    protected void openAreas() {
        ResponseEntity<FullFormData> formResponse = formRepository.getById(formId);
        if (formResponse.getStatusCode() == HttpStatus.OK) {
            Intent intent = new Intent(ConsultFormASQ3_Temp.this, AreasActivity_.class);
            intent.putExtra("form", formResponse.getBody());
            intent.putExtra("studentId", studentId);
            startActivity(intent);
        }

    }
}

package com.fiserv.codeforce.attendance;

import com.fiserv.codeforce.form.FullFormData;

import java.io.Serializable;

public class Attendance implements Serializable {

    private Integer id;
    private String date;
    private Integer formId;
    private Integer studentId;
    private Integer applicatorId;
    private String status;
    private FullFormData form;

    public Attendance() {
    }

    public Attendance(Integer id, String date, Integer formId, Integer studentId, Integer applicatorId, String status, FullFormData form) {
        this.id = id;
        this.date = date;
        this.formId = formId;
        this.studentId = studentId;
        this.applicatorId = applicatorId;
        this.status = status;
        this.form = form;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getFormId() {
        return formId;
    }

    public void setFormId(Integer formId) {
        this.formId = formId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getApplicatorId() {
        return applicatorId;
    }

    public void setApplicatorId(Integer applicatorId) {
        this.applicatorId = applicatorId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public FullFormData getForm() {
        return form;
    }

    public void setForm(FullFormData form) {
        this.form = form;
    }
}

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

    public Attendance setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getDate() {
        return date;
    }

    public Attendance setDate(String date) {
        this.date = date;
        return this;
    }

    public Integer getFormId() {
        return formId;
    }

    public Attendance setFormId(Integer formId) {
        this.formId = formId;
        return this;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public Attendance setStudentId(Integer studentId) {
        this.studentId = studentId;
        return this;
    }

    public Integer getApplicatorId() {
        return applicatorId;
    }

    public Attendance setApplicatorId(Integer applicatorId) {
        this.applicatorId = applicatorId;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Attendance setStatus(String status) {
        this.status = status;
        return this;
    }

    public FullFormData getForm() {
        return form;
    }

    public Attendance setForm(FullFormData form) {
        this.form = form;
        return this;
    }
}

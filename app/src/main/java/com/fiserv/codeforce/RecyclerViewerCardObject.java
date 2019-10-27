package com.fiserv.codeforce;

public class RecyclerViewerCardObject {

    private String name;
    private String exam;
    private Integer dni;
    private Integer formId;
    private Integer studentId;

    public RecyclerViewerCardObject(String name, String exam, Integer dni, Integer formId, Integer studentId) {
        this.name = name;
        this.exam = exam;
        this.dni = dni;
        this.formId = formId;
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExam() {
        return exam;
    }

    public void setExam(String exam) {
        this.exam = exam;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
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
}

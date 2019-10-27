package com.fiserv.codeforce;

public class RecyclerViewerCardObject {

    private String name;
    private String exam;
    private Integer dni;

    public RecyclerViewerCardObject(String name, String exam, Integer dni) {
        this.name = name;
        this.exam = exam;
        this.dni = dni;
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
}

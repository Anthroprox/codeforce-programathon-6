package com.fiserv.codeforce;

public class RecyclerViewerCardObject {

    private String name;
    private String exam;

    public RecyclerViewerCardObject(String name, String exam) {
        this.name = name;
        this.exam = exam;
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
}

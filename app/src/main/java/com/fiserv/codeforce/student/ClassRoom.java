package com.fiserv.codeforce.student;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.androidannotations.annotations.EBean;

public class ClassRoom {
    Integer id;
    Float classYear;
    String section;
    Integer status;
    Integer gradeId;
    Integer teacherId;

    public ClassRoom(Integer id, Float classYear, String section, Integer status, Integer gradeId, Integer teacherId) {
        this.id = id;
        this.classYear = classYear;
        this.section = section;
        this.status = status;
        this.gradeId = gradeId;
        this.teacherId = teacherId;
    }

    public ClassRoom() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getClassYear() {
        return classYear;
    }

    public void setClassYear(Float classYear) {
        this.classYear = classYear;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }
}

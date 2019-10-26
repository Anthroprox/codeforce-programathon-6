package com.fiserv.codeforce.student;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.androidannotations.annotations.EBean;

public class Form {
    Integer id;
    String name;
    Boolean applied;

    public Form(Integer id, String name, Boolean applied) {
        this.id = id;
        this.name = name;
        this.applied = applied;
    }

    public Form() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getApplied() {
        return applied;
    }

    public void setApplied(Boolean applied) {
        this.applied = applied;
    }
}

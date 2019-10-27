package com.fiserv.codeforce.form;


import java.io.Serializable;

public class FullFormData implements Serializable {

    private Integer id;
    private String name;
    private String instructions;
    private String status;
    private Integer minAgeMonths;
    private Integer minAgeDays;
    private Integer maxAgeMonths;
    private Integer maxAgeDays;

    public FullFormData(Integer id, String name, String instructions, String status, Integer minAgeMonths, Integer minAgeDays, Integer maxAgeMonths, Integer maxAgeDays) {
        this.id = id;
        this.name = name;
        this.instructions = instructions;
        this.status = status;
        this.minAgeMonths = minAgeMonths;
        this.minAgeDays = minAgeDays;
        this.maxAgeMonths = maxAgeMonths;
        this.maxAgeDays = maxAgeDays;
    }

    public FullFormData() {
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

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getMinAgeMonths() {
        return minAgeMonths;
    }

    public void setMinAgeMonths(Integer minAgeMonths) {
        this.minAgeMonths = minAgeMonths;
    }

    public Integer getMinAgeDays() {
        return minAgeDays;
    }

    public void setMinAgeDays(Integer minAgeDays) {
        this.minAgeDays = minAgeDays;
    }

    public Integer getMaxAgeMonths() {
        return maxAgeMonths;
    }

    public void setMaxAgeMonths(Integer maxAgeMonths) {
        this.maxAgeMonths = maxAgeMonths;
    }

    public Integer getMaxAgeDays() {
        return maxAgeDays;
    }

    public void setMaxAgeDays(Integer maxAgeDays) {
        this.maxAgeDays = maxAgeDays;
    }
}

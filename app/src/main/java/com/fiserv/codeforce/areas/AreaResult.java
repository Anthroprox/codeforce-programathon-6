package com.fiserv.codeforce.areas;

import android.telecom.Call;

import java.io.Serializable;

public class AreaResult implements Serializable {

    private Integer id;
    private String areaName;
    private int[] values = {-1,-1,-1,-1,-1,-1};


    public AreaResult(Integer id, String areaName){
        this.id = id;
        this.areaName = areaName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public void setValue(int index, int value){
        values[index] = value;
    }

    public int getValue(int index){
        return values[index];
    }

    public int[] getValues(){
        return values;
    }

    public void setValues(int[] values){
        this.values = values;
    }
}

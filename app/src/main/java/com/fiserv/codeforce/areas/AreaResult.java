package com.fiserv.codeforce.areas;

import java.io.Serializable;

public class AreaResult implements Serializable {

    private String areaName;
    private int[] values = new int[6];


    public AreaResult(String areaName){
        this.areaName = areaName;
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

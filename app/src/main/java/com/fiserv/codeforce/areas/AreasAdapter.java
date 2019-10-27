package com.fiserv.codeforce.areas;

import android.content.Context;
import android.hardware.Camera;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;

@EBean
public class AreasAdapter {
    ArrayList<AreaResult> results;


    @AfterInject
    void initAdapter() {
        results = new ArrayList<>();
        results.add(new AreaResult("Comunicación"));
        results.add(new AreaResult("Motora Gruesa"));
        results.add(new AreaResult("Motora Fina"));
        results.add(new AreaResult("Resolución de problemas"));
        results.add(new AreaResult("Socio-individual"));
    }

    public AreaResult getAreaResultByArea(String areaName){
        for(int i = 0; i < results.size(); i++){
            if(results.get(i).getAreaName().equals(areaName))
                return  results.get(i);
        }
        return null;
    }

    public void setResults(AreaResult result){
       //
        //results.get(i).setValues(result.getValues());
    }

    public int calculateTotal(){
        int total = 0;
        for (AreaResult area:results) {
            for(int values: area.getValues())
             total += values;
        }
        return total;
    }
}

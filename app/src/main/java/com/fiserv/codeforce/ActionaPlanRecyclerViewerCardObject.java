package com.fiserv.codeforce;

import com.fiserv.codeforce.action_plan.ActionPlan;

import java.util.HashMap;
import java.util.List;

public class ActionaPlanRecyclerViewerCardObject {

    String name;
    String form;
    String date;
    List<String> area;
    HashMap<String, List<String>> actions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<String> getArea() {
        return area;
    }

    public void setArea(List<String> area) {
        this.area = area;
    }

    public HashMap<String, List<String>> getActions() {
        return actions;
    }

    public void setActions(HashMap<String, List<String>> actions) {
        this.actions = actions;
    }

    public ActionaPlanRecyclerViewerCardObject() {
    }

    public ActionaPlanRecyclerViewerCardObject(String name, String form, String date, List<String> area, HashMap<String, List<String>> actions) {
        this.name = name;
        this.form = form;
        this.date = date;
        this.area = area;
        this.actions = actions;
    }
}

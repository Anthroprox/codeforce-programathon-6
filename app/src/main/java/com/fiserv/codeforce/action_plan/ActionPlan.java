package com.fiserv.codeforce.action_plan;

import androidx.annotation.NonNull;

public class ActionPlan {
    Integer id;
    Integer areaId;
    String name;
    String description;
    String status;

    public ActionPlan() {
    }

    public ActionPlan(Integer id, Integer areaId, String name, String description, String status) {
        this.id = id;
        this.areaId = areaId;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public ActionPlan setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public ActionPlan setAreaId(Integer areaId) {
        this.areaId = areaId;
        return this;
    }

    public String getName() {
        return name;
    }

    public ActionPlan setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ActionPlan setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public ActionPlan setStatus(String status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return name + " | " + description;
    }
}

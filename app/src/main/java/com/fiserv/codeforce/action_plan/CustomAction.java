package com.fiserv.codeforce.action_plan;

public class CustomAction {
    Integer id;
    String name;
    String description;
    Integer attendanceId;

    public CustomAction() {
    }

    public CustomAction(Integer id, String name, String description, Integer attendanceId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.attendanceId = attendanceId;
    }

    public Integer getId() {
        return id;
    }

    public CustomAction setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CustomAction setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CustomAction setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getAttendanceId() {
        return attendanceId;
    }

    public CustomAction setAttendanceId(Integer attendanceId) {
        this.attendanceId = attendanceId;
        return this;
    }
}

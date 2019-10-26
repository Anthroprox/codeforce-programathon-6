package com.fiserv.codeforce.student;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentPojo {
    String dob;
    String joinDate;
    Integer earlyBirthAmount;
    Integer sponsorId;
    Integer classRoomId;

    ClassRoom classRoom;

    Form form;

    Integer id;
    Integer dni;
    String firstName;
    String lastName;
    String gender;
    Integer locationId;
    String status;

    public StudentPojo() {
    }

    public StudentPojo(String dob, String joinDate, Integer earlyBirthAmount, Integer sponsorId, Integer classRoomId, ClassRoom classRoom, Form form, Integer id, Integer dni, String firstName, String lastName, String gender, Integer locationId, String status) {
        this.dob = dob;
        this.joinDate = joinDate;
        this.earlyBirthAmount = earlyBirthAmount;
        this.sponsorId = sponsorId;
        this.classRoomId = classRoomId;
        this.classRoom = classRoom;
        this.form = form;
        this.id = id;
        this.dni = dni;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.locationId = locationId;
        this.status = status;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public Integer getEarlyBirthAmount() {
        return earlyBirthAmount;
    }

    public void setEarlyBirthAmount(Integer earlyBirthAmount) {
        this.earlyBirthAmount = earlyBirthAmount;
    }

    public Integer getSponsorId() {
        return sponsorId;
    }

    public void setSponsorId(Integer sponsorId) {
        this.sponsorId = sponsorId;
    }

    public Integer getClassRoomId() {
        return classRoomId;
    }

    public void setClassRoomId(Integer classRoomId) {
        this.classRoomId = classRoomId;
    }

    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

package com.fiserv.codeforce.result;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResultMatrixParameter {

    Integer attendanceId;
    ListResultRow resultList;

    public ResultMatrixParameter() {
    }

    public ResultMatrixParameter(Integer attendanceId, ListResultRow resultList) {
        this.attendanceId = attendanceId;
        this.resultList = resultList;
    }

    public Integer getAttendanceId() {
        return attendanceId;
    }

    public ResultMatrixParameter setAttendanceId(Integer attendanceId) {
        this.attendanceId = attendanceId;
        return this;
    }

    public ListResultRow getResultList() {
        return resultList;
    }

    public ResultMatrixParameter setResultList(ListResultRow resultList) {
        this.resultList = resultList;
        return this;
    }
}

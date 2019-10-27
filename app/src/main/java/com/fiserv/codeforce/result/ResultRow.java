package com.fiserv.codeforce.result;

import java.io.Serializable;

public class ResultRow implements Serializable {
    Integer areaId;
    ListColumnResults results;

    public ResultRow() {
    }

    public ResultRow(Integer areaId, ListColumnResults results) {
        this.areaId = areaId;
        this.results = results;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public ResultRow setAreaId(Integer areaId) {
        this.areaId = areaId;
        return this;
    }

    public ListColumnResults getResults() {
        return results;
    }

    public ResultRow setResults(ListColumnResults results) {
        this.results = results;
        return this;
    }
}

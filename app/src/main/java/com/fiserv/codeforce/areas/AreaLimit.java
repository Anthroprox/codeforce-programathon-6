package com.fiserv.codeforce.areas;

public class AreaLimit {
    Integer formId;
    Integer areaId;
    Integer minValue;
    Integer maxValue;

    public AreaLimit() {
    }

    public AreaLimit(Integer formId, Integer areaId, Integer minValue, Integer maxValue) {
        this.formId = formId;
        this.areaId = areaId;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public Integer getFormId() {
        return formId;
    }

    public AreaLimit setFormId(Integer formId) {
        this.formId = formId;
        return this;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public AreaLimit setAreaId(Integer areaId) {
        this.areaId = areaId;
        return this;
    }

    public Integer getMinValue() {
        return minValue;
    }

    public AreaLimit setMinValue(Integer minValue) {
        this.minValue = minValue;
        return this;
    }

    public Integer getMaxValue() {
        return maxValue;
    }

    public AreaLimit setMaxValue(Integer maxValue) {
        this.maxValue = maxValue;
        return this;
    }
}

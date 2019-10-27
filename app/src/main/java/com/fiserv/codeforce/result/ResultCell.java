package com.fiserv.codeforce.result;


public class ResultCell {
    private Integer id;
    private Integer index;
    private Integer value;

    public ResultCell() {
    }

    public ResultCell(Integer id, Integer index, Integer value) {
        this.id = id;
        this.index = index;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public ResultCell setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getIndex() {
        return index;
    }

    public ResultCell setIndex(Integer index) {
        this.index = index;
        return this;
    }

    public Integer getValue() {
        return value;
    }

    public ResultCell setValue(Integer value) {
        this.value = value;
        return this;
    }
}



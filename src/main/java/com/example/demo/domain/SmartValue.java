package com.example.demo.domain;

import com.example.demo.data.SmartValueDTO;

/**
 * SmartValue entity class
 **/
public class SmartValue {
    private String value;
    private String tagLine;

    public String getValue() {
        return value;
    }

    void setValue(String value) {
        this.value = value;
    }

    public String getTagLine() {
        return tagLine;
    }

    void setTagLine(String tagLine) {
        this.tagLine = tagLine;
    }

    @Override
    public String toString() {
        return "SmartValue{" +
                "value='" + value + '\'' +
                ", tagLine='" + tagLine + '\'' +
                '}';
    }

    public SmartValueDTO toDTO() {
        return new SmartValueDTO(this.getValue(), this.getTagLine());
    }
}

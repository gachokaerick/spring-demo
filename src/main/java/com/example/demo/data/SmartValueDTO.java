package com.example.demo.data;

public class SmartValueDTO {
    private String value;
    private String tagLine;

    public SmartValueDTO(String value, String tagLine) {
        this.value = value;
        this.tagLine = tagLine;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTagLine() {
        return tagLine;
    }

    public void setTagLine(String tagLine) {
        this.tagLine = tagLine;
    }

    @Override
    public String toString() {
        return "SmartValue{" +
                "value='" + value + '\'' +
                ", tagLine='" + tagLine + '\'' +
                '}';
    }
}

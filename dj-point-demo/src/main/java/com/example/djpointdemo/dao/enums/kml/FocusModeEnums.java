package com.example.djpointdemo.dao.enums.kml;

/**
 * wpml:focusMode		 负载对焦模式
 */
public enum FocusModeEnums {

    FIRST_POINT("firstPoint", "首个航点自动对焦"),
    CUSTOM("custom", "标定对焦值对焦");

    private String value;
    private String description;

    FocusModeEnums(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

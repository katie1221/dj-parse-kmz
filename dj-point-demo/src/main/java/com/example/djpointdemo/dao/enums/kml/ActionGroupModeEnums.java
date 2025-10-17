package com.example.djpointdemo.dao.enums.kml;

/**
 * wpml:actionGroupMode	 动作执行模式
 */
public enum ActionGroupModeEnums {

//    MANUAL("manual", "手动控制"),
    SEQUENCE("sequence", "串行执行。即动作组内的动作依次按顺序执行");

    private String value;
    private String description;

    ActionGroupModeEnums(String value, String description) {
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

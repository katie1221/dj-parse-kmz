package com.example.djpointdemo.dao.enums.kml;

/**
 * wpml:actionTriggerType	 动作触发器类型
 */
public enum ActionTriggerTypeEnums {

    REACH_POINT("reachPoint", "到达航点时执行"),
    BETWEEN_ADJACENT_POINTS("betweenAdjacentPoints", "航段触发，均匀转云台"),
    MULTIPLE_TIMING("multipleTiming", "等时触发"),
    MULTIPLE_DISTANCE("multipleDistance", "等距触发");

    private String value;
    private String description;

    ActionTriggerTypeEnums(String value, String description) {
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

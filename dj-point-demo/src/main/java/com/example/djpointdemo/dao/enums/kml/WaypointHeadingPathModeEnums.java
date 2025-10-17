package com.example.djpointdemo.dao.enums.kml;

/**
 * wpml:waypointHeadingPathMode	 飞行器偏航角转动方向
 */
public enum WaypointHeadingPathModeEnums {

    CLOCKWISE("clockwise", "顺时针旋转飞行器偏航角"),
    COUNTER_CLOCKWISE("counterClockwise", "逆时针旋转飞行器偏航角"),
    FOLLOW_BAD_ARC("followBadArc", "沿最短路径旋转飞行器偏航角");

    private String value;
    private String description;

    WaypointHeadingPathModeEnums(String value, String description) {
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

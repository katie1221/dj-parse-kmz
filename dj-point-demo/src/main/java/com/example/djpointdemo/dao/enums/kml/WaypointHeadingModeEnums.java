package com.example.djpointdemo.dao.enums.kml;

/**
 * wpml:waypointHeadingMode	 飞行器偏航角模式
 */
public enum WaypointHeadingModeEnums {

    FOLLOW_WAYLINE("followWayline", "沿航线方向。飞行器机头沿着航线方向飞至下一航点"),
    MANUALLY("manually", "手动控制。飞行器在飞至下一航点的过程中，用户可以手动控制飞行器机头朝向"),
    FIXED("fixed", "锁定当前偏航角。飞行器机头保持执行完航点动作后的飞行器偏航角飞至下一航点"),
    SMOOTH_TRANSITION("smoothTransition", "自定义。通过“wpml:waypointHeadingAngle”给定某航点的目标偏航角，并在航段飞行过程中均匀过渡至下一航点的目标偏航角。"),
    TOWARD_POI("towardPOI", "朝向兴趣点");

    private String value;
    private String description;

    WaypointHeadingModeEnums(String value, String description) {
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

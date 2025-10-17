package com.example.djpointdemo.dao.enums.kml;

/**
 * wpml:globalWaypointTurnMode	 全局航点类型（全局航点转弯模式）
 */
public enum GlobalWaypointTurnModeEnums {

    COORDINATE_TURN("coordinateTurn", "协调转弯，不过点，提前转弯"),
    TO_POINT_AND_STOP_WITH_DISCONTINUITY_CURVATURE("toPointAndStopWithDiscontinuityCurvature", "直线飞行，飞行器到点停"),
    TO_POINT_AND_STOP_WITH_CONTINUITY_CURVATURE("toPointAndStopWithContinuityCurvature", "曲线飞行，飞行器到点停"),
    TO_POINT_AND_PASS_WITH_CONTINUITY_CURVATURE("toPointAndPassWithContinuityCurvature", "曲线飞行，飞行器过点不停");

    private String value;
    private String description;

    GlobalWaypointTurnModeEnums(String value, String description) {
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

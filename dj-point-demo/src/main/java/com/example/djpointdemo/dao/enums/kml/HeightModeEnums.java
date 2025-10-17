package com.example.djpointdemo.dao.enums.kml;

/**
 * wpml:heightMode	 航点高程参考平面
 */
public enum HeightModeEnums {

    EGM96("EGM96", "使用海拔高编辑"),
    RELATIVE_TO_START_POINT("relativeToStartPoint", "使用相对点的高度进行编辑"),
    ABOVE_GROUND_LEVEL("aboveGroundLevel", "使用地形数据，AGL下编辑(仅支持司空2平台)"),
    REALTIME_FOLLOW_SURFACE("realTimeFollowSurface", "使用实时仿地模式（仅用于建图航拍模版），仅支持M3E/M3T/M3M机型");

    private String value;
    private String description;

    HeightModeEnums(String value, String description) {
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

package com.example.djpointdemo.dao.enums.kml;

/**
 * wpml:droneEnumValue	 飞行器机型主类型
 */
public enum DroneEnumValueEnums {

    M350_RTK(89, "M350 RTK"),
    M300_RTK(60, "M300 RTK"),
    M30_M30T(67, "M30/M30T"),
    M3E_M3T_M3M(77, "M3E/M3T/M3M"),
    M3D_M3TD(91, "M3D/M3TD");

    private Integer value;
    private String description;

    DroneEnumValueEnums(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

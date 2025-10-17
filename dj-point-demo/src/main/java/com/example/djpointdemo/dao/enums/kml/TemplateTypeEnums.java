package com.example.djpointdemo.dao.enums.kml;

/**
 * wpml:templateType	 预定义模板类型
 */
public enum TemplateTypeEnums {

    WAYPOINT("waypoint", "航点飞行"),
    MAPPING2D("mapping2d", "建图航拍"),
    MAPPING3D("mapping3d", "倾斜摄影"),
    MAPPING_STRIP("mappingStrip", "航带飞行");

    private String value;
    private String description;

    TemplateTypeEnums(String value, String description) {
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

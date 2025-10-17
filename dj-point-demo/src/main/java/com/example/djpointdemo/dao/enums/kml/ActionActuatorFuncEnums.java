package com.example.djpointdemo.dao.enums.kml;

/**
 * wpml:actionActuatorFunc	 动作类型
 */
public enum ActionActuatorFuncEnums {

    TAKE_PHOTO("takePhoto", "单拍"),
    START_RECORD("startRecord", "开始录像"),
    STOP_RECORD("stopRecord", "结束录像"),
    FOCUS("focus", "对焦"),
    ZOOM("zoom", "变焦"),
    CUSTOM_DIRNAME("customDirName", "创建新文件夹"),
    GIMBAL_ROTATE("gimbalRotate", "旋转云台"),
    ROTATE_YAW("rotateYaw", "飞行器偏航"),
    HOVER("hover", "悬停等待"),
    GIMBAL_EVENLY_ROTATE("gimbalEvenlyRotate", "航段间均匀转动云台pitch角"),
    ORIENTED_SHOOT("orientedShoot", "精准复拍动作"),
    PANO_SHOT("panoShot", "全景拍照动作"),
    RECORD_POINT_CLOUD("recordPointCloud", "点云录制操作");

    private String value;
    private String description;

    ActionActuatorFuncEnums(String value, String description) {
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

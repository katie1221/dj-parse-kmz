package com.example.djpointdemo.dao.kml;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * 动作列表
 */
@Data
@XStreamAlias("wpml:action")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KmlAction {

    /**
     * 动作id
     * 注：在一个动作组内该ID唯一。建议从0开始单调连续递增。
     */
    @XStreamAlias("wpml:actionId")
    private String actionId;

    /**
     * 动作类型
     *
     * takePhoto：单拍
     * startRecord：开始录像
     * stopRecord：结束录像
     * focus：对焦
     * zoom：变焦
     * customDirName：创建新文件夹
     * gimbalRotate：旋转云台
     * rotateYaw：飞行器偏航
     * hover：悬停等待
     * gimbalEvenlyRotate：航段间均匀转动云台pitch角
     * accurateShoot：精准复拍动作（已暂停维护，建议使用orientedShoot）
     * orientedShoot：定向拍照动作
     * panoShot：全景拍照动作（支持M30/M30T、M3D/M3TD，M4E/M4T）
     * recordPointCloud：点云录制操作
     */
    @XStreamAlias("wpml:actionActuatorFunc")
    private String actionActuatorFunc;

    /**
     * 动作参数 可选
     */
    @XStreamAlias("wpml:actionActuatorFuncParam")
    private KmlActionActuatorFuncParam actionActuatorFuncParam;


}

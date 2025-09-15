package com.example.djpointdemo.dao.kml;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * 任务信息
 * @author qzz
 * @date 2025/8/12
 */
@Data
@XStreamAlias("wpml:missionConfig")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KmlMissionConfig {

    /**
     * 飞向首航点模式
     *  safely：安全模式
     *  pointToPoint：倾斜飞行模式
     */
    @XStreamAlias("wpml:flyToWaylineMode")
    private String flyToWayLineMode;

    /**
     * 航线结束动作
     * goHome：飞行器完成航线任务后，退出航线模式并返航。
     * noAction：飞行器完成航线任务后，退出航线模式。
     * autoLand：飞行器完成航线任务后，退出航线模式并原地降落。
     * gotoFirstWaypoint：飞行器完成航线任务后，立即飞向航线起始点，到达后退出航线模式。
     */
    @XStreamAlias("wpml:finishAction")
    private String finishAction;

    /**
     * 失控是否继续执行航线
     * goContinue：继续执行航线
     * executeLostAction：退出航线，执行失控动作
     */
    @XStreamAlias("wpml:exitOnRCLost")
    private String exitOnRCLost;

    /**
     * 失控动作类型
     * goBack：返航。飞行器从失控位置飞向起飞点
     * landing：降落。飞行器从失控位置原地降落
     * hover：悬停。飞行器从失控位置悬停
     */
    @XStreamAlias("wpml:executeRCLostAction")
    private String executeRCLostAction;

    /**
     * 安全起飞高度
     * 遥控器场景 [1.2,1500]，机场场景 [8,1500] （高度模式：相对起飞点高度）
     * 注：飞行器起飞后，先爬升至该高度，再根据“飞向首航点模式”的设置飞至首航点。该元素仅在飞行器未起飞时生效。
     */
    @XStreamAlias("wpml:takeOffSecurityHeight")
    private String takeOffSecurityHeight;

    /**
     * 全局航线过渡速度
     * 注：飞行器飞往每条航线首航点的速度。航线任务中断时，飞行器从当前位置恢复至断点的速度。
     */
    @XStreamAlias("wpml:globalTransitionalSpeed")
    private String globalTransitionalSpeed;

    /**
     * 全局返航高度
     * 注：飞行器返航时，先爬升至该高度，再进行返航
     */
    @XStreamAlias("wpml:globalRTHHeight")
    private String globalRTHHeight;

    /**
     * 参考起飞点  可选
     * 注：<x,y,z>指<纬度，经度，高度>。“参考起飞点”仅做航线规划参考，飞行器执行航线时以飞行器真实的起飞点为准，高度使用椭球高
     */
    @XStreamAlias("wpml:takeOffRefPoint")
    private String takeOffRefPoint;

    /**
     * 参考起飞点海拔高度   可选
     * 注：”参考起飞点“海拔高度，与“参考起飞点”中的椭球高度对应。
     */
    @XStreamAlias("wpml:takeOffRefPointAGLHeight")
    private String takeOffRefPointAGLHeight;

    /**
     * 飞行器机型信息
     */
    @XStreamAlias("wpml:droneInfo")
    private KmlDroneInfo droneInfo;

    /**
     * 负载机型信息
     */
    @XStreamAlias("wpml:payloadInfo")
    private KmlPayloadInfo payloadInfo;

}

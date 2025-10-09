package com.example.djpointdemo.dao.kml;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * 全局偏航角模式参数
 */
@Data
@XStreamAlias("wpml:globalWaypointHeadingParam")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KmlGlobalWaypointHeadingParam {

    /**
     * 飞行器偏航角模式
     *
     * followWayline：沿航线方向。飞行器机头沿着航线方向飞至下一航点
     * manually：手动控制。飞行器在飞至下一航点的过程中，用户可以手动控制飞行器机头朝向
     * fixed：锁定当前偏航角。飞行器机头保持执行完航点动作后的飞行器偏航角飞至下一航点
     * smoothTransition：自定义。通过“wpml:waypointHeadingAngle”给定某航点的目标偏航角，并在航段飞行过程中均匀过渡至下一航点的目标偏航角。
     * towardPOI：朝向兴趣点
     */
    @XStreamAlias("wpml:waypointHeadingMode")
    private String waypointHeadingMode;

    /**
     * 飞行器偏航角度
     *
     * 给定某航点的目标偏航角，并在航段飞行过程中均匀过渡至下一航点的目标偏航角。
     */
    @XStreamAlias("wpml:waypointHeadingAngle")
    private String waypointHeadingAngle;

    /**
     * 兴趣点
     * 数据格式为：纬度,经度,高度
     * 注：仅当wpml:waypointHeadingMode为towardPOI该字段生效。目前不支持Z方向朝向兴趣点，高度可设置为0。当某一航点wpml:waypointHeadingMode设置为towardPOI后，飞行器从该航点飞向下一航点途中机头都将朝向兴趣点
     */
    @XStreamAlias("wpml:waypointPoiPoint")
    private String waypointPoiPoint;

    /**
     * 飞行器偏航角转动方向
     *
     * clockwise：顺时针旋转飞行器偏航角
     * counterClockwise：逆时针旋转飞行器偏航角
     * followBadArc：沿最短路径旋转飞行器偏航角
     */
    @XStreamAlias("wpml:waypointHeadingPathMode")
    private String waypointHeadingPathMode;

    /**
     */
    @XStreamAlias("wpml:waypointHeadingPoiIndex")
    private String waypointHeadingPoiIndex;


}

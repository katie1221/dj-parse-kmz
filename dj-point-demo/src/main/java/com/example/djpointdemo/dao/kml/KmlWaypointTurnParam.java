package com.example.djpointdemo.dao.kml;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * 航点类型（航点转弯模式）
 */
@Data
@XStreamAlias("wpml:waypointTurnParam")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KmlWaypointTurnParam {

    /**
     * 航点类型（航点转弯模式）
     * coordinateTurn：协调转弯，不过点，提前转弯
     * toPointAndStopWithDiscontinuityCurvature：直线飞行，飞行器到点停
     * toPointAndStopWithContinuityCurvature：曲线飞行，飞行器到点停
     * toPointAndPassWithContinuityCurvature：曲线飞行，飞行器过点不停
     * 注：DJI Pilot 2/司空 2 上“平滑过点，提前转弯”模式设置方法为：
     * 1）将wpml:waypointTurnMode设置为toPointAndPassWithContinuityCurvature
     * 2）将wpml:useStraightLine设置为1
     */
    @XStreamAlias("wpml:waypointTurnMode")
    private String waypointTurnMode;

    /**
     * 航点转弯截距
     * (0, 航段最大长度]
     * 注：两航点间航段长度必需大于两航点转弯截距之和。此元素定义了飞行器在距离该航点若干米前，提前多少距离转弯。
     */
    @XStreamAlias("wpml:waypointTurnDampingDist")
    private String waypointTurnDampingDist;

}

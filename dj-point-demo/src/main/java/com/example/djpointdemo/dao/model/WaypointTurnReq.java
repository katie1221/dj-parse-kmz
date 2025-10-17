package com.example.djpointdemo.dao.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 全局航点转弯模式
 **/
@Data
public class WaypointTurnReq implements Serializable {

    /**
     * 航点转弯模式
     *
     * coordinateTurn：协调转弯，不过点，提前转弯
     * toPointAndStopWithDiscontinuityCurvature：直线飞行，飞行器到点停
     * toPointAndStopWithContinuityCurvature：曲线飞行，飞行器到点停
     * toPointAndPassWithContinuityCurvature：曲线飞行，飞行器过点不停
     */
    private String waypointTurnMode;

    /**
     * 航点转弯截距
     *
     * (0, 航段最大长度]
     * 注：两航点间航段长度必需大于两航点转弯截距之和。此元素定义了飞行器在距离该航点若干米前，提前多少距离转弯。
     */
    private Double waypointTurnDampingDist;

    /**
     * 该航段是否贴合直线
     *
     * 0：航段轨迹全程为曲线 1：航段轨迹尽量贴合两点连线
     * 注：当且仅当“wpml:waypointTurnParam”内"waypointTurnMode"被设置为“toPointAndStopWithContinuityCurvature”
     *      或“toPointAndPassWithContinuityCurvature”时必需。如果此元素被设置，则局部定义会覆盖全局定义。
     */
    private Integer useStraightLine;


}

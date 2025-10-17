package com.example.djpointdemo.dao.model;

import lombok.Data;
import java.io.Serializable;
import java.util.List;

/**
 * 航点
 */
@Data
public class RoutePointReq implements Serializable {

    /**
     * 航点编号
     */
    private Integer routePointIndex;

    /**
     * 经度（WGS84坐标系）
     */
    private Double longitude;

    /**
     * 纬度（WGS84坐标系）
     */
    private Double latitude;

    /**
     * 高度（WGS84椭球高度）
     */
    private Double height;

    /**
     * 飞行速度
     */
    private Double speed;

    /**
     * 航点偏航角
     */
    private WaypointHeadingReq waypointHeadingReq;

    /**
     * 航点转弯模式
     */
    private WaypointTurnReq waypointTurnReq;

    /**
     * 航点云台俯仰角
     */
    private Double gimbalPitchAngle;

    /**
     * 航点动作列表
     */
    private List<PointActionReq> actions;

    /**
     * 是否首尾航点（首尾航点不能是协调转弯类型）
     */
    private Boolean isStartAndEndPoint = false;

    /**
     * 等时拍照间隔时间 单位s
     */
    private Double timeInterval;

    /**
     * 等距拍照间隔距离 单位m
     */
    private Double distanceInterval;

    /**
     * 停止间隔拍照航点编号（动作组结束生效的航点）
     */
    private Integer endIntervalRouteIndex;

}

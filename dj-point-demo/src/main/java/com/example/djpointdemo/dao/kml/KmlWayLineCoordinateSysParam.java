package com.example.djpointdemo.dao.kml;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * 坐标系参数
 * @author qzz
 * @date 2025/8/12
 */
@Data
@XStreamAlias("wpml:waylineCoordinateSysParam")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KmlWayLineCoordinateSysParam {

    /**
     * 经纬度坐标系 WGS84：当前固定使用
     */
    @XStreamAlias("wpml:coordinateMode")
    private String coordinateMode;

    /**
     * 航点高程参考平面
     * EGM96：使用海拔高编辑
     * relativeToStartPoint：使用相对点的高度进行编辑
     * aboveGroundLevel：使用地形数据，AGL下编辑(仅支持司空2平台)
     * realTimeFollowSurface: 使用实时仿地模式（仅用于建图航拍模版），仅支持M3E/M3T/M3M机型
     */
    @XStreamAlias("wpml:heightMode")
    private String heightMode;

    /**
     * 经纬度与高度数据源 可选
     * GPS：位置数据采集来源为GPS/BDS/GLONASS/GALILEO等
     * RTKBaseStation：采集位置数据时，使用RTK基站进行差分定位
     * QianXun：采集位置数据时，使用千寻网络RTK进行差分定位
     * Custom：采集位置数据时，使用自定义网络RTK进行差分定位
     */
    @XStreamAlias("wpml:positioningType")
    private String positioningType;

    /**
     * 飞行器离被摄面高度（相对地面高）---用于计算拍照间距和GSD
     * 注：仅适用于模板类型mapping2d，mapping3d，mappingStrip
     */
    @XStreamAlias("wpml:globalShootHeight")
    private String globalShootHeight;

    /**
     * 是否开启仿地飞行 0：不开启1：开启
     * 注：仅适用于模板类型mapping2d，mapping3d，mappingStrip
     */
    @XStreamAlias("wpml:surfaceFollowModeEnable")
    private String surfaceFollowModeEnable;

    /**
     * 仿地飞行离地高度（相对地面高）
     * 注：仅适用于模板类型mapping2d，mapping3d，mappingStrip；当且仅当“wpml:surfaceFollowModeEnable”为“1”时必需
     */
    @XStreamAlias("wpml:surfaceRelativeHeight")
    private String surfaceRelativeHeight;
}

package com.example.djpointdemo.dao.kml;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import lombok.Data;

import java.util.List;

/**
 * 航点飞行模板信息
 * @author qzz
 * @date 2025/8/12
 */
@Data
@XStreamAlias("Folder")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KmlFolder {

    /**
     * 预定义模板类型
     */
    @XStreamAlias("wpml:templateType")
    private String templateType;

    /**
     * 模板ID
     * 注：在一个kmz文件内该ID唯一。建议从0开始单调连续递增。在template.kml和waylines.wpml文件中，将使用该id将模板与所生成的可执行航线进行关联。
     */
    @XStreamAlias("wpml:templateId")
    private String templateId;

    /**
     * 全局航线飞行速度  注：该元素定义了此模板生成的整段航线中，飞行器的目标飞行速度。如果额外定义了某航点的该元素，则局部定义会覆盖全局定义。
     */
    @XStreamAlias("wpml:autoFlightSpeed")
    private String autoFlightSpeed;

    /**
     * 坐标系参数
     */
    @XStreamAlias("wpml:waylineCoordinateSysParam")
    private KmlWayLineCoordinateSysParam waylineCoordinateSysParam;

    /**
     * 负载设置
     */
    @XStreamAlias("wpml:payloadParam")
    private KmlPayloadParam payloadParam;

    // 以下为航点飞行模板元素
    /**
     * 全局航点类型（全局航点转弯模式）
     * coordinateTurn：协调转弯，不过点，提前转弯
     * toPointAndStopWithDiscontinuityCurvature：直线飞行，飞行器到点停
     * toPointAndStopWithContinuityCurvature：曲线飞行，飞行器到点停
     * toPointAndPassWithContinuityCurvature：曲线飞行，飞行器过点不停
     */
    @XStreamAlias("wpml:globalWaypointTurnMode")
    private String globalWaypointTurnMode;

    /**
     * 全局航段轨迹是否尽量贴合直线
     * 0：航段轨迹全程为曲线
     * 1：航段轨迹尽量贴合两点连线
     *
     * 注：当且仅当“wpml:globalWaypointTurnMode”被设置为“toPointAndStopWithContinuityCurvature”或“toPointAndPassWithContinuityCurvature”时必需。
     * 如果额外定义了某航点的该元素，则局部定义会覆盖全局定义。
     */
    @XStreamAlias("wpml:globalUseStraightLine")
    private String globalUseStraightLine;

    /**
     * 云台俯仰角控制模式
     * manual：手动控制。
     * usePointSetting：依照每个航点设置。
     */
    @XStreamAlias("wpml:gimbalPitchMode")
    private String gimbalPitchMode;

    /**
     * 全局航线高度（相对起飞点高度）
     */
    @XStreamAlias("wpml:globalHeight")
    private String globalHeight;

    /**
     * 全局偏航角模式参数
     */
    @XStreamAlias("wpml:globalWaypointHeadingParam")
    private KmlGlobalWaypointHeadingParam globalWaypointHeadingParam;

    /**
     * 航点信息（包括航点经纬度和高度等）
     */
    @XStreamImplicit(itemFieldName = "Placemark")
    private List<KmlPlacemark> placemarkList;

    // 以下为 wpml 文件使用
    /**
     * 执行高度模式
     * WGS84：椭球高模式
     * relativeToStartPoint：相对起飞点高度模式
     * realTimeFollowSurface: 使用实时仿地模式，仅支持M3E/M3T/M3M
     * 注：该元素仅在waylines.wpml中使用。
     */
    @XStreamAlias("wpml:executeHeightMode")
    private String executeHeightMode;

    /**
     * 航线ID
     * 注：在一条航线中该ID唯一。建议从0开始单调连续递增。
     */
    @XStreamAlias("wpml:waylineId")
    private String waylineId;

    @XStreamAlias("wpml:duration")
    private String duration;

    @XStreamAlias("wpml:distance")
    private String distance;

    /**
     * 航线初始动作
     * 注：该元素用于规划一系列初始动作，在航线开始前执行。航线中断恢复时，先执行初始动作，再执行航点动作
     */
    @XStreamAlias("wpml:startActionGroup")
    private KmlActionGroup startActionGroup;
}

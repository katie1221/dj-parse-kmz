package com.example.djpointdemo.dao.kml;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import lombok.Data;

import java.util.List;

/**
 * 航点信息（包括航点经纬度和高度等）
 * @author qzz
 * @date 2025/8/12
 */
@Data
@XStreamAlias("Placemark")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KmlPlacemark {

    /**
     * 是否危险点  0：正常点，1：危险点  可选
     */
    @XStreamAlias("wpml:isRisky")
    private String isRisky;

    /**
     * 航点经纬度<经度,纬度>
     * 注：此处格式如“<Point> <coordinates> 经度,纬度 </coordinates> </Point>”
     */
    @XStreamAlias("Point")
    private KmlPoint kmlPoint;

    /**
     * 航点序号
     * 注：在一条航线内该ID唯一。该序号必须从0开始单调连续递增。
     */
    @XStreamAlias("wpml:index")
    private String index;

    /**
     * 是否使用全局高度
     */
    @XStreamAlias("wpml:useGlobalHeight")
    private String useGlobalHeight;

    /**
     * 航点高度（WGS84椭球高度）
     * 注：该元素与“wpml:height”配合使用，二者是同一位置不同高程参考平面的表达。
     * 注：当且仅当“wpml:useGlobalHeight”为“0”时必需
     */
    @XStreamAlias("wpml:ellipsoidHeight")
    private String ellipsoidHeight;

    /**
     * 航点高度（EGM96海拔高度/相对起飞点高度/AGL相对地面高度）
     * 注：该元素与“wpml:ellipsoidHeight”配合使用，二者是同一位置不同高程参考平面的表达。
     * 注：当且仅当“wpml:useGlobalHeight”为“0”时必需
     */
    @XStreamAlias("wpml:height")
    private String height;

    /**
     * 是否使用全局飞行速度 0：不使用全局设置 1：使用全局设置
     * 注：此处的全局飞行速度即“wpml:autoFlightSpeed”
     */
    @XStreamAlias("wpml:useGlobalSpeed")
    private String useGlobalSpeed;

    /**
     * 航点飞行速度
     * 注：当且仅当“wpml:useGlobalSpeed”为“0”时必需
     */
    @XStreamAlias("wpml:waypointSpeed")
    private String waypointSpeed;

    /**
     * 是否使用全局偏航角模式参数  0：不使用全局设置 1：使用全局设置
     */
    @XStreamAlias("wpml:useGlobalHeadingParam")
    private String useGlobalHeadingParam;

    /**
     * 偏航角模式参数
     * 注：当且仅当“wpml:useGlobalHeadingParam”为“0”时必需
     */
    @XStreamAlias("wpml:waypointHeadingParam")
    private KmlWaypointHeadingParam waypointHeadingParam;

    /**
     * 是否使用全局航点类型（全局航点转弯模式）
     * 0：不使用全局设置 1：使用全局设置
     */
    @XStreamAlias("wpml:useGlobalTurnParam")
    private String useGlobalTurnParam;

    /**
     * 航点类型（航点转弯模式）
     * 注：当且仅当“wpml:useGlobalTurnParam”为“0”时必需
     */
    @XStreamAlias("wpml:waypointTurnParam")
    private KmlWaypointTurnParam waypointTurnParam;

    /**
     * 该航段是否贴合直线  0：航段轨迹全程为曲线 1：航段轨迹尽量贴合两点连线
     * 注：当且仅当“wpml:waypointTurnParam”内"waypointTurnMode"被设置为“toPointAndStopWithContinuityCurvature”
     * 或“toPointAndPassWithContinuityCurvature”时必需。如果此元素被设置，则局部定义会覆盖全局定义。
     */
    @XStreamAlias("wpml:useStraightLine")
    private String useStraightLine;

    /**
     * 航点云台俯仰角（对应机型云台可转动范围）
     * 注：当且仅当“wpml:gimbalPitchMode”为“usePointSetting”时必需。
     */
    @XStreamAlias("wpml:gimbalPitchAngle")
    private String gimbalPitchAngle;

    /**
     * 航点航线：actionGroup 有一个
     * 倾斜摄影：actionGroup 有多个
     */
    @XStreamImplicit(itemFieldName = "wpml:actionGroup")
    private List<KmlActionGroup> actionGroup;

    // 下面 wpml 文件使用
    /**
     * 航点执行高度
     * 注：该元素仅在waylines.wpml中使用。具体高程参考平面在“wpml:executeHeightMode”中声明。
     */
    @XStreamAlias("wpml:executeHeight")
    private String executeHeight;

    @XStreamAlias("wpml:waypointGimbalHeadingParam")
    private KmlWaypointGimbalHeadingParam waypointGimbalHeadingParam;

    // 建图航拍模板元素
    /**
     * 是否开启标定飞行
     * 注：仅适用于M300 RTK与M350 RTK机型
     */
    @XStreamAlias("wpml:caliFlightEnable")
    private String caliFlightEnable;

    /**
     * 是否开启高程优化
     */
    @XStreamAlias("wpml:elevationOptimizeEnable")
    private String elevationOptimizeEnable;

    /**
     * 拍照模式（定时或定距） time：等时间拍照  distance：等间隔拍照
     * 注：建议使用“time”等时间拍照。在template.kml文件中定义“拍照模式”、“重叠率”和“飞行速度”，计算后得出间隔时间或间隔距离距离写入waylines.wpml中。
     */
    @XStreamAlias("wpml:shootType")
    private String shootType;

    /**
     * 航线方向
     */
    @XStreamAlias("wpml:direction")
    private String direction;

    /**
     * 测区外扩距离
     */
    @XStreamAlias("wpml:margin")
    private String margin;

    /**
     * 测区多边形
     * * 注：此处格式如“<Polygon> <outerBoundaryIs> <LinearRing> <coordinates> 经度,纬度,高度 经度,纬度,高度 经度,纬度,高度 </coordinates> </LinearRing> </outerBoundaryIs> </Polygon>”
     * * 注：当 wpml:facadeWaylineEnable 为 1 时，测区多边形支持空中面，
     * 如“<Polygon> <outerBoundaryIs> <LinearRing> <coordinates> 经度,纬度,300 经度,纬度,200 经度,纬度,50 </coordinates> </LinearRing> </outerBoundaryIs> </Polygon>”，
     * 航线生成方向与端点顺序有关
     */
    @XStreamAlias("Polygon")
    private KmlPolygon polygon;

    // 倾斜摄影模板元素
    /**
     * 云台俯仰角度（倾斜）
     * 注：不同云台可转动范围不同。倾斜摄影模板会被生成五条航线，其中1条采集正射影像，4条采集倾斜影像。此元素用于设置倾斜影像采集时云台俯仰角度。
     */
    @XStreamAlias("wpml:inclinedGimbalPitch")
    private String inclinedGimbalPitch;

    /**
     * 航线飞行速度（倾斜）
     * 注：倾斜摄影模板会被生成五条航线，其中1条采集正射影像，4条采集倾斜影像。此元素用于设置倾斜影像采集时飞行目标速度。
     */
    @XStreamAlias("wpml:inclinedFlightSpeed")
    private String inclinedFlightSpeed;

    // 航带飞行模板元素
    /**
     * 是否开启单航线飞行 0：不开启 1：开启
     */
    @XStreamAlias("wpml:singleLineEnable")
    private String singleLineEnable;

    /**
     * 每个子航带航线长度
     */
    @XStreamAlias("wpml:cuttingDistance")
    private String cuttingDistance;

    /**
     * 是否开启边缘优化 0：不开启 1：开启
     */
    @XStreamAlias("wpml:boundaryOptimEnable")
    private String boundaryOptimEnable;

    /**
     * 航带左侧外扩距离
     */
    @XStreamAlias("wpml:leftExtend")
    private String leftExtend;

    /**
     * 航带右侧外扩距离
     */
    @XStreamAlias("wpml:rightExtend")
    private String rightExtend;

    /**
     * 是否包含中心线 0：不包含 1：包含
     */
    @XStreamAlias("wpml:includeCenterEnable")
    private String includeCenterEnable;

    /**
     * 是否开启变高航带
     * 注：该元素与“LineString”配合使用，开启后将按照椭球高读取其中的高度值。
     */
    @XStreamAlias("wpml:stripUseTemplateAltitude")
    private String stripUseTemplateAltitude;

    /**
     * 航点信息
     * 注：格式为 “<LineString> <coordinates> 经度,纬度,高度 经度,维度,高度 经度,纬度,高度 </coordinates> </LineString>”。其中高度值仅在 “wpml:stripUseTemplateAltitude” 开启时读取。
     */
    @XStreamAlias("LineString")
    private KmlLineString lineString;

    @XStreamAlias("wpml:waypointWorkType")
    private String waypointWorkType;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;
}

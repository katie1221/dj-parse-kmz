package com.example.djpointdemo.dao.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

/**
 * 生成航线kmz文件参数
 **/
@Data
public class KmlParamsReq implements Serializable {

    /**
     * 航线类型 waypoint：航点航线，mapping_2d：建图航拍，mapping_3d：倾斜摄影，mapping_strip：带状航线，facade：斜面航线，solid：几何体航线，mapping_gobject：贴近摄影
     */
    private String templateType;

    /**
     * 无人机主类型 67:经纬 M30系列 77：Mavic 3行业系列 91：Matrice 3D系列 99：Matrice 4行业系列 100：Matrice 4D系列 103：Matrice 400
     */
    private Integer droneType;

    /**
     * 无人机子类型
     *
     * 67主类型---- 0：经纬 M30     1：经纬 M30 T
     * 77主类型---- 0：Mavic 3E    1：Mavic 3T     3：Mavic 3TA
     * 91主类型---- 0：Matrice 3D  1：Matrice 3TD
     * 99主类型---- 0：Matrice 4E  1：Matrice 4T
     * 100主类型----0：Matrice 4D  1：Matrice 4TD
     * 103主类型----0：Matrice 400
     */
    private Integer subDroneType;

    /**
     * 负载主类型 82：H30 83：H30T 50：P1 84：L2  65532：V1 喊话器  65533：S1 探照灯
     */
    private Integer payloadType;

    /**
     * 负载子类型
     *
     * H30系列主类型---- 0：H30       2：H30T
     * P1主类型    ---- 0：P1-24mm   1：P1-35mm  2：P1-50mm
     * L2主类型    ---- 1：L2
     * PSDK主类型  ---- 0：V1 喊话器  0：S1 探照灯
     */
    private Integer payloadSubEnumValue;

    /**
     * 负载挂载位置  固定0
     *
     * 0：对于 M300 RTK 机型为视线随机头朝前，机身下方左云台。对于其他机型，对应主云台。
     * 1：对于 M300 RTK 机型为视线随机头朝前，机身下方右云台。
     * 2：对于 M300 RTK 机型为机身上方云台。
     * 7：指 FPV 相机
     */
    private Integer payloadPosition;

    /**
     * 负载图片存储类型
     *
     * wide：存储 广角镜头照片
     * zoom：存储 变焦镜头照片
     * ir：  存储 红外镜头照片
     * narrow_band: 存储 窄带镜头拍摄照片
     * visable：可见光照片
     * 注：存储多个镜头照片，格式
     */
    private String imageFormat;

    /**
     * 航线结束动作
     *
     * goHome：飞行器完成航线任务后，退出航线模式并返航。
     * noAction：飞行器完成航线任务后，退出航线模式。
     * autoLand：飞行器完成航线任务后，退出航线模式并原地降落。
     * gotoFirstWaypoint：飞行器完成航线任务后，立即飞向航线起始点，到达后退出航线模式。
     */
    private String finishAction;

    /**
     * 失控动作
     *
     * goBack：返航。飞行器从失控位置飞向起飞点
     * landing：降落。飞行器从失控位置原地降落
     * hover：悬停。飞行器从失控位置悬停
     */
    private String exitOnRcLostAction;

    /**
     * 全局航线高度（WGS84椭球高度）
     */
    private Double globalHeight;

    /**
     * 全局航线飞行速度
     */
    private Double autoFlightSpeed;

    /**
     * 全局偏航角模式
     */
    private WaypointHeadingReq waypointHeadingReq;

    /**
     * 全局航点转弯模式
     */
    private WaypointTurnReq waypointTurnReq;

    /**
     * 云台俯仰角控制模式
     *
     * manual：手动控制。
     * usePointSetting：依照每个航点设置。
     */
    private String gimbalPitchMode;

    /**
     * 参考起飞点
     * 注：<x,y,z>指<纬度，经度，高度>。“参考起飞点”仅做航线规划参考，飞行器执行航线时以飞行器真实的起飞点为准，高度使用椭球高
     */
    private String takeOffRefPoint;

    /**
     * 航点列表
     */
    private List<RoutePointReq> routePointList;

    /**
     * 建图航拍、倾斜摄影、航带飞行模板参数
     */
    private MappingTypeReq mappingTypeReq;

    /**
     * 航线初始动作列表
     */
    private List<PointActionReq> startActionList;

    /**
     * 倾斜摄影 航线列表
     */
    private List<FolderListReq> folderList;
}

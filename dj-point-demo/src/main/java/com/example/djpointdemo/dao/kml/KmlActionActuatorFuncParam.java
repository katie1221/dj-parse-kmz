package com.example.djpointdemo.dao.kml;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

@Data
@XStreamAlias("wpml:actionActuatorFuncParam")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KmlActionActuatorFuncParam {

    // takePhoto & startRecord & stopRecord

    /**
     * 负载挂载位置
     */
    @XStreamAlias("wpml:payloadPositionIndex")
    private String payloadPositionIndex;

    /**
     * 拍摄照片文件后缀  为生成媒体文件命名时将额外附带该后缀
     */
    @XStreamAlias("wpml:fileSuffix")
    private String fileSuffix;

    /**
     * 拍摄照片存储类型
     *
     * zoom: 存储变焦镜头拍摄照片
     * wide: 存储广角镜头拍摄照片
     * ir: 存储红外镜头拍摄照片
     * narrow_band: 存储窄带镜头拍摄照片
     * visable：可见光照片
     * 注：存储多个镜头照片，格式如“<wpml:payloadLensIndex>wide,ir,narrow_band</wpml:payloadLensIndex>”表示同时使用广角、红外和窄带镜头
     */
    @XStreamAlias("wpml:payloadLensIndex")
    private String payloadLensIndex;

    /**
     * 是否使用全局存储类型
     * 0：不使用全局设置   1：使用全局设置
     */
    @XStreamAlias("wpml:useGlobalPayloadLensIndex")
    private String useGlobalPayloadLensIndex;

    // focus

    /**
     * 是否点对焦
     */
    @XStreamAlias("wpml:isPointFocus")
    private String isPointFocus;

    /**
     * 对焦点位置
     * 注：对焦点或对焦区域左上角在画面的X轴（宽）坐标。0为最左侧，1为最右侧。
     */
    @XStreamAlias("wpml:focusX")
    private String focusX;

    /**
     * 对焦点位置
     * 注：对焦点或对焦区域左上角在画面的Y轴（高）坐标。0为最上方，1为最下方。
     */
    @XStreamAlias("wpml:focusY")
    private String focusY;

    /**
     * 对焦区域宽度比
     * 注：对焦区域大小占画面整体的比例，此为宽度比
     * 注：当且仅当“isPointFocus”为“0”（即区域对焦）时必需。
     */
    @XStreamAlias("wpml:focusRegionWidth")
    private String focusRegionWidth;

    /**
     * 对焦区域高度比
     * 注：对焦区域大小占画面整体的比例，此为高度比
     * 注：当且仅当“isPointFocus”为“0”（即区域对焦）时必需。
     */
    @XStreamAlias("wpml:focusRegionHeight")
    private String focusRegionHeight;

    /**
     * 是否无穷远对焦
     * 0: 非无穷远对焦 1: 无穷远对焦
     */
    @XStreamAlias("wpml:isInfiniteFocus")
    private String isInfiniteFocus;

    // zoom

    /**
     * 变焦焦距
     */
    @XStreamAlias("wpml:focalLength")
    private String focalLength;

    // customDirName

    /**
     * 新文件夹的名称
     */
    @XStreamAlias("wpml:directoryName")
    private String directoryName;

    // gimbalRotate & gimbalEvenlyRotate

    /**
     * 云台偏航角转动坐标系
     * north：相对地理北
     */
    @XStreamAlias("wpml:gimbalHeadingYawBase")
    private String gimbalHeadingYawBase;

    /**
     * 云台转动模式
     * absoluteAngle：绝对角度，相对于正北方的角度
     */
    @XStreamAlias("wpml:gimbalRotateMode")
    private String gimbalRotateMode;

    /**
     * 是否使能云台Pitch转动
     * 0：不使能 1：使能
     */
    @XStreamAlias("wpml:gimbalPitchRotateEnable")
    private String gimbalPitchRotateEnable;

    /**
     * 云台Pitch转动角度
     * 注：不同云台可转动范围不同
     */
    @XStreamAlias("wpml:gimbalPitchRotateAngle")
    private String gimbalPitchRotateAngle;

    /**
     * 是否使能云台Roll转动
     * 0：不使能 1：使能
     */
    @XStreamAlias("wpml:gimbalRollRotateEnable")
    private String gimbalRollRotateEnable;

    /**
     * 云台Roll转动角度
     * 注：不同云台可转动范围不同
     */
    @XStreamAlias("wpml:gimbalRollRotateAngle")
    private String gimbalRollRotateAngle;

    /**
     * 是否使能云台Yaw转动
     * 0：不使能 1：使能
     */
    @XStreamAlias("wpml:gimbalYawRotateEnable")
    private String gimbalYawRotateEnable;

    /**
     * 云台Yaw转动角度
     */
    @XStreamAlias("wpml:gimbalYawRotateAngle")
    private String gimbalYawRotateAngle;

    /**
     * 是否使能云台转动时间
     * 0：不使能 1：使能
     */
    @XStreamAlias("wpml:gimbalRotateTimeEnable")
    private String gimbalRotateTimeEnable;

    /**
     * 云台完成转动用时
     */
    @XStreamAlias("wpml:gimbalRotateTime")
    private String gimbalRotateTime;

    // rotateYaw

    /**
     * 飞行器目标偏航角（相对于地理北）
     * 注：飞行器旋转至该目标偏航角。0°为正北方向，90°为正东方向，-90°为正西方向，-180°/180°为正南方向。
     */
    @XStreamAlias("wpml:aircraftHeading")
    private String aircraftHeading;

    /**
     * 飞行器偏航角转动模式
     * clockwise：顺时针旋转  counterClockwise：逆时针旋转
     */
    @XStreamAlias("wpml:aircraftPathMode")
    private String aircraftPathMode;

    // hover

    /**
     * 飞行器悬停等待时间
     */
    @XStreamAlias("wpml:hoverTime")
    private String hoverTime;

    // orientedShoot

    /**
     * 是否框选精准复拍目标
     * 1: 已框选目标物  0: 未框选目标物
     * 注：该值设置为1，复拍时飞行器会自主寻找目标进行拍摄。该值设置为0，复拍时飞行器只会按照飞行器姿态和负载姿态进行动作重复，不会自主寻找目标
     */
    @XStreamAlias("wpml:accurateFrameValid")
    private String accurateFrameValid;

    /**
     * 目标框角度
     * 注：目标框的旋转角度(以Y轴为基准，顺时针旋转)
     */
    @XStreamAlias("wpml:targetAngle")
    private String targetAngle;

    /**
     * 动作唯一标识
     * 注：拍照时，该值将被写入照片文件中，用于关联动作和照片文件
     */
    @XStreamAlias("wpml:actionUUID")
    private String actionUUID;

    /**
     * 照片宽度
     */
    @XStreamAlias("wpml:imageWidth")
    private String imageWidth;

    /**
     * 照片高度
     */
    @XStreamAlias("wpml:imageHeight")
    private String imageHeight;

    /**
     * AF电机位置
     */
    @XStreamAlias("wpml:AFPos")
    private String AFPos;

    /**
     * 云台端口号
     * 拍摄照片的相机安装位置
     * *注：M30/M30T机型该值固定为0
     */
    @XStreamAlias("wpml:gimbalPort")
    private String gimbalPort;

    /**
     * 相机类型
     * 52（机型：M30双光相机）,
     * 53（机型：M30T三光相机）
     * 66（机型：Mavic 3E 相机）
     * 67（机型：Mavic 3T 相机）
     * 80（机型：Matrice 3D 相机）
     * 81（机型：Matrice 3TD 相机）
     */
    @XStreamAlias("wpml:orientedCameraType")
    private String orientedCameraType;

    /**
     * 照片文件路径  照片文件名
     */
    @XStreamAlias("wpml:orientedFilePath")
    private String orientedFilePath;

    /**
     * 照片文件MD5值
     */
    @XStreamAlias("wpml:orientedFileMD5")
    private String orientedFileMD5;

    /**
     * 照片文件大小  照片文件实际大小
     */
    @XStreamAlias("wpml:orientedFileSize")
    private String orientedFileSize;

    /**
     * 照片文件后缀
     * 为生成媒体文件命名时将额外附带该后缀
     */
    @XStreamAlias("wpml:orientedFileSuffix")
    private String orientedFileSuffix;

    /**
     * 光圈大小
     * 注：该值为真实光圈x100
     */
    @XStreamAlias("wpml:orientedCameraApertue")
    private String orientedCameraApertue;

    /**
     * 环境亮度
     */
    @XStreamAlias("wpml:orientedCameraLuminance")
    private String orientedCameraLuminance;

    /**
     * 快门时间
     */
    @XStreamAlias("wpml:orientedCameraShutterTime")
    private String orientedCameraShutterTime;

    /**
     * ISO
     */
    @XStreamAlias("wpml:orientedCameraISO")
    private String orientedCameraISO;

    /**
     * 拍照模式
     * normalPhoto: 普通拍照
     * lowLightSmartShooting：智能低光拍照
     */
    @XStreamAlias("wpml:orientedPhotoMode")
    private String orientedPhotoMode;

    // panoShot

    /**
     * 全景拍照模式
     *
     * panoShot_360：全景模式
     */
    @XStreamAlias("wpml:panoShotSubMode")
    private String panoShotSubMode;

    // recordPointCloud

    /**
     * 点云操作
     *
     * startRecord：开始点云录制
     * pauseRecord：暂停点云录制
     * resumeRecord：继续点云录制
     * stopRecord：结束点云录制
     */
    @XStreamAlias("wpml:recordPointCloudOperate")
    private String recordPointCloudOperate;
}

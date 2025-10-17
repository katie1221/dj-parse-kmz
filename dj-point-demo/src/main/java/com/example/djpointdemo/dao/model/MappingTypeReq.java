package com.example.djpointdemo.dao.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 建图航拍、倾斜摄影、航带飞行模板参数
 **/
@Data
public class MappingTypeReq implements Serializable {

    /**
     * 采集方式  camera：可见光 / lidar：激光
     */
    private String collectionMethod;

    /**
     * 镜头类型 camera：可见光 / lidar：激光
     */
    private String lensType;

    /**
     * 航向重叠率
     */
    private Integer overlapH;

    /**
     * 旁向重叠率
     */
    private Integer overlapW;

    /**
     * 是否开启高程优化
     *
     * 0：不开启
     * 1：开启，飞行器会在航线执行完毕后，飞向测区中心采集一组倾斜照片，优化高程精度。
     */
    private Integer elevationOptimizeEnable;

    /**
     * 拍照模式
     *
     * time：等时间拍照
     * distance：等间隔拍照
     * 注：建议使用“time”等时间拍照。
     * 在template.kml文件中定义“拍照模式”、“重叠率”和“飞行速度”，计算后得出间隔时间或间隔距离距离写入waylines.wpml中。
     */
    private String shootType;

    /**
     * 航线方向 整型 [0, 360]
     */
    private String direction;

    /**
     * 测区外扩距离 整型
     */
    private String margin;

    /**
     * 测区多边形坐标  经度,纬度,高度
     */
    private List<CoordinatePointReq> coordinates;


}

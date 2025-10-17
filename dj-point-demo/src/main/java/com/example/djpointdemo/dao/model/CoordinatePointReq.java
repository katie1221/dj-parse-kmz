package com.example.djpointdemo.dao.model;

import lombok.Data;
import java.io.Serializable;

/**
 * 测区多边形坐标  经度,纬度,高度
 **/
@Data
public class CoordinatePointReq implements Serializable {

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

}

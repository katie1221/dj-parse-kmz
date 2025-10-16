package com.example.djpointdemo.dao.kml;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * 测区多边形：经度,纬度,高度
 * @author qzz
 * @date 2025/8/12
 */
@Data
@XStreamAlias("Polygon")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KmlPolygon {

    @XStreamAlias("outerBoundaryIs")
    private KmlOuterBoundaryIs outerBoundaryIs;
}

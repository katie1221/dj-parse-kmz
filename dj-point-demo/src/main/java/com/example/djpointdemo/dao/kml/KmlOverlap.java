package com.example.djpointdemo.dao.kml;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

@Data
@XStreamAlias("wpml:overlap")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KmlOverlap {

    @XStreamAlias("wpml:orthoLidarOverlapH")
    private String orthoLidarOverlapH;

    @XStreamAlias("wpml:orthoLidarOverlapW")
    private String orthoLidarOverlapW;

    @XStreamAlias("wpml:orthoCameraOverlapH")
    private String orthoCameraOverlapH;

    @XStreamAlias("wpml:orthoCameraOverlapW")
    private String orthoCameraOverlapW;

    @XStreamAlias("wpml:inclinedLidarOverlapH")
    private String inclinedLidarOverlapH;

    @XStreamAlias("wpml:inclinedLidarOverlapW")
    private String inclinedLidarOverlapW;

    @XStreamAlias("wpml:inclinedCameraOverlapH")
    private String inclinedCameraOverlapH;

    @XStreamAlias("wpml:inclinedCameraOverlapW")
    private String inclinedCameraOverlapW;

}

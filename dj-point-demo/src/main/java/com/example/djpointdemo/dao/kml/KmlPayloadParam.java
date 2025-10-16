package com.example.djpointdemo.dao.kml;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * 负载设置
 */
@Data
@XStreamAlias("wpml:payloadParam")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KmlPayloadParam {

    @XStreamAlias("wpml:payloadPositionIndex")
    private String payloadPositionIndex;

    @XStreamAlias("wpml:focusMode")
    private String focusMode;

    @XStreamAlias("wpml:meteringMode")
    private String meteringMode;

    @XStreamAlias("wpml:dewarpingEnable")
    private String dewarpingEnable;

    @XStreamAlias("wpml:returnMode")
    private String returnMode;

    @XStreamAlias("wpml:samplingRate")
    private String samplingRate;

    @XStreamAlias("wpml:scanningMode")
    private String scanningMode;

    @XStreamAlias("wpml:modelColoringEnable")
    private String modelColoringEnable;

    @XStreamAlias("wpml:imageFormat")
    private String imageFormat;


}

package com.example.djpointdemo.dao.kml;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * 负载机型信息
 */
@Data
@XStreamAlias("wpml:payloadInfo")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KmlPayloadInfo {

    /**
     * 负载机型主类型
     * 负载机型类型枚举值请参考产品支持页面中的相机枚举值中type-subtype-gimbalindex中的type字段
     */
    @XStreamAlias("wpml:payloadEnumValue")
    private String payloadEnumValue;

    @XStreamAlias("wpml:payloadSubEnumValue")
    private String payloadSubEnumValue;

    /**
     * 负载挂载位置
     * 负载挂载位置枚举值请参考产品支持页面中的相机枚举值中type-subtype-gimbalindex中的gimbalindex字段
     */
    @XStreamAlias("wpml:payloadPositionIndex")
    private String payloadPositionIndex;

}

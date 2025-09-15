package com.example.djpointdemo.dao.kml;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * 飞行器机型信息
 */
@Data
@XStreamAlias("wpml:droneInfo")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KmlDroneInfo {

    /**
     * 飞行器机型主类型
     * 飞行器主类型枚举值请参考产品支持页面中的飞行器/遥控器/机场枚举值中的主类型(type)
     */
    @XStreamAlias("wpml:droneEnumValue")
    private String droneEnumValue;

    /**
     * 飞行器机型子类型
     *  飞行器子类型枚举值请参考产品支持页面中的飞行器/遥控器/机场枚举值中的子类型(sub_type)
     *
     *  注：当“飞行器机型主类型”为有效值时，该元素才是必需
     */
    @XStreamAlias("wpml:droneSubEnumValue")
    private String droneSubEnumValue;

}

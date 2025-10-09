package com.example.djpointdemo.dao.kml;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * 动作组触发器
 */
@Data
@XStreamAlias("wpml:actionTrigger")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KmlActionTrigger {

    /**
     * 动作触发器类型
     * reachPoint：到达航点时执行
     * betweenAdjacentPoints：航段触发，均匀转云台
     * multipleTiming：等时触发
     * multipleDistance：等距触发
     *
     * 注：“betweenAdjacentPoints”需配合动作"gimbalEvenlyRotate"使用，
     * “multipleTiming” 配合动作 “takePhoto” 即可实现等时间隔拍照，
     * “multipleDistance” 配合动作 “takePhoto” 即可实现等距离间隔拍照。
     */
    @XStreamAlias("wpml:actionTriggerType")
    private String actionTriggerType;

    /**
     * 动作触发器参数
     * > 0
     * 注：当“actionTriggerType”为“multipleTiming”时，该元素表示间隔时间，单位是s。
     * 当“actionTriggerType”为“multipleDistance”时，该元素表示间隔距离，单位是m。
     */
    @XStreamAlias("wpml:actionTriggerParam")
    private String actionTriggerParam;


}

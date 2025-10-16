package com.example.djpointdemo.dao.kml;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import lombok.Data;

import java.util.List;

/**
 * 航线初始动作
 */
@Data
@XStreamAlias("wpml:actionGroup")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KmlActionGroup {

    /**
     * 动作组id
     * 注：在一个kmz文件内该ID唯一。建议从0开始单调连续递增。
     */
    @XStreamAlias("wpml:actionGroupId")
    private String actionGroupId;

    /**
     * 动作组开始生效的航点
     */
    @XStreamAlias("wpml:actionGroupStartIndex")
    private String actionGroupStartIndex;

    /**
     * 动作组结束生效的航点
     * 注：当“动作组结束生效的航点”与“动作组开始生效的航点”一致，则代表该动作组仅在该航点处生效。
     */
    @XStreamAlias("wpml:actionGroupEndIndex")
    private String actionGroupEndIndex;

    /**
     * 动作执行模式
     * sequence：串行执行。即动作组内的动作依次按顺序执行。
     */
    @XStreamAlias("wpml:actionGroupMode")
    private String actionGroupMode;

    /**
     * 动作组触发器
     */
    @XStreamAlias("wpml:actionTrigger")
    private KmlActionTrigger actionTrigger;

    /**
     * 动作列表
     */
    @XStreamImplicit
    private List<KmlAction> action;

}

package com.example.djpointdemo.dao.kml;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import lombok.Data;

import java.util.List;

/**
 * @author qzz
 * @date 2025/8/12
 */
@Data
@XStreamAlias("Document")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KmlDocument {

    /**
     * 创建信息：文件创建作者,可选
     */
    @XStreamAlias("wpml:author")
    private String author;

    /**
     * 创建信息：文件创建时间,可选
     */
    @XStreamAlias("wpml:createTime")
    private String createTime;

    /**
     * 创建信息：文件更新时间,可选
     */
    @XStreamAlias("wpml:updateTime")
    private String updateTime;

    /**
     * 任务信息：航线任务的全局参数
     */
    @XStreamAlias("wpml:missionConfig")
    private KmlMissionConfig kmlMissionConfig;

    /**
     * 模板信息：航点飞行模板信息    设置List的理由：每个Folder代表一条可执行的航线。特别的，当使用“倾斜摄影”模板时，将生成5条可执行航线，对应waylines.wpml内的5个Folder元素。
     */
    @XStreamImplicit(itemFieldName = "Folder")
    private List<KmlFolder> folder;
}

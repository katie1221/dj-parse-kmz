package com.example.djpointdemo.dao.kml;

import lombok.Data;
import java.util.List;

/**
 * 航线文件
 */
@Data
public class KmlParams {

    /**
     * 任务信息
     */
    private KmlMissionConfig kmlMissionConfig;

    /**
     * 模板信息：航点飞行模板信息    设置List的理由：每个Folder代表一条可执行的航线。特别的，当使用“倾斜摄影”模板时，将生成5条可执行航线，对应waylines.wpml内的5个Folder元素。
     */
    private List<KmlFolder> folder;

    /**
     * 航线名称
     */
    private String name;

    /**
     * 项目id
     */
    private String projectUuid;

    /**
     * 是否临时航线 1：是 0：否
     */
    private Boolean temporaryFlag;
}


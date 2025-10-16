package com.example.djpointdemo.dto;

import lombok.Data;
import java.util.List;

/**
 * 临时航线请求参数
 * @author qzz
 * @date 2025/8/19
 */
@Data
public class AirLineTemporaryRequestJson {

    /**
     * 项目UUID
     */
    private String projectUuid;

    /**
     * 坐标集合
     */
    private List<Gps> gpsList;
}

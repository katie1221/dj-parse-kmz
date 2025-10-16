package com.example.djpointdemo.service;

import com.example.djpointdemo.dao.kml.KmlInfo;
import com.example.djpointdemo.dao.kml.KmlParams;
import com.example.djpointdemo.dto.AirLineTemporaryRequestJson;

/**
 * @author qzz
 * @date 2025/8/12
 */
public interface DjAirLineParseService {

    /**
     * 解析kmz文件 HTTP网络地址
     * @param fileUrl
     * @param type 0:kmlInfo返回 1:wpmlInfo返回
     * @return
     */
    KmlInfo parseKmz(String fileUrl, Short type);

    /**
     * 解析kmz文件  本地文件
     * @param fileUrl
     * @return
     */
    KmlInfo parseKmzLocalFile(String fileUrl);

    /**
     * 生成kmz文件（带航点）
     * @param kmlParams 航线文件参数
     * @return
     */
    String createKmz(KmlParams kmlParams);

    /**
     * 新建临时航线文件，并同步到大疆
     * @param requestJson
     * @return
     */
    String createTemporaryKmzUploadDj(AirLineTemporaryRequestJson requestJson);
}

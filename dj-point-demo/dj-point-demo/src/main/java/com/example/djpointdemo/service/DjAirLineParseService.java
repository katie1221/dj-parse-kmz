package com.example.djpointdemo.service;

import com.example.djpointdemo.dao.kml.KmlInfo;

/**
 * @author qzz
 * @date 2025/8/12
 */
public interface DjAirLineParseService {

    /**
     * 解析kmz文件
     * @param fileUrl
     * @return
     */
    KmlInfo parseKmz(String fileUrl);
}

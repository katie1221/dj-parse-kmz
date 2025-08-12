package com.example.djpointdemo.controller;

import com.example.djpointdemo.dao.kml.KmlInfo;
import com.example.djpointdemo.service.DjAirLineParseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 解析航线kmz文件
 * @author qzz
 * @date 2025/8/12
 */
@RestController
public class DjAirLineParseController {

    @Autowired
    private DjAirLineParseService djAirLineParseService;

    /**
     * 解析kmz文件
     * @param fileUrl
     * @return
     */
    @GetMapping("/parseKmz")
    public KmlInfo parseKmz(@RequestParam("fileUrl") String fileUrl){
        return djAirLineParseService.parseKmz(fileUrl);
    }
}

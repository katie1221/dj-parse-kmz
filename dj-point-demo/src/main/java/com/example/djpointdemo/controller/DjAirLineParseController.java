package com.example.djpointdemo.controller;

import com.example.djpointdemo.dao.kml.KmlInfo;
import com.example.djpointdemo.dao.kml.KmlParams;
import com.example.djpointdemo.service.DjAirLineParseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * 解析kmz文件 网络文件
     * @param fileUrl
     * @return
     */
    @GetMapping("/parseKmz")
    public KmlInfo parseKmz(@RequestParam("fileUrl") String fileUrl){
        return djAirLineParseService.parseKmz(fileUrl);
    }

    /**
     * 解析kmz文件 本地文件
     * @param fileUrl
     * @return
     */
    @GetMapping("/parseKmzLocalFile")
    public KmlInfo parseKmzLocalFile(@RequestParam("fileUrl") String fileUrl){
        return djAirLineParseService.parseKmzLocalFile(fileUrl);
    }

    /**
     * 生成kmz文件
     * @param kmlParams
     * @return
     */
    @PostMapping("/createKmz")
    public String createKmz(@RequestBody KmlParams kmlParams){
        return djAirLineParseService.createKmz(kmlParams);
    }
}

package com.example.djpointdemo.service.impl;

import cn.hutool.core.io.FileUtil;
import com.example.djpointdemo.dao.kml.KmlInfo;
import com.example.djpointdemo.service.DjAirLineParseService;
import com.example.djpointdemo.util.ParseFileUtils;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Objects;

/**
 * @author qzz
 * @date 2025/8/12
 */
@Service
public class DjAirLineParseServiceImpl implements DjAirLineParseService {

    /**
     * 解析kmz文件
     * @param fileUrl
     * @return
     */
    @Override
    public KmlInfo parseKmz(String fileUrl) {
        File file = new File(fileUrl);
        try (ArchiveInputStream archiveInputStream = new ZipArchiveInputStream(FileUtil.getInputStream(file))) {
            ArchiveEntry entry;
            KmlInfo kmlInfo = new KmlInfo();
            KmlInfo wpmlInfo = new KmlInfo();
            while (!Objects.isNull(entry = archiveInputStream.getNextEntry())) {
                String name = entry.getName();
                if (name.toLowerCase().endsWith(".kml")) {
                    kmlInfo = ParseFileUtils.parseKml(archiveInputStream);
                } else if (name.toLowerCase().endsWith(".wpml")) {
                    wpmlInfo = ParseFileUtils.parseKml(archiveInputStream);
                }
            }
            return kmlInfo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

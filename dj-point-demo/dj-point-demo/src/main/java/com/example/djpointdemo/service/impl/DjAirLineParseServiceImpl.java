package com.example.djpointdemo.service.impl;

import com.example.djpointdemo.dao.kml.KmlInfo;
import com.example.djpointdemo.service.DjAirLineParseService;
import com.example.djpointdemo.util.ParseFileUtils;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.springframework.stereotype.Service;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * @author qzz  你把 HTTP 网络地址直接当成本地文件路径传给 File 类了 ，File 是用来操作本地文件系统路径的，不支持 HTTP URL 。
 * @date 2025/8/12
 */
@Service
public class DjAirLineParseServiceImpl implements DjAirLineParseService {

    /**
     * 解析kmz文件   HTTP网络地址
     * @param fileUrl
     * @return
     */
    @Override
    public KmlInfo parseKmz(String fileUrl) {
        fileUrl = "http://10.1.102.43:30080/api-file/file/show/?signature=e992823b336a416a4b4ea3c0c11d63840878b8c14a1fdd6b2b1e26c1ed899abd9cbcc2ebffca95026b6a5f6acd92f466b98305bc71bd709eabb8aa28f76f3056e50abb3443d2f7696f443cae0d0fcfb3&fileName=test.kmz";
        KmlInfo kmlInfo = new KmlInfo();
        KmlInfo wpmlInfo = new KmlInfo();
        if (fileUrl == null || fileUrl.trim().isEmpty()) {
            return kmlInfo;
        }

        HttpURLConnection connection = null;
        try {
            // 1. 建立网络连接并配置
            URL url = new URL(fileUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(10000); // 10秒连接超时
            connection.setReadTimeout(30000);  // 30秒读取超时

            // 2. 检查响应状态
            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new IOException("文件下载失败，响应码: " + responseCode + "，信息: " + connection.getResponseMessage());
            }

            // 3. 获取输入流并解压解析（使用try-with-resources管理流）
            try (InputStream inputStream = new BufferedInputStream(connection.getInputStream());
                 ZipInputStream zipInputStream = new ZipInputStream(inputStream)) {

                ZipEntry entry;
                // 4. 遍历ZIP条目
                while ((entry = zipInputStream.getNextEntry()) != null) {
                    String name = entry.getName().toLowerCase();
                    if (name.endsWith(".kml")) {
                        kmlInfo = ParseFileUtils.parseKml(zipInputStream);
                    } else if (name.endsWith(".wpml")) {
                        wpmlInfo = ParseFileUtils.parseKml(zipInputStream);
                    }
                    zipInputStream.closeEntry(); // 关闭当前条目
                }

                // 5. 根据业务返回结果（示例：优先返回KML，无则返回WPML）
                return kmlInfo;
            }

        } catch (Exception e) {
            String errorMsg = "KMZ解析失败（URL: " + fileUrl + "）：" + e.getMessage();
            System.err.println(errorMsg);
            throw new RuntimeException(errorMsg, e); // 抛出异常让调用者处理
        } finally {
            if (connection != null) {
                connection.disconnect(); // 断开连接
            }
        }
    }

    /**
     * 解析kmz文件   本地文件
     * @param fileUrl
     * @return
     */
    @Override
    public KmlInfo parseKmzLocalFile(String fileUrl) {

        try {
            //File是用来操作本地文件系统路径的，不支持 HTTP URL
            File file = new File(fileUrl);
            ZipFile zipFile = new ZipFile(file);

            ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(file));

            ArchiveInputStream archiveInputStream = new ZipArchiveInputStream(zipInputStream);
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

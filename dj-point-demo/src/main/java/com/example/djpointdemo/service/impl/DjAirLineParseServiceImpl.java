package com.example.djpointdemo.service.impl;

import cn.hutool.core.io.FileUtil;
import com.example.djpointdemo.dao.kml.KmlInfo;
import com.example.djpointdemo.dao.kml.KmlParams;
import com.example.djpointdemo.dto.CredentialsInfo;
import com.example.djpointdemo.service.DjAirLineParseService;
import com.example.djpointdemo.util.AliYunOSSUploader;
import com.example.djpointdemo.util.ParseFileUtils;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.springframework.stereotype.Service;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.zip.ZipEntry;
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
        fileUrl = getNewFileUrl("http://10.1.102.43:30080/api-file/file/show/?signature=669fb7914fd5868dbbce9c9db48b1edcdb7d96e67ea81631cf85457c94ea5c03aceb2173f4e7150288fe23fd39e336d1614d9641bfa78f101b1b69c642faed1e067f9480af21b07f18e576829595f91d&fileName=巡检航点航线.kmz");
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

        //File是用来操作本地文件系统路径的，不支持 HTTP URL
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
            return wpmlInfo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 含中文参数，重新编码URL中的中文参数
     * @param fileUrl
     * @return
     */
    public String getNewFileUrl(String fileUrl) {
        // 1. 检查并编码URL中的中文参数（关键修复）
        try {
            URL originalUrl = new URL(fileUrl);
            String query = originalUrl.getQuery();
            if (query != null && !query.isEmpty()) {
                // 分割参数并编码中文值
                String[] params = query.split("&");
                StringBuilder encodedQuery = new StringBuilder();
                for (String param : params) {
                    String[] keyValue = param.split("=", 2);
                    if (keyValue.length == 2) {
                        // 对参数值进行UTF-8编码
                        String encodedValue = URLEncoder.encode(keyValue[1], StandardCharsets.UTF_8.name());
                        encodedQuery.append(keyValue[0]).append("=").append(encodedValue).append("&");
                    } else {
                        encodedQuery.append(param).append("&");
                    }
                }
                // 重建编码后的URL
                if (encodedQuery.length() > 0) {
                    encodedQuery.setLength(encodedQuery.length() - 1); // 移除最后一个&
                    String encodedUrl = originalUrl.getProtocol() + "://" +
                            originalUrl.getHost() +
                            (originalUrl.getPort() != -1 ? ":" + originalUrl.getPort() : "") +
                            originalUrl.getPath() + "?" + encodedQuery;
                    fileUrl = encodedUrl;
                }
            }
        } catch (Exception e) {
            System.err.println("URL编码处理失败: " + e.getMessage());
        }
        return  fileUrl;
    }

    /**
     * 生成kmz文件（带航点）
     * @param kmlParams 航线文件参数
     * @return
     */
    @Override
    public String createKmz(KmlParams kmlParams) {
        String fileName = System.currentTimeMillis()+"";
        //生成航线 KMZ 文件
        String createKmzUrl = ParseFileUtils.buildKmz(fileName, kmlParams);

        //阿里云oss上传航线文件到司空sync存储桶
        String securityToken = "CAISigV1q6Ft5B2yfSjIr5mACo6FjpF72vHYZ0PLj0UeQf4fqvLsujz2IH9JdHNgB+AasPozlGxT7Poblrt+UMQcHQnKbM99q49L9hmobIeZIwZhDFtf2vOfAmG2J0PRTKWwCryL+Lq/F96pb1fb7FgRpZLxaTSlWXG8LJSNkuQJR98LXw6+H1UkadBNPVkg0hZzVx3rOO2qLwThj0fJEUNsoXAcs25k7rmlycDuFnO8dTTzwfRHoJ/qcNr2LZtsLtJxUtO4mfdqcbDI3yMV8wJN+OAqhfJJpDuX4ZTDXQEOpknWY+GRooI3IUp1a/NrEKIa8vOtnKE/5bfYnd2vwB8INOpbWWGDTtj7hZqURb/sOIdoK7r2YHnAidrWccb56Fl9MS9KcVpEdYVkdnJ7WVkmFTiGb6H39V2WOw6oFfbdgeZ0o2EcrTjB+d6NOCLOIdGw2joZPZkRdl4hMwVss0Xqbq4BdX41ETRgHK3HaYRocDdUq67joRaoDE8G9HxMuODkbP77o70WbZ6FPqhLyo0Afp9LwRVIM138UOCpkVxGNj4nE7NHyKnqJZikkvznot6Pe+/ABdQGuVggEzfKtHi1U35eLD2Dn79EaVOe9NbRy7GLqcs5TUxsxKgGXV7cIYo18ww/uPfrtErPyoK5DCD2pFhf08LD4oxC5XFjevqZmOKItlbcxifPPPljyJmHBDMxHUTrICYlmernnW4c4hAT1m+1Pxta8EiWz2+/cYgDyPuHg2lDBqpDRVNttIIFPw6KYPD+ANZjMYsKFIgm2WakWZSWkPauKZHkDmltZYung3rONtpALccBSCnog+/OJvpNovQZZGSlQoUXx+UTuV/5llpE0a1nu4TaWSIwbBqAATPtTo21HRttkFudEyccSxYQBtl2SfjwlsD5Byr+w3s4zjOFHjlRp7lD5tfjRdb8kjVobyMotsQtUsaF+KqnU8Sy36aADSe8Tb5an0t30IaAs2AvTh2MBs6Q/Yi8/mmiZVaUcjiOn2vhUXTWjgK0AnEHEWaoRHA/fyHmH40uzrH9IAA=";

        CredentialsInfo credentialsInfo = new CredentialsInfo();
        credentialsInfo.setEndpoint("https://oss-cn-hangzhou.aliyuncs.com");
        credentialsInfo.setBucket("hz-file-storage-prod");
        credentialsInfo.setObjectKeyPrefix("4a6e5d87-6607-488c-870e-de39cd66c9a4/73cb10ca-acfb-4b03-9854-f996de3ee3b1/7d321859-1ff7-4ec8-b7a4-f8ee41ac6609");
        credentialsInfo.setAccessKeyId("STS.NZ5A41cNZm33eezkEKMq3E7GX");
        credentialsInfo.setAccessKeySecret("GkjuvstT5Bc4wiL1HEgpKeP7fTEcaBYh12UVtUgBD4XE");
        credentialsInfo.setExpire(43200L);
        credentialsInfo.setSecurityToken(securityToken);
        AliYunOSSUploader.uploadFileToDji(createKmzUrl, credentialsInfo);

        File file = new File(createKmzUrl);
        file.deleteOnExit();
        return createKmzUrl;
    }
}

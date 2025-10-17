package com.example.djpointdemo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.ClientException;
import com.example.djpointdemo.dao.kml.*;
import com.example.djpointdemo.dao.model.KmlParamsReq;
import com.example.djpointdemo.dto.AirLineTemporaryRequestJson;
import com.example.djpointdemo.dto.CredentialsInfo;
import com.example.djpointdemo.dto.Gps;
import com.example.djpointdemo.service.DjAirLineParseService;
import com.example.djpointdemo.util.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @author qzz  你把 HTTP 网络地址直接当成本地文件路径传给 File 类了 ，File 是用来操作本地文件系统路径的，不支持 HTTP URL 。
 * @date 2025/8/12
 */
@Slf4j
@Service
public class DjAirLineParseServiceImpl implements DjAirLineParseService {

    /**
     * 解析kmz文件   HTTP网络地址
     * @param fileUrl
     * @param type 0:kmlInfo返回 1:wpmlInfo返回
     * @return
     */
    @Override
    public KmlInfo parseKmz(String fileUrl, Short type) {
        fileUrl = "https://file-storage.djicdn.com/4a6e5d87-6607-488c-870e-de39cd66c9a4/73cb10ca-acfb-4b03-9854-f996de3ee3b1/d7b0a755-b1b8-4817-b065-195086c2f587/%E6%96%B0%E5%BB%BA%E8%88%AA%E7%82%B9%E8%88%AA%E7%BA%BF%281%29.kmz?auth_key=1758844800-0-0-d5151d259290628dd9b4f119ecc8d1be&response-content-disposition=attachment;filename=\"新建航点航线(1).kmz\";filename*=utf-8''%25E6%2596%25B0%25E5%25BB%25BA%25E8%2588%25AA%25E7%2582%25B9%25E8%2588%25AA%25E7%25BA%25BF%25281%2529.kmz";
        //如果fileUrl链接地址含中文，则需求重新编码URL中的中文参数
        fileUrl = getNewFileUrl(fileUrl);
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
                return type.equals(Short.valueOf("0")) ? kmlInfo : wpmlInfo;
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

        try {
            CredentialsInfo credentialsInfo = new CredentialsInfo();
            credentialsInfo.setEndpoint("https://oss-cn-hangzhou.aliyuncs.com");
            credentialsInfo.setBucket("hz-file-storage-prod");
            credentialsInfo.setObjectKeyPrefix("4a6e5d87-6607-488c-870e-de39cd66c9a4/73cb10ca-acfb-4b03-9854-f996de3ee3b1/7d321859-1ff7-4ec8-b7a4-f8ee41ac6609");
            credentialsInfo.setAccessKeyId("STS.NZ5A41cNZm33eezkEKMq3E7GX");
            credentialsInfo.setAccessKeySecret("GkjuvstT5Bc4wiL1HEgpKeP7fTEcaBYh12UVtUgBD4XE");
            credentialsInfo.setExpire(43200L);
            credentialsInfo.setSecurityToken(securityToken);
            AliYunOSSUploader.uploadFileToDji(createKmzUrl, credentialsInfo);
        }catch (Exception e){
             e.printStackTrace();
        }finally {
            if (StringUtils.isNotEmpty(createKmzUrl)) {
                //删除 createKmzUrl 临时文件
                File file = new File(createKmzUrl);
                if (file.exists() && !file.delete()) {
                    file.deleteOnExit();
                }
            }
        }
        return createKmzUrl;
    }

    /**
     * 新建临时航线文件，并同步到大疆
     * @param requestJson
     * @return
     */
    @Override
    public String createTemporaryKmzUploadDj(AirLineTemporaryRequestJson requestJson) {
        if(requestJson.getProjectUuid() == null){
            throw new ClientException("项目UUID不能为空");
        }
        if(CollUtil.isEmpty(requestJson.getGpsList())){
            throw new ClientException("航线坐标不能为空");
        }

        String projectUuid = requestJson.getProjectUuid();

        //获取大疆坐标列表（gcj02到wgs84转换）
        List<KmlPoint> kmlPointList = getKmlPointByGpsList(requestJson.getGpsList());

        //根据项目uuid获取原始航线文件信息---- 用于构建新的航线文件， 替换航点坐标信息
        String downloadUrl = "";

        //解析获取kml文件内容
        KmlInfo kmlInfo = parseKmz(downloadUrl, Short.valueOf("0"));
        log.info("handleTemporaryKmzKmlParams kmlInfo:{}", JSONObject.toJSONString(kmlInfo));
        //重置临时航线含航点参数数据
        KmlParams kmlParams = handleTemporaryKmzKmlParams(kmlInfo, kmlPointList);
        log.info("handleTemporaryKmzKmlParams kmlParams:{}", JSONObject.toJSONString(kmlParams));
        //上传航线文件到大疆司空2
        String name = System.currentTimeMillis() + "-临时航线";
        kmlParams.setTemporaryFlag(Boolean.TRUE);

        return handleAirLineUploadDj(projectUuid, name, kmlParams);
    }

    /**
     * 获取大疆坐标列表（gcj02到wgs84转换）
     * @param gpsList
     * @return
     */
    public List<KmlPoint> getKmlPointByGpsList(List<Gps> gpsList){
        List<KmlPoint> kmlPointList = new ArrayList<>();
        for (Gps gps : gpsList){
            KmlPoint point = new KmlPoint();
            //gcj02到wgs84转换
            Gps gps2 = GpsTransfer.gcj02_To_Wgs84_exact(gps.getLat(), gps.getLon());
            point.setCoordinates(gps2.getLon() + "," + gps2.getLat());
            kmlPointList.add(point);
        }
        return kmlPointList;
    }

    /**
     * 重置临时航线含航点参数数据
     * @param kmlInfo
     * @param kmlPointList
     * @return
     */
    public KmlParams handleTemporaryKmzKmlParams(KmlInfo kmlInfo, List<KmlPoint> kmlPointList){
        KmlParams kmlParams = new KmlParams();
        KmlDocument kmlDocument = kmlInfo.getDocument();
        //1.设置任务信息
        kmlParams.setKmlMissionConfig(kmlDocument.getKmlMissionConfig());
        //2.设置航点飞行模板信息
        List<KmlFolder> folderList = kmlDocument.getFolder();
        for (KmlFolder kmlFolder : folderList) {
            List<KmlPlacemark> placemarkList = new ArrayList<>();
            KmlPlacemark oldKmlPlacemark = kmlFolder.getPlacemarkList().get(0);
            KmlActionGroup actionGroup = oldKmlPlacemark.getActionGroup().get(0);

            kmlFolder.setWaylineId("0");
            kmlFolder.setExecuteHeightMode("WGS84");
            kmlFolder.setDuration("1800");
            kmlFolder.setDistance("0");
            //最后一个坐标索引
            Integer laseIndex = kmlPointList.size() - 1;
            //重置航线的航点坐标信息
            for (int i = 0;i < kmlPointList.size(); i++){
                String index = String.valueOf(i);
                KmlPlacemark kmlPlacemark = new KmlPlacemark();
                VoUtils.copyProperties(oldKmlPlacemark, kmlPlacemark);
                //重置下列参数
                kmlPlacemark.setIndex(index);
                //1.设置航点经纬度<经度,纬度>
                kmlPlacemark.setKmlPoint(kmlPointList.get(i));

                //2.最后一个坐标添加 悬停动态
                if(Integer.valueOf(i).equals(laseIndex)){
                    //设置航线初始动作
                    KmlActionGroup kmlActionGroup = new KmlActionGroup();
                    kmlActionGroup.setActionGroupId(index);
                    kmlActionGroup.setActionGroupStartIndex(index);
                    kmlActionGroup.setActionGroupEndIndex(index);
                    kmlActionGroup.setActionGroupMode(actionGroup.getActionGroupMode());
                    //动作组触发器
                    KmlActionTrigger actionTrigger = new KmlActionTrigger();
                    actionTrigger.setActionTriggerType(actionGroup.getActionTrigger().getActionTriggerType());
                    kmlActionGroup.setActionTrigger(actionTrigger);
                    //动作列表
                    List<KmlAction> kmlActionList = new ArrayList<>();
                    KmlAction kmlAction = new KmlAction();
                    kmlAction.setActionId(index);
                    //动作类型：hover 悬停
                    kmlAction.setActionActuatorFunc("hover");
                    KmlActionActuatorFuncParam actionActuatorFuncParam = new KmlActionActuatorFuncParam();
                    //悬停时间，单位s
                    actionActuatorFuncParam.setHoverTime("1800");
                    kmlAction.setActionActuatorFuncParam(actionActuatorFuncParam);
                    kmlActionList.add(kmlAction);
                    kmlActionGroup.setAction(kmlActionList);
                    kmlPlacemark.setActionGroup(Collections.singletonList(kmlActionGroup));
                }
                //3 航点执行高度
                kmlPlacemark.setExecuteHeight(kmlPlacemark.getEllipsoidHeight());
                KmlWaypointGimbalHeadingParam kmlWaypointGimbalHeadingParam = new KmlWaypointGimbalHeadingParam();
                kmlWaypointGimbalHeadingParam.setWaypointGimbalPitchAngle("0");
                kmlWaypointGimbalHeadingParam.setWaypointGimbalYawAngle("0");
                kmlPlacemark.setWaypointGimbalHeadingParam(kmlWaypointGimbalHeadingParam);
                kmlPlacemark.setWaypointWorkType("0");
                placemarkList.add(kmlPlacemark);
            }
            kmlFolder.setPlacemarkList(placemarkList);
        }
        kmlParams.setFolder(folderList);
        return kmlParams;
    }

    /**
     * 上传航线文件到大疆司空2
     * @param projectUuid 项目uuid
     * @param name 航线名称
     * @param kmlParams 航线参数
     */
    private String handleAirLineUploadDj(String projectUuid, String name, KmlParams kmlParams) {
        String createKmzUrl = null;
        try {
            String fileName = System.currentTimeMillis() + "";
            //1.生成航线 KMZ 文件
            createKmzUrl = ParseFileUtils.buildKmz(fileName, kmlParams);

            //2.获取项目的存储上传凭证接口 获取司空2储存桶的上传凭证
            CredentialsInfo credentialsInfo = syncProjectStsToken(projectUuid);

            if (credentialsInfo == null) {
                //项目的存储上传凭证 为空
                throw new ClientException("获取项目的存储上传凭证为空");
            }

            //3.上传文件到司空2储存桶
            String objectKey = AliYunOSSUploader.uploadFileToDji(createKmzUrl, credentialsInfo);
            log.info("上传文件 到司空2存储桶,返回objectKey:{}", objectKey);

            //4.调用 大疆航线上传完成通知接口
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (StringUtils.isNotEmpty(createKmzUrl)) {
                //删除 createKmzUrl 临时文件
                File file = new File(createKmzUrl);
                if (file.exists() && !file.delete()) {
                    file.deleteOnExit();
                }
            }
        }
    }

    private CredentialsInfo syncProjectStsToken(String projectUuid) {
        String securityToken = "CAISigV1q6Ft5B2yfSjIr5mACo6FjpF72vHYZ0PLj0UeQf4fqvLsujz2IH9JdHNgB+AasPozlGxT7Poblrt+UMQcHQnKbM99q49L9hmobIeZIwZhDFtf2vOfAmG2J0PRTKWwCryL+Lq/F96pb1fb7FgRpZLxaTSlWXG8LJSNkuQJR98LXw6+H1UkadBNPVkg0hZzVx3rOO2qLwThj0fJEUNsoXAcs25k7rmlycDuFnO8dTTzwfRHoJ/qcNr2LZtsLtJxUtO4mfdqcbDI3yMV8wJN+OAqhfJJpDuX4ZTDXQEOpknWY+GRooI3IUp1a/NrEKIa8vOtnKE/5bfYnd2vwB8INOpbWWGDTtj7hZqURb/sOIdoK7r2YHnAidrWccb56Fl9MS9KcVpEdYVkdnJ7WVkmFTiGb6H39V2WOw6oFfbdgeZ0o2EcrTjB+d6NOCLOIdGw2joZPZkRdl4hMwVss0Xqbq4BdX41ETRgHK3HaYRocDdUq67joRaoDE8G9HxMuODkbP77o70WbZ6FPqhLyo0Afp9LwRVIM138UOCpkVxGNj4nE7NHyKnqJZikkvznot6Pe+/ABdQGuVggEzfKtHi1U35eLD2Dn79EaVOe9NbRy7GLqcs5TUxsxKgGXV7cIYo18ww/uPfrtErPyoK5DCD2pFhf08LD4oxC5XFjevqZmOKItlbcxifPPPljyJmHBDMxHUTrICYlmernnW4c4hAT1m+1Pxta8EiWz2+/cYgDyPuHg2lDBqpDRVNttIIFPw6KYPD+ANZjMYsKFIgm2WakWZSWkPauKZHkDmltZYung3rONtpALccBSCnog+/OJvpNovQZZGSlQoUXx+UTuV/5llpE0a1nu4TaWSIwbBqAATPtTo21HRttkFudEyccSxYQBtl2SfjwlsD5Byr+w3s4zjOFHjlRp7lD5tfjRdb8kjVobyMotsQtUsaF+KqnU8Sy36aADSe8Tb5an0t30IaAs2AvTh2MBs6Q/Yi8/mmiZVaUcjiOn2vhUXTWjgK0AnEHEWaoRHA/fyHmH40uzrH9IAA=";
        CredentialsInfo credentialsInfo = new CredentialsInfo();
        credentialsInfo.setEndpoint("https://oss-cn-hangzhou.aliyuncs.com");
        credentialsInfo.setBucket("hz-file-storage-prod");
        credentialsInfo.setObjectKeyPrefix("4a6e5d87-6607-488c-870e-de39cd66c9a4/73cb10ca-acfb-4b03-9854-f996de3ee3b1/7d321859-1ff7-4ec8-b7a4-f8ee41ac6609");
        credentialsInfo.setAccessKeyId("STS.NZ5A41cNZm33eezkEKMq3E7GX");
        credentialsInfo.setAccessKeySecret("GkjuvstT5Bc4wiL1HEgpKeP7fTEcaBYh12UVtUgBD4XE");
        credentialsInfo.setExpire(43200L);
        credentialsInfo.setSecurityToken(securityToken);
        return credentialsInfo;
    }

    /**
     * 通用生成kmz文件
     * @param kmlParamsReq
     * @return
     */
    @Override
    public String buildCommonKmz(KmlParamsReq kmlParamsReq) {
        String fileName = System.currentTimeMillis() + "";
        //1.生成航线 KMZ 文件
        String url = BuildFileUtils.buildKmz(fileName, kmlParamsReq);
        return url;
    }
}

package com.example.djpointdemo.util;

import cn.hutool.core.date.DateUtil;
import com.example.djpointdemo.common.FileTypeConstants;
import com.example.djpointdemo.dao.kml.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.apache.commons.lang3.StringUtils;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 航线文件操作工具类
 * @author qzz
 * @date 2025/8/12
 */
public class ParseFileUtils {

    /**
     * XML 头部
     */
    private static final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";

    /**
     * 生成的本地 kmz 文件存储路径
     */
    private static final String LOCAL_KMZ_FILE_PATH = "file" + File.separator + "kmz" + File.separator;

    /**
     * kml文件解析
     *
     * @param inputStream
     * @return KmlInfo
     */
    public static KmlInfo parseKml(InputStream inputStream) {
        XStream xStream = new XStream();
        xStream.allowTypes(new Class[]{KmlInfo.class, KmlWayLineCoordinateSysParam.class, KmlPoint.class});
        xStream.alias("kml", KmlInfo.class);
        xStream.processAnnotations(KmlInfo.class);
        xStream.autodetectAnnotations(true);
        xStream.ignoreUnknownElements();
//        xStream.addImplicitCollection(KmlActionGroup.class, "action");
        return (KmlInfo) xStream.fromXML(inputStream);
    }

    /**
     * 生成航线 KMZ 文件
     *
     * @param fileName  文件名
     * @param kmlParams 参数对象
     * @return 本地文件路径
     */
    public static String buildKmz(String fileName, KmlParams kmlParams) {
        //深度拷贝文件
        KmlParams templateParams = getTemplateParams(kmlParams);
        KmlInfo kmlInfo = buildKml(templateParams);
        KmlInfo wpmlInfo = buildWpml(getWayLinesParams(kmlParams));
        return buildKmz(fileName, kmlInfo, wpmlInfo);
    }

    /**
     * 组装template.kml所需参数
     * @param kmlParams
     * @return
     */
    public static KmlParams getTemplateParams(KmlParams kmlParams){
        // 创建模板参数并进行深度拷贝，避免影响原始对象
        KmlParams templateParams = new KmlParams();

        List<KmlFolder> originalFolders = kmlParams.getFolder();
        List<KmlFolder> copiedFolders = new ArrayList<>();
        for (KmlFolder originalFolder : originalFolders) {
            // 拷贝文件夹本身
            KmlFolder copiedFolder = new KmlFolder();
            VoUtils.copyProperties(originalFolder, copiedFolder);
            copiedFolder.setExecuteHeightMode(null);
            copiedFolder.setWaylineId(null);

            // 深度拷贝地标列表
            List<KmlPlacemark> originalPlacemarks = originalFolder.getPlacemarkList();
            if (originalPlacemarks != null) {
                List<KmlPlacemark> copiedPlacemarks = new ArrayList<>();
                for (KmlPlacemark originalPlacemark : originalPlacemarks) {
                    KmlPlacemark copiedPlacemark = new KmlPlacemark();
                    VoUtils.copyProperties(originalPlacemark, copiedPlacemark);
                    // 只在拷贝对象上修改属性
                    copiedPlacemark.setExecuteHeight(null);
                    copiedPlacemarks.add(copiedPlacemark);
                }
                copiedFolder.setPlacemarkList(copiedPlacemarks);
            }
            copiedFolders.add(copiedFolder);
        }
        templateParams.setFolder(copiedFolders);

        // 深度拷贝任务配置
        KmlMissionConfig originalMissionConfig = kmlParams.getKmlMissionConfig();
        if (originalMissionConfig != null) {
            KmlMissionConfig copiedMissionConfig = new KmlMissionConfig();
            // 逐个复制属性而非使用引用
            VoUtils.copyProperties(originalMissionConfig, copiedMissionConfig);
            templateParams.setKmlMissionConfig(copiedMissionConfig);
        }
        return templateParams;
    }

    /**
     * 组装waylines.wpml所需参数
     * @param kmlParams
     * @return
     */
    public static KmlParams getWayLinesParams(KmlParams kmlParams){
        kmlParams.getKmlMissionConfig().setTakeOffRefPoint(null);
        kmlParams.getKmlMissionConfig().setTakeOffRefPointAGLHeight(null);

        for (KmlFolder folder : kmlParams.getFolder()) {
            folder.setTemplateType(null);
            folder.setWaylineCoordinateSysParam(null);
            folder.setGlobalWaypointTurnMode(null);
            folder.setGlobalUseStraightLine(null);
            folder.setGimbalPitchMode(null);
            folder.setGlobalHeight(null);
            folder.getPlacemarkList().forEach(f ->{
                f.setUseGlobalHeight(null);
                f.setEllipsoidHeight(null);
                f.setHeight(null);
                f.setUseGlobalSpeed(null);
                f.setUseGlobalTurnParam(null);
            });
        }
        return kmlParams;
    }

    public static KmlInfo buildKml(KmlParams kmlParams) {
        KmlInfo kmlInfo = new KmlInfo();
        kmlInfo.setDocument(buildKmlDocument(FileTypeConstants.KML, kmlParams));
        return kmlInfo;
    }

    public static KmlInfo buildWpml(KmlParams kmlParams) {
        KmlInfo kmlInfo = new KmlInfo();
        kmlInfo.setDocument(buildKmlDocument(FileTypeConstants.WPML, kmlParams));
        return kmlInfo;
    }

    public static KmlDocument buildKmlDocument(String fileType, KmlParams kmlParams) {
        KmlDocument kmlDocument = new KmlDocument();
        if (StringUtils.equals(fileType, FileTypeConstants.KML)) {
            kmlDocument.setAuthor("Cleaner");
            kmlDocument.setCreateTime(String.valueOf(DateUtil.current()));
            kmlDocument.setUpdateTime(String.valueOf(DateUtil.current()));
        }
        kmlDocument.setKmlMissionConfig(kmlParams.getKmlMissionConfig());
        kmlDocument.setFolder(kmlParams.getFolder());
        return kmlDocument;
    }

    /**
     * 生成航线 KMZ 文件
     *
     * @param fileName 文件名
     * @param kmlInfo  kml 文件信息
     * @param wpmlInfo wpml 文件信息
     * @return 本地文件路径
     */
    public static String buildKmz(String fileName, KmlInfo kmlInfo, KmlInfo wpmlInfo) {
        XStream xStream = new XStream(new DomDriver());
        xStream.processAnnotations(KmlInfo.class);
//        xStream.addImplicitCollection(KmlActionGroup.class, "action");

        String kml = XML_HEADER + xStream.toXML(kmlInfo);
        String wpml = XML_HEADER + xStream.toXML(wpmlInfo);

        String destFilePath = LOCAL_KMZ_FILE_PATH + fileName + ".kmz";
        File file = new File(destFilePath);
        File parentFile = file.getParentFile();
        if (parentFile != null && !parentFile.exists()) {
            parentFile.mkdirs();
        }

        try (FileOutputStream fileOutputStream = new FileOutputStream(destFilePath);
             ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream)) {
            zipOutputStream.setLevel(0); // 0 表示不压缩，存储方式

            // 创建 wpmz 目录中的 template.kml 文件条目
            buildZipFile("wpmz/template.kml", zipOutputStream, kml);

            // 创建 wpmz 目录中的 waylines.wpml 文件条目
            buildZipFile("wpmz/waylines.wpml", zipOutputStream, wpml);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return LOCAL_KMZ_FILE_PATH + fileName + ".kmz";
    }

    private static void buildZipFile(String name, ZipOutputStream zipOutputStream, String content) throws IOException {
        ZipEntry kmlEntry = new ZipEntry(name);
        zipOutputStream.putNextEntry(kmlEntry);
        // 将内容写入 ZIP 条目
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8))) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) >= 0) {
                zipOutputStream.write(buffer, 0, length);
            }
        }
        zipOutputStream.closeEntry(); // 关闭条目
    }
}

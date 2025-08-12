package com.example.djpointdemo.util;

import com.example.djpointdemo.dao.kml.KmlInfo;
import com.example.djpointdemo.dao.kml.KmlPoint;
import com.example.djpointdemo.dao.kml.KmlWayLineCoordinateSysParam;
import com.thoughtworks.xstream.XStream;

import java.io.InputStream;

/**
 * 航线文件操作工具类
 * @author qzz
 * @date 2025/8/12
 */
public class ParseFileUtils {

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
}

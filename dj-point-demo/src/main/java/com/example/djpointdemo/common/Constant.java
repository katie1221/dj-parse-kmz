package com.example.djpointdemo.common;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author qzz
 * @date 2025/10/17
 */
public class Constant {

    /**
     * 机型 domain-type-sub_type组成
     */
    public static Map<String, String> deviceModelKeyMap = new ConcurrentHashMap<>();

    static {
        deviceModelKeyMap.put("0-67-0", "经纬 M30");
        deviceModelKeyMap.put("0-67-1", "经纬 M30 T");
        deviceModelKeyMap.put("0-77-0", "Mavic 3E");
        deviceModelKeyMap.put("0-77-1", "Mavic 3T");
        deviceModelKeyMap.put("0-77-3", "Mavic 3TA");
        deviceModelKeyMap.put("0-91-0", "Matrice 3D");
        deviceModelKeyMap.put("0-91-1", "Matrice 3TD");
        deviceModelKeyMap.put("0-99-0", "Matrice 4E");
        deviceModelKeyMap.put("0-99-1", "Matrice 4T");
        deviceModelKeyMap.put("0-100-0", "Matrice 4D");
        deviceModelKeyMap.put("0-100-1", "Matrice 4TD");
        deviceModelKeyMap.put("0-103-0", "Matrice 400");
    }

    /**
     * 负载信息 payloadEnumValue、payloadSubEnumValue、payloadPositionIndex
     */
    public static Map<String, String> payloadInfoMap = new ConcurrentHashMap<>();

    static {
        payloadInfoMap.put("82-0-0", "H30");
        payloadInfoMap.put("83-2-0", "H30T");
        payloadInfoMap.put("50-0-0", "P1-24mm");
        payloadInfoMap.put("50-1-0", "P1-35mm");
        payloadInfoMap.put("50-2-0", "P1-50mm");
        payloadInfoMap.put("84-1-0", "L2");
        payloadInfoMap.put("65533-0-0", "S1 探照灯");
        payloadInfoMap.put("65532-0-0", "V1 喊话灯");
    }
}

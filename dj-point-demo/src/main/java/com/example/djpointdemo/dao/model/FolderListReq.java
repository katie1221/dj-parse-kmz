package com.example.djpointdemo.dao.model;

import lombok.Data;

import java.util.List;

/**
 * @author qzz
 * @date 2025/10/17
 */
@Data
public class FolderListReq {

    /**
     * 航线初始动作列表
     */
    private List<PointActionReq> startActionList;

    private String wayLineId;

    /**
     * 航点列表
     */
    private List<RoutePointReq> routePointList;
}

package com.stylefeng.guns.api.cinema.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chenzhibin
 * @time 2020/4/23 17:03
 */
@Data
public class AreaVO implements Serializable {
    private static final long serialVersionUID = -9004262192475982433L;
    /**
     * 行政区域id
     */
    private String areaId;
    /**
     * 影院品牌名称
     */
    private String brandName;
    /**
     * 是否有效
     */
    private boolean isActive;
}

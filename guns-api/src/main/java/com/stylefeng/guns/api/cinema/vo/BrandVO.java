package com.stylefeng.guns.api.cinema.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chenzhibin
 * @time 2020/4/23 17:02
 */
@Data
public class BrandVO implements Serializable {
    private static final long serialVersionUID = -8025357458823134973L;
    /**
     * 影院编号,默认为99,全部
     */
    private String brandId;
    /**
     * 影院名称
     */
    private String brandName;
    /**
     * 是否有效
     */
    private boolean isActive;
}

package com.stylefeng.guns.api.cinema.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chenzhibin
 * @time 2020/4/23 16:57
 */
@Data
public class CinemaVO implements Serializable {
    private static final long serialVersionUID = -8338781523206942389L;
    /**
     * 影院id
     */
    private String uuid;
    /**
     * 影院名称
     */
    private String cinemaName;
    /**
     * 影院地址
     */
    private String address;
    /**
     * 最低价格
     */
    private String minimumPrice;
}

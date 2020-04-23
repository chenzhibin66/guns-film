package com.stylefeng.guns.api.cinema.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chenzhibin
 * @time 2020/4/23 17:09
 */
@Data
public class CinemaInfoVO implements Serializable {
    private static final long serialVersionUID = 6271097725337137857L;
    /**
     * 影院id
     */
    private String cinemaId;
    /**
     * 图片url
     */
    private String imgUrl;
    /**
     * 影院名称
     */
    private String cinemaName;
    /**
     * 影院地址
     */
    private String cinemaAddress;
    /**
     * 影院电话
     */
    private String cinemaPhone;
}

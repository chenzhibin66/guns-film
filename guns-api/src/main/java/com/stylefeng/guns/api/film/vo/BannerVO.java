package com.stylefeng.guns.api.film.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 广告VO
 *
 * @author chenzhibin
 * @time 2020/3/30 20:55
 */
@Data
public class BannerVO implements Serializable {
    private static final long serialVersionUID = -1428222505737842551L;
    /**
     * 广告id
     **/
    private String bannerId;
    /**
     * 广告地址
     **/
    private String bannerAddress;
    /**
     * 广告链接
     **/
    private String bannerUrl;


}

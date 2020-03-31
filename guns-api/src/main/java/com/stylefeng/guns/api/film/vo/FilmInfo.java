package com.stylefeng.guns.api.film.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 影片信息
 *
 * @author chenzhibin
 * @time 2020/3/30 22:53
 */
@Data
public class FilmInfo implements Serializable {
    private static final long serialVersionUID = 6688413827970489044L;
    /**
     * 电影id
     **/
    private String filmId;
    /**
     * 电影类型
     **/
    private int filmType;
    /**
     * 图片地址
     **/
    private String imgAddress;
    /**
     * 电影名称
     **/
    private String filmName;
    /**
     * 电影评分
     **/
    private String filmScore;
    /**
     * 推荐人数
     **/
    private int expectNum;
    /**
     * 上映时间
     **/
    private String showTime;
    /**
     * 票房
     **/
    private int boxNum;
    /**
     * 评分
     **/
    private String score;
}

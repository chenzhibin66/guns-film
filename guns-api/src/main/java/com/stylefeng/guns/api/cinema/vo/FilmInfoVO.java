package com.stylefeng.guns.api.cinema.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author chenzhibin
 * @time 2020/4/23 17:15
 */
@Data
public class FilmInfoVO implements Serializable {
    private static final long serialVersionUID = 2086811656589338201L;
    /**
     * 影片id
     */
    private String filmId;
    /**
     * 影片名称
     */
    private String filmName;
    /**
     * 影片时长
     */
    private String filmLength;
    /**
     * 影片类型
     */
    private String filmType;
    /**
     * 影片分类
     */
    private String filmCats;
    /**
     * 演员
     */
    private String actors;
    /**
     * 图片地址
     */
    private String imgAddress;
    /**
     * 影片集合
     */
    private List<FilmFieldVO> filmFields;
}

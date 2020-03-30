package com.stylefeng.guns.rest.modular.film.vo;

import lombok.Data;

import java.util.List;

/**
 * 电影 VO
 *
 * @author chenzhibin
 * @time 2020/3/30 22:52
 */
@Data
public class FilmVO {
    /**
     * 电影编号
     **/
    private Integer filmNum;
    /**
     * 电影信息列表
     **/
    private List<FilmInfo> filmInfos;
}

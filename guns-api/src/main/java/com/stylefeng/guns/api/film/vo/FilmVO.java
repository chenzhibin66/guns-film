package com.stylefeng.guns.api.film.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 电影 VO
 *
 * @author chenzhibin
 * @time 2020/3/30 22:52
 */
@Data
public class FilmVO implements Serializable {
    private static final long serialVersionUID = -2041524513847934394L;
    /**
     * 电影编号
     **/
    private Integer filmNum;
    /**
     * 电影信息列表
     **/
    private List<FilmInfo> filmInfos;
}

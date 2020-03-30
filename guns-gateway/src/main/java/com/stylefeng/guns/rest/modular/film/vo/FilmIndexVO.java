package com.stylefeng.guns.rest.modular.film.vo;

import lombok.Data;

import java.util.List;

/**
 * @author chenzhibin
 * @time 2020/3/30 20:55
 */
@Data
public class FilmIndexVO {
    /**
     * 广告列表
     **/
    private List<BannerVO> banners;
    /**
     * 热门电影
     **/
    private FilmVO hotFilms;
    /**
     * 即将上映
     **/
    private FilmVO soonFilms;
    /**
     * 票房排行榜列表
     **/
    private List<FilmInfo> boxRanking;
    /**
     * 最受欢迎排行列表
     **/
    private List<FilmInfo> expectRanking;
    /**
     * 热门前100
     **/
    private List<FilmInfo> top100;
}

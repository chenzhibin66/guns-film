package com.stylefeng.guns.api.film;

import com.stylefeng.guns.api.film.vo.BannerVO;
import com.stylefeng.guns.api.film.vo.FilmInfo;
import com.stylefeng.guns.api.film.vo.FilmVO;

import java.util.List;

/**
 * 影片服务API
 *
 * @author chenzhibin
 * @time 2020/3/31 9:52
 */
public interface FilmServiceAPI {
    /**
     * 功能描述: 获取广告列表
     *
     * @Param: []
     * @Return: com.stylefeng.guns.api.film.vo.BannerVO
     */
    BannerVO getBanners();

    /**
     * 功能描述: 获取热门电影
     *
     * @Param: [isLimit, nums]
     * @Return: com.stylefeng.guns.api.film.vo.FilmVO
     */
    FilmVO getHotFilms(boolean isLimit, int nums);

    /**
     * 功能描述: 票房排行榜
     *
     * @Param: []
     * @Return: java.util.List<com.stylefeng.guns.api.film.vo.FilmInfo>
     */
    List<FilmInfo> getBoxRanking();

    /**
     * 功能描述: 获取前100
     *
     * @Param:
     * @Return: java.util.List<com.stylefeng.guns.api.film.vo.FilmInfo>
     */
    List<FilmInfo> getTop();
}

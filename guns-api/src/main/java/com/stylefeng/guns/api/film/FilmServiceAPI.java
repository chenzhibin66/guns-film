package com.stylefeng.guns.api.film;

import com.stylefeng.guns.api.film.vo.*;

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
    List<BannerVO> getBanners();

    /**
     * 功能描述: 获取热门电影
     *
     * @Param: [isLimit, nums]
     * @Return: com.stylefeng.guns.api.film.vo.FilmVO
     */
    FilmVO getHotFilms(boolean isLimit, int nums, int nowPage, int sortId, int sourceId, int yearId, int catId);

    /**
     * 功能描述: 获取即将上映影片
     *
     * @Param: [isLimit, nums]
     * @Return: com.stylefeng.guns.api.film.vo.FilmVO
     */
    FilmVO getSoonFilms(boolean isLimit, int nums, int nowPage, int sortId, int sourceId, int yearId, int catId);

    /**
     * 功能描述: 获取经典影片
     *
     * @Param: [isLimit, nums, nowPage, sortId, sourceId, yearId, catId]
     * @Return: com.stylefeng.guns.api.film.vo.FilmVO
     */
    FilmVO getClassicFilms(int nums, int nowPage, int sortId, int sourceId, int yearId, int catId);

    /**
     * 功能描述: 票房排行榜
     *
     * @Param: []
     * @Return: java.util.List<com.stylefeng.guns.api.film.vo.FilmInfo>
     */
    List<FilmInfo> getBoxRanking();

    /**
     * 功能描述:期待上映排行榜
     *
     * @Param: []
     * @Return: java.util.List<com.stylefeng.guns.api.film.vo.FilmInfo>
     */
    List<FilmInfo> getExpectRanking();

    /**
     * 功能描述: 获取前100
     *
     * @Param:
     * @Return: java.util.List<com.stylefeng.guns.api.film.vo.FilmInfo>
     */
    List<FilmInfo> getTop();

    //获取影片条件接口

    /**
     * 功能描述: 分类条件
     *
     * @Param: []
     * @Return: java.util.List<com.stylefeng.guns.api.film.vo.CatVO>
     */
    List<CatVO> getCats();

    /**
     * 功能描述: 获取片源条件
     *
     * @Param: []
     * @Return: java.util.List<com.stylefeng.guns.api.film.vo.SourceVO>
     */
    List<SourceVO> getSources();

    /**
     * 功能描述: 获取年代条件
     *
     * @Param: []
     * @Return: java.util.List<com.stylefeng.guns.api.film.vo.YearVO>
     */
    List<YearVO> getYears();

    /**
     * 功能描述: 根据影片id或者名称获取影片信息
     *
     * @Param: [searchType, searchParam]
     * @Return: com.stylefeng.guns.api.film.vo.FilmDetailVO
     */
    FilmDetailVO getFilmDetail(int searchType, String searchParam);
}

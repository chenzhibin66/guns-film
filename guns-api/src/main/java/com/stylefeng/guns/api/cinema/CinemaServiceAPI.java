package com.stylefeng.guns.api.cinema;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.api.cinema.vo.*;

import java.util.List;

/**
 * 影院服务API
 *
 * @author chenzhibin
 * @time 2020/4/23 16:16
 */
public interface CinemaServiceAPI {
    /**
     * 功能描述: 查询影院列表
     *
     * @Param: [cinemaQueryVO]
     * @Return: com.baomidou.mybatisplus.plugins.Page<com.stylefeng.guns.api.cinema.vo.CinemaVO>
     */
    Page<CinemaVO> getCinemas(CinemaQueryVO cinemaQueryVO);

    /**
     * 功能描述:根据条件获取品牌列表,[除了99以外,其他的数字为isActive]
     *
     * @Param: [brandId]
     * @Return: java.util.List<com.stylefeng.guns.api.cinema.vo.BrandVO>
     */
    List<BrandVO> getBrands(int brandId);

    /**
     * 功能描述:获取行政区域列表
     *
     * @Param: [areaId]
     * @Return: java.util.List<com.stylefeng.guns.api.cinema.vo.AreaVO>
     */
    List<AreaVO> getAreas(int areaId);

    /**
     * 功能描述:获取影厅类型列表
     *
     * @Param: [hallType]
     * @Return: java.util.List<com.stylefeng.guns.api.cinema.vo.HallTypeVO>
     */
    List<HallTypeVO> getHallTypes(int hallType);

    /**
     * 功能描述: 根据影院编号获取影院信息
     *
     * @Param: [cinemaId]
     * @Return: com.stylefeng.guns.api.cinema.vo.CinemaInfoVO
     */
    CinemaInfoVO getCinemaInfoById(int cinemaId);

    /**
     * 功能描述:根据影院编号获取所有电影的信息和对应的放映场次信息
     *
     * @Param: [cinemaId]
     * @Return: com.stylefeng.guns.api.cinema.vo.FilmInfoVO
     */
    List<FilmInfoVO> getFilmInfoByCinemaId(int cinemaId);

    /**
     * 功能描述: 根据放映场次ID获取放映信息
     *
     * @Param: [fieldId]
     * @Return: com.stylefeng.guns.api.cinema.vo.FilmFieldVO
     */
    HallInfoVO getFilmFieldInfo(int fieldId);

    /**
     * 功能描述: 根据放映场次查询播放的电影编号,然后根据电影编号获取对应的电影信息
     *
     * @Param: [fieldId]
     * @Return: com.stylefeng.guns.api.cinema.vo.FilmInfoVO
     */
    FilmInfoVO getFilmInfoByFieldId(int fieldId);
}

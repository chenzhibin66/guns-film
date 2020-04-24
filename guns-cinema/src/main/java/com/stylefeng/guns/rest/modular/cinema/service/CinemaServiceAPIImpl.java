package com.stylefeng.guns.rest.modular.cinema.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.api.cinema.CinemaServiceAPI;
import com.stylefeng.guns.api.cinema.vo.*;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chenzhibin
 * @time 2020/4/24 9:47
 */
@Component
@Service(interfaceClass = CinemaServiceAPI.class)
public class CinemaServiceAPIImpl implements CinemaServiceAPI {

    /**
     * 功能描述: 查询影院列表
     *
     * @Param: [cinemaQueryVO]
     * @Return: com.baomidou.mybatisplus.plugins.Page<com.stylefeng.guns.api.cinema.vo.CinemaVO>
     */
    @Override
    public Page<CinemaVO> getCinemas(CinemaQueryVO cinemaQueryVO) {
        return null;
    }


    /**
     * 功能描述:根据条件获取品牌列表,[除了99以外,其他的数字为isActive]
     *
     * @Param: [brandId]
     * @Return: java.util.List<com.stylefeng.guns.api.cinema.vo.BrandVO>
     */
    @Override
    public List<BrandVO> getBrands(int brandId) {
        return null;
    }


    /**
     * 功能描述:获取行政区域列表
     *
     * @Param: [areaId]
     * @Return: java.util.List<com.stylefeng.guns.api.cinema.vo.AreaVo>
     */
    @Override
    public List<AreaVo> getAreas(int areaId) {
        return null;
    }

    /**
     * 功能描述:获取影厅类型列表
     *
     * @Param: [hallType]
     * @Return: java.util.List<com.stylefeng.guns.api.cinema.vo.HallTypeVO>
     */
    @Override
    public List<HallTypeVO> getHallTypes(int hallType) {
        return null;
    }


    /**
     * 功能描述: 根据影院编号获取影院信息
     *
     * @Param: [cinemaId]
     * @Return: com.stylefeng.guns.api.cinema.vo.CinemaInfoVO
     */
    @Override
    public CinemaInfoVO getCinemaInfoById(int cinemaId) {
        return null;
    }

    /**
     * 功能描述:根据影院编号获取所有电影的信息和对应的放映场次信息
     *
     * @Param: [cinemaId]
     * @Return: com.stylefeng.guns.api.cinema.vo.FilmInfoVO
     */
    @Override
    public FilmInfoVO getFilmInfoByCinemaId(int cinemaId) {
        return null;
    }

    /**
     * 功能描述: 根据放映场次ID获取放映信息
     *
     * @Param: [fieldId]
     * @Return: com.stylefeng.guns.api.cinema.vo.FilmFieldVO
     */
    @Override
    public FilmFieldVO getFilmFieldInfo(int fieldId) {
        return null;
    }

    /**
     * 功能描述: 根据放映场次查询播放的电影编号,然后根据电影编号获取对应的电影信息
     *
     * @Param: [fieldId]
     * @Return: com.stylefeng.guns.api.cinema.vo.FilmInfoVO
     */
    @Override
    public FilmInfoVO getFilmInfoByFieldId(int fieldId) {
        return null;
    }
}

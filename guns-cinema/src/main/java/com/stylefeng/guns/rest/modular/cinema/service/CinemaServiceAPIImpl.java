package com.stylefeng.guns.rest.modular.cinema.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.api.cinema.CinemaServiceAPI;
import com.stylefeng.guns.api.cinema.vo.*;
import com.stylefeng.guns.rest.constant.CinemaConstants;
import com.stylefeng.guns.rest.convert.CinemaConvert;
import com.stylefeng.guns.rest.entity.AreaDictDO;
import com.stylefeng.guns.rest.entity.BrandDictDO;
import com.stylefeng.guns.rest.entity.CinemaDO;
import com.stylefeng.guns.rest.entity.HallDictDO;
import com.stylefeng.guns.rest.modular.cinema.dao.*;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzhibin
 * @time 2020/4/24 9:47
 */
@Component
@Service(interfaceClass = CinemaServiceAPI.class)
public class CinemaServiceAPIImpl implements CinemaServiceAPI {

    @Resource
    private CinemaRepository cinemaRepository;
    @Resource
    private AreaDictRepository areaDictRepository;
    @Resource
    private BrandDictRepository brandDictRepository;
    @Resource
    private HallDictRepository hallDictRepository;
    @Resource
    private HallFilmInfoRepository hallFilmInfoRepository;
    @Resource
    private FieldRepository fieldRepository;

    /**
     * 功能描述: 查询影院列表
     *
     * @Param: [cinemaQueryVO]
     * @Return: com.baomidou.mybatisplus.plugins.Page<com.stylefeng.guns.api.cinema.vo.CinemaVO>
     */
    @Override
    public Page<CinemaVO> getCinemas(CinemaQueryVO cinemaQueryVO) {
        List<CinemaVO> cinemaVOS = new ArrayList<>();
        EntityWrapper<CinemaDO> entityWrapper = new EntityWrapper<>();
        //判断有没有传入查询条件,没有则为默认全部
        if (null == cinemaQueryVO) {
            cinemaQueryVO.setBrandId(99);
            cinemaQueryVO.setDistrictId(99);
            cinemaQueryVO.setHallType(99);
            cinemaQueryVO.setNowPage(1);
            cinemaQueryVO.setPageSize(12);
        }
        Page<CinemaDO> page = new Page<>(cinemaQueryVO.getNowPage(), cinemaQueryVO.getPageSize());

        entityWrapper.eq(CinemaConstants.BRAND_ID, cinemaQueryVO.getBrandId());
        entityWrapper.eq(CinemaConstants.AREA_ID, cinemaQueryVO.getDistrictId());
        entityWrapper.like(CinemaConstants.HALL_IDS, "%+" + cinemaQueryVO.getHallType() + "+%");
        List<CinemaDO> cinemas = cinemaRepository.selectPage(page, entityWrapper);
        cinemaVOS = CinemaConvert.convert(cinemas);

        long counts = cinemaRepository.selectCount(entityWrapper);
        Page<CinemaVO> result = new Page<>();
        result.setRecords(cinemaVOS);
        result.setSize(cinemaQueryVO.getPageSize());
        result.setTotal(counts);
        return result;
    }


    /**
     * 功能描述:根据条件获取品牌列表,[除了99以外,其他的数字为isActive]
     *
     * @Param: [brandId]
     * @Return: java.util.List<com.stylefeng.guns.api.cinema.vo.BrandVO>
     */
    @Override
    public List<BrandVO> getBrands(int brandId) {
        List<BrandVO> brandVOS = new ArrayList<>();
        boolean flag = false;
        //判断传入的id是否存在
        BrandDictDO brandDictDO = brandDictRepository.selectById(brandId);
        //判断id是否等于99
        if (brandId == CinemaConstants.ALL || brandDictDO == null || brandDictDO.getUuid() == null) {
            flag = true;
        }
        //查询所有列表
        List<BrandDictDO> brandDictDOS = brandDictRepository.selectList(null);

        for (BrandDictDO brandDict : brandDictDOS) {
            BrandVO brandVO = new BrandVO();
            brandVO.setBrandName(brandDict.getShowName());
            brandVO.setBrandId(brandDict.getUuid() + "");
            //如果flag为true,则需要99.如果为false,则匹配上的内容active
            if (flag) {
                if (brandDict.getUuid() == CinemaConstants.ALL) {
                    brandVO.setActive(true);
                }
            } else {
                if (brandDict.getUuid() == brandId) {
                    brandVO.setActive(true);
                }
            }
            brandVOS.add(brandVO);
        }
        return brandVOS;
    }


    /**
     * 功能描述:获取行政区域列表
     *
     * @Param: [areaId]
     * @Return: java.util.List<com.stylefeng.guns.api.cinema.vo.AreaVO>
     */
    @Override
    public List<AreaVO> getAreas(int areaId) {
        List<AreaVO> areaVOS = new ArrayList<>();
        boolean flag = false;
        //判断传入的id是否存在
        AreaDictDO areaDictDO = areaDictRepository.selectById(areaId);
        //判断id是否等于99
        if (areaId == CinemaConstants.ALL || areaDictDO == null || areaDictDO.getUuid() == null) {
            flag = true;
        }
        //查询所有列表
        List<AreaDictDO> areaDictDOS = areaDictRepository.selectList(null);

        for (AreaDictDO areaDict : areaDictDOS) {
            AreaVO areaVO = new AreaVO();
            areaVO.setBrandName(areaDict.getShowName());
            areaVO.setAreaId(areaDict.getUuid() + "");
            //如果flag为true,则需要99.如果为false,则匹配上的内容active
            if (flag) {
                if (areaDict.getUuid() == CinemaConstants.ALL) {
                    areaVO.setActive(true);
                }
            } else {
                if (areaDict.getUuid() == areaId) {
                    areaVO.setActive(true);
                }
            }
            areaVOS.add(areaVO);
        }
        return areaVOS;
    }


    /**
     * 功能描述:获取影厅类型列表
     *
     * @Param: [hallType]
     * @Return: java.util.List<com.stylefeng.guns.api.cinema.vo.HallTypeVO>
     */
    @Override
    public List<HallTypeVO> getHallTypes(int hallType) {
        List<HallTypeVO> hallTypeVOS = new ArrayList<>();
        boolean flag = false;
        //判断传入的id是否存在
        HallDictDO hallDictDO = hallDictRepository.selectById(hallType);
        //判断id是否等于99
        if (hallType == CinemaConstants.ALL || hallDictDO == null || hallDictDO.getUuid() == null) {
            flag = true;
        }
        //查询所有列表
        List<HallDictDO> hallDictDOS = hallDictRepository.selectList(null);

        for (HallDictDO hallDict : hallDictDOS) {
            HallTypeVO hallTypeVO = new HallTypeVO();
            hallTypeVO.setHallTypeName(hallDict.getShowName());
            hallTypeVO.setHallTypeId(hallDict.getUuid() + "");
            //如果flag为true,则需要99.如果为false,则匹配上的内容active
            if (flag) {
                if (hallDict.getUuid() == CinemaConstants.ALL) {
                    hallTypeVO.setActive(true);
                }
            } else {
                if (hallDict.getUuid() == hallType) {
                    hallTypeVO.setActive(true);
                }
            }
            hallTypeVOS.add(hallTypeVO);
        }
        return hallTypeVOS;
    }

    /**
     * 功能描述: 根据影院编号获取影院信息
     *
     * @Param: [cinemaId]
     * @Return: com.stylefeng.guns.api.cinema.vo.CinemaInfoVO
     */
    @Override
    public CinemaInfoVO getCinemaInfoById(int cinemaId) {
        CinemaDO cinemaDO = cinemaRepository.selectById(cinemaId);
        CinemaInfoVO cinemaInfoVO = CinemaConvert.convert(cinemaDO);
        return cinemaInfoVO;
    }

    /**
     * 功能描述:根据影院编号获取所有电影的信息和对应的放映场次信息
     *
     * @Param: [cinemaId]
     * @Return: com.stylefeng.guns.api.cinema.vo.FilmInfoVO
     */
    @Override
    public List<FilmInfoVO> getFilmInfoByCinemaId(int cinemaId) {
        List<FilmInfoVO> filmInfoVOS = fieldRepository.getFilmInfos(cinemaId);
        return filmInfoVOS;
    }

    /**
     * 功能描述: 根据放映场次ID获取放映信息
     *
     * @Param: [fieldId]
     * @Return: com.stylefeng.guns.api.cinema.vo.FilmFieldVO
     */
    @Override
    public HallInfoVO getFilmFieldInfo(int fieldId) {
        HallInfoVO hallInfoVO = fieldRepository.getHallInfo(fieldId);
        return hallInfoVO;
    }

    /**
     * 功能描述: 根据放映场次查询播放的电影编号,然后根据电影编号获取对应的电影信息
     *
     * @Param: [fieldId]
     * @Return: com.stylefeng.guns.api.cinema.vo.FilmInfoVO
     */
    @Override
    public FilmInfoVO getFilmInfoByFieldId(int fieldId) {
        FilmInfoVO filmInfoVO = fieldRepository.getFilmInfoById(fieldId);
        return filmInfoVO;
    }
}

package com.stylefeng.guns.rest.modular.film.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.api.film.FilmServiceAPI;
import com.stylefeng.guns.api.film.vo.BannerVO;
import com.stylefeng.guns.api.film.vo.FilmInfo;
import com.stylefeng.guns.api.film.vo.FilmVO;
import com.stylefeng.guns.rest.convert.FilmConvert;
import com.stylefeng.guns.rest.entity.BannerDO;
import com.stylefeng.guns.rest.entity.FilmDO;
import com.stylefeng.guns.rest.modular.film.dao.BannerRepository;
import com.stylefeng.guns.rest.modular.film.dao.FilmInfoRepository;
import com.stylefeng.guns.rest.modular.film.dao.FilmRepository;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzhibin
 * @time 2020/3/31 21:09
 */
@Component
@Service(interfaceClass = FilmServiceAPI.class)
public class FilmServiceAPIImpl implements FilmServiceAPI {

    @Resource
    private BannerRepository bannerRepository;

    @Resource
    private FilmRepository filmRepository;
    @Resource
    private FilmInfoRepository filmInfoRepository;

    @Override
    public List<BannerVO> getBanners() {
        List<BannerDO> bannerDOList = bannerRepository.selectList(null);
        List<BannerVO> bannerVOList = FilmConvert.convertToBannerVOS(bannerDOList);
        return bannerVOList;
    }

    @Override
    public FilmVO getHotFilms(boolean isLimit, int nums) {
        FilmVO filmVO = new FilmVO();
        List<FilmInfo> filmInfos = new ArrayList<>();
        //热门影片的限制条件
        EntityWrapper<FilmDO> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("film_status", "1");
        //判断是否是首页需要的内容
        if (isLimit) {
            //如果是，则限制条数、限制内容为热门电影
            Page<FilmDO> page = new Page<>(1, nums);
            List<FilmDO> filmDOS = filmRepository.selectPage(page, entityWrapper);
            //组织filmInfo
            filmInfos = FilmConvert.convert(filmDOS);
            filmVO.setFilmNum(filmDOS.size());
            filmVO.setFilmInfos(filmInfos) ;
        }


        //如果不是
        return null;
    }

    @Override
    public FilmVO getSoonFilms(boolean isLimit, int nums) {
        return null;
    }

    @Override
    public List<FilmInfo> getBoxRanking() {
        return null;
    }

    @Override
    public List<FilmInfo> getTop() {
        return null;
    }
}

package com.stylefeng.guns.rest.convert;

import com.google.common.collect.Lists;

import com.stylefeng.guns.api.film.vo.BannerVO;
import com.stylefeng.guns.api.film.vo.FilmInfo;
import com.stylefeng.guns.core.util.DateUtil;
import com.stylefeng.guns.rest.entity.BannerDO;
import com.stylefeng.guns.rest.entity.FilmDO;

import java.util.List;

/**
 * 影片模块相关转换
 *
 * @author chenzhibin
 * @time 2020/3/31 21:16
 */
public class FilmConvert {

    public static List<BannerVO> convertToBannerVOS(List<BannerDO> banners) {
        List<BannerVO> bannerVOlist = Lists.newArrayList();
        for (BannerDO bannerDO : banners) {
            bannerVOlist.add(convert(bannerDO));
        }
        return bannerVOlist;

    }

    private static BannerVO convert(BannerDO bannerDO) {
        BannerVO bannerVO = new BannerVO();
        bannerVO.setBannerId(bannerDO.getUuid() + "");
        bannerVO.setBannerAddress(bannerDO.getBannerAddress());
        bannerVO.setBannerUrl(bannerDO.getBannerUrl());
        return bannerVO;
    }

    public static List<FilmInfo> convert(List<FilmDO> filmDOS) {
        List<FilmInfo> filmInfolist=Lists.newArrayList();
        for (FilmDO filmDO :filmDOS) {
        	filmInfolist.add(convertFromFilmDO(filmDO));
        }
        return filmInfolist;

    }

    private static FilmInfo convertFromFilmDO(FilmDO filmDO) {
        FilmInfo filmInfo = new FilmInfo();
        filmInfo.setFilmId(filmDO.getUuid()+"");
        filmInfo.setFilmType(filmDO.getFilmType());
        filmInfo.setImgAddress(filmDO.getImgAddress());
        filmInfo.setFilmName(filmDO.getFilmName());
        filmInfo.setFilmScore(filmDO.getFilmScore());
        filmInfo.setExpectNum(filmDO.getFilmPresalenum());
        filmInfo.setShowTime(DateUtil.getDay(filmDO.getFilmTime()));
        filmInfo.setBoxNum(filmDO.getFilmBoxOffice());
        filmInfo.setScore(filmDO.getFilmScore());
        return filmInfo;
    }
}

package com.stylefeng.guns.rest.convert;

import com.google.common.collect.Lists;

import com.stylefeng.guns.api.film.vo.*;
import com.stylefeng.guns.core.util.DateUtil;
import com.stylefeng.guns.rest.entity.*;

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

    public static List<FilmInfo> convertToFilmInfo(List<FilmDO> filmDOS) {
        List<FilmInfo> filmInfolist = Lists.newArrayList();
        for (FilmDO filmDO : filmDOS) {
            filmInfolist.add(convertFromFilmDO(filmDO));
        }
        return filmInfolist;

    }

    private static FilmInfo convertFromFilmDO(FilmDO filmDO) {
        FilmInfo filmInfo = new FilmInfo();
        filmInfo.setFilmId(filmDO.getUuid() + "");
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

    public static List<CatVO> convertToCatVO(List<CatDictDO> catDicts) {
        List<CatVO> catVOlist = Lists.newArrayList();
        for (CatDictDO catDictDO : catDicts) {
            catVOlist.add(convertFromCatDictDO(catDictDO));
        }
        return catVOlist;

    }

    private static CatVO convertFromCatDictDO(CatDictDO catDictDO) {
        CatVO catVO = new CatVO();
        catVO.setCatId(catDictDO.getUuid() + "");
        catVO.setCatName(catDictDO.getShowName());
        return catVO;
    }

    public static List<YearVO> convert(List<YearDictDO> yearDictDOs) {
        List<YearVO> yearVOlist = Lists.newArrayList();
        for (YearDictDO yearDictDO : yearDictDOs) {
            yearVOlist.add(convertFromYearDictDO(yearDictDO));
        }
        return yearVOlist;

    }

    private static YearVO convertFromYearDictDO(YearDictDO yearDictDO) {
        YearVO yearVO = new YearVO();
        yearVO.setYearId(yearDictDO.getUuid() + "");
        yearVO.setYearName(yearDictDO.getShowName());
        return yearVO;
    }

    public static List<SourceVO> convertToSourceVO(List<SourceDictDO> sourceDictDOs) {
        List<SourceVO> sourceVOlist = Lists.newArrayList();
        for (SourceDictDO sourceDictDO : sourceDictDOs) {
            sourceVOlist.add(convertFromSourceDictDO(sourceDictDO));
        }
        return sourceVOlist;

    }

    private static SourceVO convertFromSourceDictDO(SourceDictDO sourceDictDO) {
        SourceVO sourceVO = new SourceVO();
        sourceVO.setSourceId(sourceDictDO.getUuid() + "");
        sourceVO.setSourceName(sourceDictDO.getShowName());
        return sourceVO;
    }
}

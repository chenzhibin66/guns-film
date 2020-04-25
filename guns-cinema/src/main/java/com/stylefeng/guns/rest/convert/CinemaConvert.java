package com.stylefeng.guns.rest.convert;
import com.google.common.collect.Lists;

import com.stylefeng.guns.api.cinema.vo.CinemaInfoVO;
import com.stylefeng.guns.api.cinema.vo.CinemaVO;
import com.stylefeng.guns.rest.entity.CinemaDO;

import java.util.List;

/**
 * @author chenzhibin
 * @time 2020/4/24 15:46
 */
public class CinemaConvert {


    public static List<CinemaVO> convert(List<CinemaDO> cinemas) {
        List<CinemaVO> cinemaVOlist=Lists.newArrayList();
        for (CinemaDO cinemaDO :cinemas) {
        	cinemaVOlist.add(convertFromCinemaDO(cinemaDO));
        }
        return cinemaVOlist;

    }

    private static CinemaVO convertFromCinemaDO(CinemaDO cinemaDO) {
        CinemaVO cinemaVO = new CinemaVO();
        cinemaVO.setUuid(cinemaDO.getUuid()+"");
        cinemaVO.setCinemaName(cinemaDO.getCinemaName());
        cinemaVO.setAddress(cinemaDO.getCinemaAddress());
        cinemaVO.setMinimumPrice(cinemaDO.getMinimumPrice()+"");
        return cinemaVO;
    }

    public static CinemaInfoVO convert(CinemaDO cinemaDO) {
        CinemaInfoVO cinemaInfoVO = new CinemaInfoVO();
        cinemaInfoVO.setCinemaId(cinemaDO.getUuid()+"");
        cinemaInfoVO.setImgUrl(cinemaDO.getImgAddress());
        cinemaInfoVO.setCinemaName(cinemaDO.getCinemaName());
        cinemaInfoVO.setCinemaAddress(cinemaDO.getCinemaAddress());
        cinemaInfoVO.setCinemaPhone(cinemaDO.getCinemaPhone());
        return cinemaInfoVO;
    }
}

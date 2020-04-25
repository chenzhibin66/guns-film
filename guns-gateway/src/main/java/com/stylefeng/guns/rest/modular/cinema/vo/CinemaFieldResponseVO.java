package com.stylefeng.guns.rest.modular.cinema.vo;

import com.stylefeng.guns.api.cinema.vo.CinemaInfoVO;
import com.stylefeng.guns.api.cinema.vo.FilmInfoVO;
import com.stylefeng.guns.api.cinema.vo.HallInfoVO;
import lombok.Data;

/**
 * @author chenzhibin
 * @time 2020/4/25 14:56
 */
@Data
public class CinemaFieldResponseVO {
    private CinemaInfoVO cinemaInfo;
    private FilmInfoVO filmInfo;
    private HallInfoVO hallInfo;
}

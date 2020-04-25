package com.stylefeng.guns.rest.modular.cinema.vo;

import com.stylefeng.guns.api.cinema.vo.CinemaInfoVO;
import com.stylefeng.guns.api.cinema.vo.FilmInfoVO;
import lombok.Data;

import java.util.List;

/**
 * @author chenzhibin
 * @time 2020/4/25 15:01
 */
@Data
public class CinemaFieldsResponseVO {
    private CinemaInfoVO cinemaInfo;
    private List<FilmInfoVO> filmList;
}

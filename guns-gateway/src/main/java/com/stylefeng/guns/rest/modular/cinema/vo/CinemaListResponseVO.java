package com.stylefeng.guns.rest.modular.cinema.vo;

import com.stylefeng.guns.api.cinema.vo.CinemaVO;
import lombok.Data;

import java.util.List;

/**
 * @author chenzhibin
 * @time 2020/4/25 14:46
 */
@Data
public class CinemaListResponseVO {

    private List<CinemaVO> cinemas;
}

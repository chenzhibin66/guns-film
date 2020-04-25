package com.stylefeng.guns.rest.modular.cinema.vo;

import com.stylefeng.guns.api.cinema.vo.AreaVO;
import com.stylefeng.guns.api.cinema.vo.BrandVO;
import com.stylefeng.guns.api.cinema.vo.HallTypeVO;
import lombok.Data;

import java.util.List;

/**
 * @author chenzhibin
 * @time 2020/4/25 14:50
 */
@Data
public class CinemaConditionResponseVO {
    private List<BrandVO> brandList;
    private List<AreaVO> areaList;
    private List<HallTypeVO> hallTypeList;
}

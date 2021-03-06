package com.stylefeng.guns.rest.modular.cinema;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.api.cinema.CinemaServiceAPI;
import com.stylefeng.guns.api.cinema.vo.*;
import com.stylefeng.guns.api.order.OrderServiceAPI;
import com.stylefeng.guns.rest.modular.cinema.vo.CinemaConditionResponseVO;
import com.stylefeng.guns.rest.modular.cinema.vo.CinemaFieldResponseVO;
import com.stylefeng.guns.rest.modular.cinema.vo.CinemaFieldsResponseVO;
import com.stylefeng.guns.rest.modular.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author chenzhibin
 * @time 2020/4/23 16:14
 */
@Slf4j
@Controller
@ResponseBody
@RequestMapping("/cinema/")
public class CinemaController {

    private static final String IMG_PRE = "http://39.96.162.42/get_audio_src?";

    @Reference(interfaceClass = CinemaServiceAPI.class, cache = "lru", check = false)
    private CinemaServiceAPI cinemaServiceAPI;

    @Reference(interfaceClass = OrderServiceAPI.class, check = false)
    private OrderServiceAPI orderServiceAPI;

    @RequestMapping(value = "getCinemas")
    public ResponseVO getCinemas(CinemaQueryVO cinemaQueryVO) {
        try {
            Page<CinemaVO> page = cinemaServiceAPI.getCinemas(cinemaQueryVO);
            //判断是否有满足条件的影院
            if (page.getRecords() == null || page.getRecords().size() == 0) {
                return ResponseVO.serviceFail("没有影院可查!");
            } else {
                return ResponseVO.success(page.getCurrent(), (int) page.getPages(), "", page.getRecords());
            }
        } catch (Exception e) {
            log.error("获取影院列表异常", e);
            return ResponseVO.serviceFail("查询影院列表失败!");
        }
    }

    @RequestMapping(value = "getCondition")
    public ResponseVO getCondition(CinemaQueryVO cinemaQueryVO) {
        //获取三个集合,然后封装成一个对象返回
        try {
            List<BrandVO> brands = cinemaServiceAPI.getBrands(cinemaQueryVO.getBrandId());
            List<AreaVO> areas = cinemaServiceAPI.getAreas(cinemaQueryVO.getDistrictId());
            List<HallTypeVO> hallTypes = cinemaServiceAPI.getHallTypes(cinemaQueryVO.getHallType());

            CinemaConditionResponseVO cinemaConditionResponseVO = new CinemaConditionResponseVO();
            cinemaConditionResponseVO.setAreaList(areas);
            cinemaConditionResponseVO.setBrandList(brands);
            cinemaConditionResponseVO.setHallTypeList(hallTypes);
            return ResponseVO.success(cinemaConditionResponseVO);
        } catch (Exception e) {
            log.error("获取条件列表失败", e);
            return ResponseVO.serviceFail("获取影院查询列表失败!");
        }
    }

    @RequestMapping(value = "getFields")
    public ResponseVO getFields(Integer cinemaId) {
        try {
            CinemaInfoVO cinemaInfo = cinemaServiceAPI.getCinemaInfoById(cinemaId);
            List<FilmInfoVO> filmInfo = cinemaServiceAPI.getFilmInfoByCinemaId(cinemaId);
            CinemaFieldsResponseVO cinemaFieldsResponseVO = new CinemaFieldsResponseVO();
            cinemaFieldsResponseVO.setCinemaInfo(cinemaInfo);
            cinemaFieldsResponseVO.setFilmList(filmInfo);
            return ResponseVO.success(IMG_PRE, cinemaFieldsResponseVO);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取播放场次失败!");
            return ResponseVO.serviceFail("获取播放场次失败!");
        }
    }

    @RequestMapping(value = "getFieldInfo", method = RequestMethod.POST)
    public ResponseVO getFieldInfo(Integer cinemaId, Integer fieldId) {
        if (cinemaId == null && fieldId == null) {
            log.error("cinemaId and fieldId is null");
            return null;
        }
        System.out.println("cinemaId:" + cinemaId + " " + "filedId:" + fieldId);
        try {
            CinemaInfoVO cinemaInfo = cinemaServiceAPI.getCinemaInfoById(cinemaId);
            FilmInfoVO filmInfo = cinemaServiceAPI.getFilmInfoByFieldId(fieldId);
            HallInfoVO hallInfo = cinemaServiceAPI.getFilmFieldInfo(fieldId);
            hallInfo.setSoldSeats(orderServiceAPI.getSoldSeatsByFieldId(fieldId));
            CinemaFieldResponseVO cinemaFieldResponseVO = new CinemaFieldResponseVO();
            cinemaFieldResponseVO.setCinemaInfo(cinemaInfo);
            cinemaFieldResponseVO.setFilmInfo(filmInfo);
            cinemaFieldResponseVO.setHallInfo(hallInfo);
            return ResponseVO.success(IMG_PRE, cinemaFieldResponseVO);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取选座信息失败!", e);
            return ResponseVO.serviceFail("获取选座信息失败!");
        }
    }
}

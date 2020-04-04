package com.stylefeng.guns.rest.modular.film;

import com.stylefeng.guns.api.film.FilmServiceAPI;
import com.stylefeng.guns.rest.modular.film.vo.FilmIndexVO;
import com.stylefeng.guns.rest.modular.vo.ResponseVO;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenzhibin
 * @time 2020/3/29 15:36
 */
@RestController
@RequestMapping("/film/")
public class FilmController {

    private static final String IMG_PRE = "http//img/pre/";
    @Reference(interfaceClass = FilmServiceAPI.class)
    private FilmServiceAPI filmServiceAPI;

    /**
     * 获取首页信息接口
     * API网关
     * 1.功能聚合
     * 好处:
     * 1.6个接口,一次请求,同一时刻节省了5次http请求
     * 2.同一个接口对外暴露,降低了前后端分离开发的难度和复杂度
     **/
    @RequestMapping(value = "getIndex", method = RequestMethod.GET)
    public ResponseVO<FilmIndexVO> getIndex() {
        FilmIndexVO filmIndexVO = new FilmIndexVO();
        filmIndexVO.setBanners(filmServiceAPI.getBanners());
        filmIndexVO.setHotFilms(filmServiceAPI.getHotFilms(true, 8));
        filmIndexVO.setSoonFilms(filmServiceAPI.getSoonFilms(true, 8));
        filmIndexVO.setBoxRanking(filmServiceAPI.getBoxRanking());
        filmIndexVO.setExpectRanking(filmServiceAPI.getExpectRanking());
        filmIndexVO.setTop100(filmServiceAPI.getTop());
        return ResponseVO.success(IMG_PRE, filmIndexVO);
    }

    @RequestMapping(value = "getConditionList", method = RequestMethod.GET)
    public ResponseVO getConditionList(@RequestParam(value = "catId", required = false, defaultValue = "99") String catId,
                                       @RequestParam(value = "sourceId", required = false, defaultValue = "99") String sourceId,
                                       @RequestParam(value = "yearId", required = false, defaultValue = "99") String yearId) {
        //类型集合
        return null;
    }
}

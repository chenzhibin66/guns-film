package com.stylefeng.guns.rest.modular.film;

import com.stylefeng.guns.api.film.FilmAsyncServiceAPI;
import com.stylefeng.guns.api.film.FilmServiceAPI;
import com.stylefeng.guns.api.film.vo.*;
import com.stylefeng.guns.rest.modular.film.vo.FilmConditionVO;
import com.stylefeng.guns.rest.modular.film.vo.FilmIndexVO;
import com.stylefeng.guns.rest.modular.film.vo.FilmRequestVO;
import com.stylefeng.guns.rest.modular.vo.ResponseVO;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author chenzhibin
 * @time 2020/3/29 15:36
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/film/")
public class FilmController {

    private static final String IMG_PRE = "http//img/pre/";
    @Reference(interfaceClass = FilmServiceAPI.class, check = false)
    private FilmServiceAPI filmServiceAPI;

    @Reference(interfaceClass = FilmAsyncServiceAPI.class, async = true, check = false)
    private FilmAsyncServiceAPI filmAsyncServiceAPI;

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
        filmIndexVO.setHotFilms(filmServiceAPI.getHotFilms(true, 8, 1, 1, 99, 99, 99));
        filmIndexVO.setSoonFilms(filmServiceAPI.getSoonFilms(true, 8, 1, 2, 99, 99, 99));
        filmIndexVO.setBoxRanking(filmServiceAPI.getBoxRanking());
        filmIndexVO.setExpectRanking(filmServiceAPI.getExpectRanking());
        filmIndexVO.setTop100(filmServiceAPI.getTop());
        return ResponseVO.success(IMG_PRE, filmIndexVO);
    }

    @RequestMapping(value = "getConditionList", method = RequestMethod.GET)
    public ResponseVO getConditionList(@RequestParam(value = "catId", required = false, defaultValue = "99") String catId,
                                       @RequestParam(value = "sourceId", required = false, defaultValue = "99") String sourceId,
                                       @RequestParam(value = "yearId", required = false, defaultValue = "99") String yearId) {
        FilmConditionVO filmConditionVO = new FilmConditionVO();
        boolean flag = false;
        //类型集合
        List<CatVO> cats = filmServiceAPI.getCats();
        List<CatVO> catResult = new ArrayList<>();
        CatVO cat = null;
        for (CatVO catVO : cats) {
            //判断集合是否存在catId,如果存在将对应的实体变成active状态
            if (catVO.getCatId().equals("99")) {
                cat = catVO;
                continue;
            }
            if (catVO.getCatId().equals(catId)) {
                flag = true;
                catVO.setActive(true);
            } else {
                catVO.setActive(false);
            }
            catResult.add(catVO);
            //如果不存在,则默认将全部变为Active状态
        }
        if (!flag) {
            cat.setActive(true);
            catResult.add(cat);
        } else {
            cat.setActive(false);
            catResult.add(cat);
        }
        //片源集合
        flag = false;
        List<SourceVO> sources = filmServiceAPI.getSources();
        List<SourceVO> sourceResult = new ArrayList<>();
        SourceVO sourceVO = null;
        for (SourceVO source : sources) {
            if (source.getSourceId().equals("99")) {
                sourceVO = source;
                continue;
            }
            if (source.getSourceId().equals(sourceId)) {
                flag = true;
                source.setActive(true);
            } else {
                source.setActive(false);
            }
            sourceResult.add(source);

        }
        if (!flag) {
            sourceVO.setActive(true);
            sourceResult.add(sourceVO);
        } else {
            sourceVO.setActive(false);
            sourceResult.add(sourceVO);
        }
        //年代集合
        flag = false;
        List<YearVO> years = filmServiceAPI.getYears();
        List<YearVO> yearResult = new ArrayList<>();
        YearVO yearVO = null;
        for (YearVO year : years) {
            if (year.getYearId().equals("99")) {
                yearVO = year;
                continue;
            }
            if (year.getYearId().equals(yearId)) {
                flag = true;
                year.setActive(true);
            } else {
                year.setActive(false);
            }
            yearResult.add(year);
        }
        //如果不存在,则默认将全部变为active状态
        if (!flag) {
            yearVO.setActive(true);
            yearResult.add(yearVO);
        } else {
            yearVO.setActive(false);
            yearResult.add(yearVO);
        }
        filmConditionVO.setCatInfo(catResult);
        filmConditionVO.setSourceInfo(sourceResult);
        filmConditionVO.setYearInfo(yearResult);
        return ResponseVO.success(filmConditionVO);
    }

    @RequestMapping(value = "getFilms", method = RequestMethod.GET)
    public ResponseVO getFilms(FilmRequestVO filmRequestVO) {
        FilmVO filmVO = null;
        String img_pre = "http://img.meetingshop.cn/";
        // 根据showType判断影片查询类型
        switch (filmRequestVO.getShowType()) {
            case 1:
                filmVO = filmServiceAPI.getHotFilms(
                        false, filmRequestVO.getPageSize(), filmRequestVO.getNowPage(),
                        filmRequestVO.getSortId(), filmRequestVO.getSourceId(), filmRequestVO.getYearId(),
                        filmRequestVO.getCatId());
                break;
            case 2:
                filmVO = filmServiceAPI.getSoonFilms(
                        false, filmRequestVO.getPageSize(), filmRequestVO.getNowPage(),
                        filmRequestVO.getSortId(), filmRequestVO.getSourceId(), filmRequestVO.getYearId(),
                        filmRequestVO.getCatId());
                break;
            case 3:
                filmVO = filmServiceAPI.getClassicFilms(
                        filmRequestVO.getPageSize(), filmRequestVO.getNowPage(),
                        filmRequestVO.getSortId(), filmRequestVO.getSourceId(),
                        filmRequestVO.getYearId(), filmRequestVO.getCatId());
                break;
            default:
                filmVO = filmServiceAPI.getHotFilms(
                        false, filmRequestVO.getPageSize(), filmRequestVO.getNowPage(),
                        filmRequestVO.getSortId(), filmRequestVO.getSourceId(), filmRequestVO.getYearId(),
                        filmRequestVO.getCatId());
                break;
        }
        return ResponseVO.success(filmVO.getNowPage(), filmVO.getTotalPage(),
                img_pre, filmVO.getFilmInfos());
    }

    @RequestMapping(value = "films/{searchParam}", method = RequestMethod.GET)
    public ResponseVO films(@PathVariable("searchParam") String searchParam, int searchType) throws ExecutionException, InterruptedException {
        //根据searchType,判断查询类型
        FilmDetailVO filmDetailVO = filmServiceAPI.getFilmDetail(searchType, searchParam);
        if (null == filmDetailVO) {
            return ResponseVO.serviceFail("没有可查询的影片!");
        } else if (filmDetailVO.getFilmId() == null || filmDetailVO.getFilmId().trim().length() == 0) {
            return ResponseVO.serviceFail("没有可查询的影片!");
        }
        String filmId = filmDetailVO.getFilmId();
        //查询影片的详细信息----Dubbo的异步获取
        //获取影片信息
        filmAsyncServiceAPI.getFilmDesc(filmId);
        Future<FilmDescVO> filmDescVOFuture = RpcContext.getContext().getFuture();

        //获取图片信息
        filmAsyncServiceAPI.getImgs(filmId);
        Future<ImgVO> imgVOFuture = RpcContext.getContext().getFuture();

        //获取导演信息
        filmAsyncServiceAPI.getDirector(filmId);
        Future<ActorVO> directorFuture = RpcContext.getContext().getFuture();

        //获取演员信息
        filmAsyncServiceAPI.getActors(filmId);
        Future<List<ActorVO>> actorsFuture = RpcContext.getContext().getFuture();


        InfoRequestVO infoRequestVO = new InfoRequestVO();
        ActorRequestVO actorRequestVO = new ActorRequestVO();
        actorRequestVO.setActors(actorsFuture.get());
        actorRequestVO.setDirector(directorFuture.get());

        infoRequestVO.setActors(actorRequestVO);
        infoRequestVO.setBiography(filmDescVOFuture.get().getBiography());
        infoRequestVO.setFilmId(filmId);
        infoRequestVO.setImgVO(imgVOFuture.get());

        filmDetailVO.setInfo04(infoRequestVO);
        return ResponseVO.success("http://img.meetingshop.cn/", filmDetailVO);

    }
}

package com.stylefeng.guns.rest.modular.film.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.api.film.FilmServiceAPI;
import com.stylefeng.guns.api.film.vo.*;
import com.stylefeng.guns.rest.constant.FilmConstants;
import com.stylefeng.guns.rest.convert.FilmConvert;
import com.stylefeng.guns.rest.entity.*;
import com.stylefeng.guns.rest.modular.film.dao.*;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzhibin
 * @time 2020/3/31 21:09
 */
@Component
@Service(interfaceClass = FilmServiceAPI.class)
public class FilmServiceAPIImpl implements FilmServiceAPI {

    @Resource
    private BannerRepository bannerRepository;
    @Resource
    private FilmRepository filmRepository;
    @Resource
    private FilmInfoRepository filmInfoRepository;
    @Resource
    private CatDictRepository catDictRepository;
    @Resource
    private YearDictRepository yearDictRepository;
    @Resource
    private SourceDictRepository sourceDictRepository;
    @Resource
    private ActorRepository actorRepository;

    @Override
    public List<BannerVO> getBanners() {
        List<BannerDO> bannerDOList = bannerRepository.selectList(null);
        List<BannerVO> bannerVOList = FilmConvert.convertToBannerVOS(bannerDOList);
        return bannerVOList;
    }

    @Override
    public FilmVO getHotFilms(boolean isLimit, int nums, int nowPage, int sortId, int sourceId, int yearId, int catId) {
        FilmVO filmVO = new FilmVO();
        List<FilmInfo> filmInfos = new ArrayList<>();

        //热门影片的限制条件
        EntityWrapper<FilmDO> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq(FilmConstants.FILM_STATUS, FilmConstants.NOW_SHOWING);
        //判断是否是首页需要的内容
        if (isLimit) {
            //如果是,则限制条数\限制内容为热门影片
            Page<FilmDO> page = new Page<>(1, nums);
            List<FilmDO> filmDOS = filmRepository.selectPage(page, entityWrapper);
            filmInfos = FilmConvert.convertToFilmInfo(filmDOS);
        } else {
            //如果不是,则是列表页,同样需要限制内容为热门影片
            Page<FilmDO> page = null;
            // 根据sortId的不同，来组织不同的Page对象
            // 1-按热门搜索，2-按时间搜索，3-按评价搜索
            switch (sortId) {
                case FilmConstants.QUERY_BY_HOT:
                    page = new Page<>(nowPage, nums, FilmConstants.ORDER_BY_FILM_BOX_OFFICE);
                    break;
                case FilmConstants.QUERY_BY_TIME:
                    page = new Page<>(nowPage, nums, FilmConstants.ORDER_BY_FILM_TIME);
                    break;
                case FilmConstants.QUERY_BY_EVALUATE:
                    page = new Page<>(nowPage, nums, FilmConstants.ORDER_BY_FILM_SCORE);
                    break;
                default:
                    page = new Page<>(nowPage, nums, FilmConstants.ORDER_BY_FILM_BOX_OFFICE);
            }
            // 如果sourceId,yearId,catId 不为99 ,则表示要按照对应的编号进行查询
            judge(sourceId, yearId, catId, entityWrapper);
            List<FilmDO> filmDOS = filmRepository.selectPage(page, entityWrapper);
            filmInfos = FilmConvert.convertToFilmInfo(filmDOS);

            filmVO.setFilmNum(filmDOS.size());


            // 需要总页数 totalCounts/nums -> 0 + 1 = 1
            // 每页10条，我现在有6条 -> 1
            int totalCounts = filmRepository.selectCount(entityWrapper);
            int totalPages = (totalCounts / nums) + 1;

            filmVO.setFilmInfos(filmInfos);
            filmVO.setTotalPage(totalPages);
            filmVO.setNowPage(nowPage);
        }
        return filmVO;
    }

    @Override
    public FilmVO getSoonFilms(boolean isLimit, int nums, int nowPage, int sortId, int sourceId, int yearId, int catId) {
        FilmVO filmVO = new FilmVO();
        List<FilmInfo> filmInfos = new ArrayList<>();
        EntityWrapper<FilmDO> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq(FilmConstants.FILM_STATUS, FilmConstants.WILL_SHOWING);
        if (isLimit) {
            Page<FilmDO> page = new Page<>(1, nums);
            List<FilmDO> filmDOS = filmRepository.selectPage(page, entityWrapper);
            filmInfos = FilmConvert.convertToFilmInfo(filmDOS);
            filmVO.setFilmNum(filmDOS.size());
            filmVO.setFilmInfos(filmInfos);
        } else {
            // 如果不是，则是列表页，同样需要限制内容为即将上映影片
            Page<FilmDO> page = null;
            // 根据sortId的不同，来组织不同的Page对象
            // 1-按热门搜索，2-按时间搜索，3-按评价搜索
            switch (sortId) {
                case FilmConstants.QUERY_BY_HOT:
                    page = new Page<>(nowPage, nums, FilmConstants.ORDER_BY_FILM_PRESALENUM);
                    break;
                case FilmConstants.QUERY_BY_TIME:
                    page = new Page<>(nowPage, nums, FilmConstants.ORDER_BY_FILM_TIME);
                    break;
                case FilmConstants.QUERY_BY_EVALUATE:
                    page = new Page<>(nowPage, nums, FilmConstants.ORDER_BY_FILM_PRESALENUM);
                    break;
                default:
                    page = new Page<>(nowPage, nums, FilmConstants.ORDER_BY_FILM_PRESALENUM);
            }
            judge(sourceId, yearId, catId, entityWrapper);
            List<FilmDO> filmDOS = filmRepository.selectPage(page, entityWrapper);

            filmInfos = FilmConvert.convertToFilmInfo(filmDOS);
            filmVO.setFilmNum(filmDOS.size());
            // 需要总页数 totalCounts/nums -> 0 + 1 = 1
            // 每页10条，我现在有6条 -> 1
            int totalCounts = filmRepository.selectCount(entityWrapper);
            int totalPages = (totalCounts / nums) + 1;

            filmVO.setFilmInfos(filmInfos);
            filmVO.setTotalPage(totalPages);
            filmVO.setNowPage(nowPage);
        }
        return filmVO;
    }

    @Override
    public FilmVO getClassicFilms(int nums, int nowPage, int sortId, int sourceId, int yearId, int catId) {
        FilmVO filmVO = new FilmVO();
        List<FilmInfo> filmInfos = new ArrayList<>();

        //即将上映影片的限制条件
        EntityWrapper<FilmDO> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq(FilmConstants.FILM_STATUS, FilmConstants.CLASSIC_FILM);

        //如果不是,则是列表页,同样需要限制内容为即将上映影片
        Page<FilmDO> page = null;
        // 根据sortId的不同，来组织不同的Page对象
        // 1-按热门搜索，2-按时间搜索，3-按评价搜索
        switch (sortId) {
            case FilmConstants.QUERY_BY_HOT:
                page = new Page<>(nowPage, nums, FilmConstants.ORDER_BY_FILM_BOX_OFFICE);
                break;
            case FilmConstants.QUERY_BY_TIME:
                page = new Page<>(nowPage, nums, FilmConstants.ORDER_BY_FILM_TIME);
                break;
            case FilmConstants.QUERY_BY_EVALUATE:
                page = new Page<>(nowPage, nums, FilmConstants.ORDER_BY_FILM_SCORE);
                break;
            default:
                page = new Page<>(nowPage, nums, FilmConstants.ORDER_BY_FILM_BOX_OFFICE);
                break;

        }
        // 如果sourceId,yearId,catId 不为99 ,则表示要按照对应的编号进行查询
        judge(sourceId, yearId, catId, entityWrapper);

        List<FilmDO> filmDOS = filmRepository.selectPage(page, entityWrapper);
        // 组织filmInfos
        filmInfos = FilmConvert.convertToFilmInfo(filmDOS);
        filmVO.setFilmNum(filmDOS.size());

        // 需要总页数 totalCounts/nums -> 0 + 1 = 1
        // 每页10条，我现在有6条 -> 1
        int totalCounts = filmRepository.selectCount(entityWrapper);
        int totalPages = (totalCounts / nums) + 1;

        filmVO.setFilmInfos(filmInfos);
        filmVO.setTotalPage(totalPages);
        filmVO.setNowPage(nowPage);

        return filmVO;
    }


    @Override
    public List<FilmInfo> getBoxRanking() {
        //条件:正在上映的票房前10名
        return getShowingFilm(FilmConstants.NOW_SHOWING, FilmConstants.CURRENT_PAGE, FilmConstants.PAGE_SIZE, FilmConstants.ORDER_BY_FILM_BOX_OFFICE);
    }

    @Override
    public List<FilmInfo> getExpectRanking() {
        //条件:即将上映预售前10名
        return getShowingFilm(FilmConstants.WILL_SHOWING, FilmConstants.CURRENT_PAGE, FilmConstants.PAGE_SIZE, FilmConstants.ORDER_BY_FILM_PRESALENUM);
    }

    @Override
    public List<FilmInfo> getTop() {
        //条件:正在上映评分前10名
        return getShowingFilm(FilmConstants.NOW_SHOWING, FilmConstants.CURRENT_PAGE, FilmConstants.PAGE_SIZE, FilmConstants.ORDER_BY_FILM_BOX_OFFICE);
    }

    @Override
    public List<CatVO> getCats() {

        List<CatDictDO> catDicts = catDictRepository.selectList(null);
        List<CatVO> catVOs = FilmConvert.convertToCatVO(catDicts);
        return catVOs;
    }

    @Override
    public List<SourceVO> getSources() {
        List<SourceDictDO> sourceDictDOs = sourceDictRepository.selectList(null);
        List<SourceVO> sourceVOs = FilmConvert.convertToSourceVO(sourceDictDOs);
        return sourceVOs;
    }

    @Override
    public List<YearVO> getYears() {
        List<YearDictDO> yearDictDOs = yearDictRepository.selectList(null);
        List<YearVO> yearVOs = FilmConvert.convert(yearDictDOs);
        return yearVOs;
    }

    @Override
    public FilmDetailVO getFilmDetail(int searchType, String searchParam) {
        FilmDetailVO filmDetailVO = null;
        //searchType 1-按名称查找 2-按id查找
        if (searchType == 1) {
            filmDetailVO = filmRepository.getFilmDetailByName(searchParam);
        } else {
            filmDetailVO = filmRepository.getFilmDetailById(searchParam);
        }
        return filmDetailVO;
    }

    @Override
    public FilmDescVO getFilmDesc(String filmId) {
        FilmInfoDO filmInfoDO = getFilmInfo(filmId);

        FilmDescVO filmDescVO = new FilmDescVO();
        filmDescVO.setBiography(filmInfoDO.getBiography());
        filmDescVO.setFilmId(filmId);
        return filmDescVO;
    }

    @Override
    public ImgVO getImgs(String filmId) {
        FilmInfoDO filmInfoDO = getFilmInfo(filmId);
        String filmImgStr = filmInfoDO.getFilmImgs();
        String[] filmImgs = filmImgStr.split(",");

        ImgVO imgVO = new ImgVO();
        imgVO.setMainImg(filmImgs[0]);
        imgVO.setImg01(filmImgs[1]);
        imgVO.setImg02(filmImgs[2]);
        imgVO.setImg03(filmImgs[3]);
        imgVO.setImg04(filmImgs[4]);
        return imgVO;
    }

    @Override
    public ActorVO getDirector(String filmId) {
        FilmInfoDO filmInfoDO = getFilmInfo(filmId);
        Integer directorId = filmInfoDO.getDirectorId();
        ActorDO actorDO = actorRepository.selectById(directorId);
        ActorVO actorVO = new ActorVO();
        actorVO.setImgAddress(actorDO.getActorImg());
        actorVO.setDirectorName(actorDO.getActorName());

        return actorVO;
    }

    @Override
    public List<ActorVO> getActors(String filmId) {
        FilmInfoDO filmInfoDO = getFilmInfo(filmId);
        List<ActorVO> actors = actorRepository.getActors(filmId);
        return actors;
    }

    public List<FilmInfo> getShowingFilm(String params, int current, int size, String orderByFiled) {
        EntityWrapper<FilmDO> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq(FilmConstants.FILM_STATUS, params);
        Page<FilmDO> page = new Page<>(current, size, orderByFiled);
        List<FilmInfo> filmInfos = FilmConvert.convertToFilmInfo(filmRepository.selectPage(page, entityWrapper));
        return filmInfos;
    }

    private void queryBySortId(int nowPage, int nums, int sortId, Page<FilmDO> page) {
        switch (sortId) {
            case FilmConstants.QUERY_BY_TIME:
                page = new Page<>(nowPage, nums, FilmConstants.ORDER_BY_FILM_TIME);
                break;
            case FilmConstants.QUERY_BY_EVALUATE:
                page = new Page<>(nowPage, nums, FilmConstants.ORDER_BY_FILM_SCORE);
                break;
            default:
                page = new Page<>(nowPage, nums, FilmConstants.ORDER_BY_FILM_BOX_OFFICE);
                break;
        }
    }


    private void judge(int sourceId, int yearId, int catId, EntityWrapper<FilmDO> entityWrapper) {
        if (sourceId != 99) {
            entityWrapper.eq("film_source", sourceId);
        }
        if (yearId != 99) {
            entityWrapper.eq("film_date", yearId);
        }
        if (catId != 99) {
            // #2#4#22#
            String catStr = "%#" + catId + "#%";
            entityWrapper.like("film_cats", catStr);
        }
    }

    private FilmInfoDO getFilmInfo(String filmId) {
        FilmInfoDO filmInfoDO = new FilmInfoDO();
        filmInfoDO.setFilmId(filmId);
        filmInfoDO = filmInfoRepository.selectOne(filmInfoDO);
        return filmInfoDO;
    }
}

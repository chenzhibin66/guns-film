package com.stylefeng.guns.rest.modular.film.service;

import com.stylefeng.guns.api.film.FilmAsyncServiceAPI;
import com.stylefeng.guns.api.film.vo.ActorVO;
import com.stylefeng.guns.api.film.vo.FilmDescVO;
import com.stylefeng.guns.api.film.vo.ImgVO;
import com.stylefeng.guns.rest.entity.ActorDO;
import com.stylefeng.guns.rest.entity.FilmInfoDO;
import com.stylefeng.guns.rest.modular.film.dao.ActorRepository;
import com.stylefeng.guns.rest.modular.film.dao.FilmInfoRepository;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chenzhibin
 * @time 2020/4/23 14:51
 */
@Component
@Service(interfaceClass = FilmAsyncServiceAPI.class)
public class FilmAsyncServiceAPIImpl implements FilmAsyncServiceAPI {

    @Resource
    private FilmInfoRepository filmInfoRepository;

    @Resource
    private ActorRepository actorRepository;

    @Override
    public FilmDescVO getFilmDesc(String filmId) {
        FilmInfoDO filmInfoDO = getFilmInfo(filmId);
        FilmDescVO filmDescVO = new FilmDescVO();
        filmDescVO.setBiography(filmInfoDO.getBiography());
        filmDescVO.setBiography(filmId);
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
        return actorRepository.getActors(filmId);
    }

    private FilmInfoDO getFilmInfo(String filmId) {
        FilmInfoDO filmInfoDO = new FilmInfoDO();
        filmInfoDO.setFilmId(filmId);
        filmInfoDO = filmInfoRepository.selectOne(filmInfoDO);
        return filmInfoDO;
    }
}

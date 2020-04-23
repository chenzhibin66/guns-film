package com.stylefeng.guns.api.film;

import com.stylefeng.guns.api.film.vo.ActorVO;
import com.stylefeng.guns.api.film.vo.FilmDescVO;
import com.stylefeng.guns.api.film.vo.ImgVO;

import java.util.List;

/**
 * @author chenzhibin
 * @time 2020/4/23 14:48
 */
public interface FilmAsyncServiceAPI {
    /**
     * 功能描述:
     *
     * @Param: 获取影片描述信息
     * @Return: com.stylefeng.guns.api.film.vo.FilmDescVO
     */
    FilmDescVO getFilmDesc(String filmId);

    /**
     * 功能描述: 获取图片信息
     *
     * @Param: [filmId]
     * @Return: com.stylefeng.guns.api.film.vo.ImgVO
     */
    ImgVO getImgs(String filmId);

    /**
     * 功能描述: 获取导演信息
     *
     * @Param: [filmId]
     * @Return: com.stylefeng.guns.api.film.vo.ActorVO
     */
    ActorVO getDirector(String filmId);

    /**
     * 功能描述: 获取演员信息
     *
     * @Param: [filmId]
     * @Return: java.util.List<com.stylefeng.guns.api.film.vo.ActorVO>
     */
    List<ActorVO> getActors(String filmId);
}

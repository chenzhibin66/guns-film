package com.stylefeng.guns.api.film.vo;

import com.stylefeng.guns.api.film.vo.ActorRequestVO;
import com.stylefeng.guns.api.film.vo.ImgVO;
import lombok.Data;

/**
 * @author chenzhibin
 * @time 2020/4/22 16:36
 */
@Data
public class InfoRequestVO {
    private String biography;
    private ActorRequestVO actors;
    private ImgVO imgVO;
    private String filmId;
}

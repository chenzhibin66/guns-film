package com.stylefeng.guns.api.film.vo;

import com.stylefeng.guns.api.film.vo.ActorVO;
import lombok.Data;

import java.util.List;

/**
 * @author chenzhibin
 * @time 2020/4/22 16:31
 */
@Data
public class ActorRequestVO {
    private ActorVO director;
    private List<ActorVO> actors;
}

package com.stylefeng.guns.rest.modular.film.dao;

import com.stylefeng.guns.api.film.vo.ActorVO;
import com.stylefeng.guns.rest.entity.ActorDO;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 演员表 Mapper 接口
 * </p>
 *
 * @author chenzhibin
 * @since 2020-03-31
 */
public interface ActorRepository extends BaseMapper<ActorDO> {

    List<ActorVO> getActors(@Param("filmId") String filmId);
}

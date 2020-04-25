package com.stylefeng.guns.rest.modular.cinema.dao;

import com.stylefeng.guns.api.cinema.vo.FilmInfoVO;
import com.stylefeng.guns.api.cinema.vo.HallInfoVO;
import com.stylefeng.guns.rest.entity.FieldDO;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 放映场次表 Mapper 接口
 * </p>
 *
 * @author chenzhibin
 * @since 2020-04-24
 */
public interface FieldRepository extends BaseMapper<FieldDO> {

    List<FilmInfoVO> getFilmInfos(@Param("cinemaId") int cinemaId);

    HallInfoVO getHallInfo(@Param("fieldId") int field);

    FilmInfoVO getFilmInfoById(@Param("fieldId") int field);
}

package com.stylefeng.guns.rest.modular.order.dao;

import com.stylefeng.guns.rest.entity.OrderDO;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 订单信息表 Mapper 接口
 * </p>
 *
 * @author chenzhibin
 * @since 2020-04-27
 */
public interface OrderMapper extends BaseMapper<OrderDO> {

    String getSeatsByFieldId(@Param("fieldId") String fieldId);
}

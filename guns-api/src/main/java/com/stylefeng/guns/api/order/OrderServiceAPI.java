package com.stylefeng.guns.api.order;

import com.stylefeng.guns.api.order.vo.OrderVO;

import java.util.List;

/**
 * @author chenzhibin
 * @time 2020/4/26 16:07
 */
public interface OrderServiceAPI {
    /**
     * 功能描述: 验证售出的票是否为真
     *
     * @Param: [seats]
     * @Return: boolean
     */
    boolean isTrueSeats(String fieldId, String seats);

    /**
     * 功能描述:已经销售的座位里,有没有这些座位
     *
     * @Param: [fieldId, seats]
     * @Return: boolean
     */
    boolean isNotSoldSeats(String fieldId, String seats);

    /**
     * 功能描述:创建订单
     *
     * @Param: [fieldId, soldSeats, seatsName, userId]
     * @Return: com.stylefeng.guns.api.order.vo.OrderVO
     */
    OrderVO saveOrder(Integer fieldId, String soldSeats, String seatsName, Integer userId);

    /**
     * 功能描述: 查询当前用户已经购买的订单
     *
     * @Param: [userId]
     * @Return: java.util.List<com.stylefeng.guns.api.order.vo.OrderVO>
     */
    List<OrderVO> getOrdersByUserId(Integer userId);

    /**
     * 功能描述: 根据FieldId获取所有已经销售的座位编号
     *
     * @Param: [fieldId]
     * @Return: java.lang.String
     */
    String getSoldSeatsByFieldId(Integer fieldId);
}


package com.stylefeng.guns.rest.modular.order.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.api.cinema.CinemaServiceAPI;
import com.stylefeng.guns.api.cinema.vo.FilmInfoVO;
import com.stylefeng.guns.api.cinema.vo.OrderQueryVO;
import com.stylefeng.guns.api.order.OrderServiceAPI;
import com.stylefeng.guns.api.order.vo.OrderVO;
import com.stylefeng.guns.core.util.UUIDUtil;
import com.stylefeng.guns.rest.common.util.FTPUtil;
import com.stylefeng.guns.rest.entity.Order2020DO;
import com.stylefeng.guns.rest.modular.order.dao.Order2020Mapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;


/**
 * @author chenzhibin
 * @time 2020/4/27 14:49
 */

@Slf4j
@Component
@Service(interfaceClass = OrderServiceAPI.class)
public class OrderServiceAPIImpl2020 implements OrderServiceAPI {

    @Resource
    private Order2020Mapper order2020Mapper;

    @Reference(interfaceClass = CinemaServiceAPI.class, check = false)
    private CinemaServiceAPI cinemaServiceAPI;

    @Autowired
    private FTPUtil ftpUtil;

    @Override
    public boolean isTrueSeats(String fieldId, String seats) {
        //根据fieldId找到对应的座位位置图
        String seatPath = order2020Mapper.getSeatsByFieldId(fieldId);
        //读取位置图,判断seats是否为真
        String fileStrByAddress = ftpUtil.getFileStrByAddress(seatPath);
        //将fileStrByAddress转为JSON对象
        JSONObject jsonObject = JSONObject.parseObject(fileStrByAddress);
        //seats=1,2,3 ids="1,3,4,7,88"
        String ids = jsonObject.get("ids").toString();
        //每一次匹配上的,都给isTrue+1;
        String[] seatArrs = seats.split(",");
        String[] idArrs = ids.split(",");
        int isTrue = 0;
        for (String id : idArrs) {
            for (String seat : seatArrs) {
                if (seat.equalsIgnoreCase(id)) {
                    isTrue++;
                }
            }
        }
        //如果匹配上的数量与已售座位数一致,则表示全都匹配上了
        if (seatArrs.length == isTrue) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isNotSoldSeats(String fieldId, String seats) {
        EntityWrapper<Order2020DO> entityWrapper = new EntityWrapper();
        entityWrapper.eq("field_id", fieldId);
        List<Order2020DO> Order2020DOS = order2020Mapper.selectList(entityWrapper);
        String[] seatArrs = seats.split(",");
        //有任何一个编号匹配上,则直接返回失败
        for (Order2020DO order : Order2020DOS) {
            String[] ids = order.getSeatsIds().split(",");
            for (String id : ids) {
                for (String seat : seatArrs) {
                    if (id.equalsIgnoreCase(seat)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public OrderVO saveOrder(Integer fieldId, String soldSeats, String seatsName, Integer userId) {
        String uuid = UUIDUtil.getUuid();
        //影片信息
        FilmInfoVO filmInfoVO = cinemaServiceAPI.getFilmInfoByFieldId(fieldId);
        Integer filmId = Integer.parseInt(filmInfoVO.getFilmId());

        //影院信息
        OrderQueryVO orderQueryVO = cinemaServiceAPI.getOrderNeeds(fieldId);
        Integer cinemaId = Integer.parseInt(orderQueryVO.getCinemaId());
        double filmPrice = Double.parseDouble(orderQueryVO.getFilmPrice());

        //求订单总金额
        int solds = soldSeats.split(",").length;
        double totalPrice = getTotalPrice(solds, filmPrice);

        Order2020DO Order2020DO = new Order2020DO();
        Order2020DO.setUuid(uuid);
        Order2020DO.setSeatsName(seatsName);
        Order2020DO.setSeatsIds(soldSeats);
        Order2020DO.setOrderUser(userId);
        Order2020DO.setOrderPrice(totalPrice);
        Order2020DO.setFilmPrice(filmPrice);
        Order2020DO.setFieldId(fieldId);
        Order2020DO.setFilmId(filmId);
        Order2020DO.setCinemaId(cinemaId);

        Integer insert = order2020Mapper.save(Order2020DO);
        if (insert > 0) {
            OrderVO orderVO = order2020Mapper.getOrderInfoById(uuid);
            if (orderVO == null || orderVO.getOrderId() == null) {
                log.error("订单信息查询失败,订单编号为{}", uuid);
                return null;
            } else {
                return orderVO;
            }
        } else {
            log.error("订单插入失败");
            return null;
        }
    }

    @Override
    public Page<OrderVO> getOrdersByUserId(Integer userId, Page<OrderVO> page) {
        Page<OrderVO> result = new Page<>();
        if (userId == null) {
            log.error("订单查询业务失败,用户id为空");
            return null;
        } else {
            List<OrderVO> orders = order2020Mapper.getOrdersByUserId(userId, page);
            if (orders == null && orders.size() == 0) {
                result.setTotal(0);
                result.setRecords(new ArrayList<>());
                return result;
            } else {
                //获取订单总数
                EntityWrapper<Order2020DO> entityWrapper = new EntityWrapper<>();
                entityWrapper.eq("order_user", userId);
                Integer counts = order2020Mapper.selectCount(entityWrapper);
                result.setTotal(counts);
                result.setRecords(orders);
                return result;
            }
        }
    }

    @Override
    public String getSoldSeatsByFieldId(Integer fieldId) {
        if (fieldId == null) {
            log.error("查询已售座位错误,未传入任何场次编号");
            return "";
        } else {
            String soldSeat = order2020Mapper.getSoldSeatsByFieldId(fieldId);
            return soldSeat;
        }
    }

    @Override
    public OrderVO getOrderInfoById(String orderId) {

        OrderVO orderInfo = order2020Mapper.getOrderInfoById(orderId);
        return orderInfo;
    }

    @Override
    public boolean paySuccess(String orderId) {
        Order2020DO order2020DO = new Order2020DO();
        order2020DO.setUuid(orderId);
        order2020DO.setOrderStatus(1);
        Integer result = order2020Mapper.updateById(order2020DO);
        if (result >= 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean payFail(String orderId) {
        Order2020DO oderDO = new Order2020DO();
        oderDO.setUuid(orderId);
        oderDO.setOrderStatus(2);
        Integer result = order2020Mapper.updateById(oderDO);
        if (result >= 1) {
            return true;
        } else {
            return false;
        }
    }

    private double getTotalPrice(int solds, double filmPrice) {
        BigDecimal soldsDeci = new BigDecimal(solds);
        BigDecimal priceDeci = new BigDecimal(filmPrice);
        BigDecimal result = soldsDeci.multiply(priceDeci);
        BigDecimal bigDecimal = result.setScale(2, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }
}


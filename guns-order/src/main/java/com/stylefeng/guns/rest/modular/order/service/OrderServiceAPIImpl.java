package com.stylefeng.guns.rest.modular.order.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.api.cinema.CinemaServiceAPI;
import com.stylefeng.guns.api.cinema.vo.FilmInfoVO;
import com.stylefeng.guns.api.cinema.vo.OrderQueryVO;
import com.stylefeng.guns.api.order.OrderServiceAPI;
import com.stylefeng.guns.api.order.vo.OrderVO;
import com.stylefeng.guns.core.util.UUIDUtil;
import com.stylefeng.guns.rest.common.util.FTPUtil;
import com.stylefeng.guns.rest.entity.OrderDO;
import com.stylefeng.guns.rest.modular.order.dao.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
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
public class OrderServiceAPIImpl implements OrderServiceAPI {

    @Resource
    private OrderMapper orderMapper;

    @Reference(interfaceClass = CinemaServiceAPI.class,check = false)
    private CinemaServiceAPI cinemaServiceAPI;

    @Resource
    private FTPUtil ftpUtil;

    @Override
    public boolean isTrueSeats(String fieldId, String seats) {
        //根据fieldId找到对应的座位位置图
        String seatPath = orderMapper.getSeatsByFieldId(fieldId);
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
        EntityWrapper<OrderDO> entityWrapper = new EntityWrapper();
        entityWrapper.eq("fileld_id", fieldId);
        List<OrderDO> orderDOS = orderMapper.selectList(entityWrapper);
        String[] seatArrs = seats.split(",");
        //有任何一个编号匹配上,则直接返回失败
        for (OrderDO order : orderDOS) {
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

        OrderDO orderDO = new OrderDO();
        orderDO.setUuid(uuid);
        orderDO.setSeatsName(seatsName);
        orderDO.setSeatsIds(soldSeats);
        orderDO.setOrderUser(userId);
        orderDO.setOrderPrice(totalPrice);
        orderDO.setFilmPrice(filmPrice);
        orderDO.setFieldId(fieldId);
        orderDO.setFilmId(filmId);
        orderDO.setCinemaId(cinemaId);

        Integer insert = orderMapper.insert(orderDO);
        if (insert > 0) {
            OrderVO orderVO = orderMapper.getOrderInfoById(uuid);
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
    public List<OrderVO> getOrdersByUserId(Integer userId) {
        if (userId == null) {
            log.error("订单查询业务失败,用户id为空");
            return null;
        } else {
            List<OrderVO> orders = orderMapper.getOrdersByUserId(userId);
            if (orders == null && orders.size() == 0) {
                return new ArrayList<>();
            } else {
                return orders;
            }
        }
    }

    @Override
    public String getSoldSeatsByFieldId(Integer fieldId) {
        if (fieldId == null) {
            log.error("查询已售座位错误,未传入任何场次编号");
            return "";
        } else {
            String soldSeat = orderMapper.getSoldSeatsByFieldId(fieldId);
            return soldSeat;
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

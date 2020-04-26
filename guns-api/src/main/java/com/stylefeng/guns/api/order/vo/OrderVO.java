package com.stylefeng.guns.api.order.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chenzhibin
 * @time 2020/4/26 16:35
 */
@Data
public class OrderVO implements Serializable {
    private static final long serialVersionUID = -7872144717097542049L;
    private String orderId;
    private String filmName;
    private String fieldIdTime;
    private String cinemaName;
    private String seatsName;
    private String orderPrice;
    private String orderTimestamp;
    private String orderStatus;
}

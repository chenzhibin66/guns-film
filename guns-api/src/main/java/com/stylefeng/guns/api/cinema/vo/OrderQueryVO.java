package com.stylefeng.guns.api.cinema.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chenzhibin
 * @time 2020/4/28 10:20
 */
@Data
public class OrderQueryVO implements Serializable {
    private static final long serialVersionUID = -5700994847878556131L;

    private String cinemaId;
    private String filmPrice;
}

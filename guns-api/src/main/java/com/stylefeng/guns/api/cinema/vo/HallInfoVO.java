package com.stylefeng.guns.api.cinema.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chenzhibin
 * @time 2020/4/23 17:17
 */
@Data
public class HallInfoVO implements Serializable {
    private static final long serialVersionUID = 482709851953246406L;

    private String hallFieldId;
    private String hallName;
    private String price;
    private String seatFile;
    private String soldSeats;
}

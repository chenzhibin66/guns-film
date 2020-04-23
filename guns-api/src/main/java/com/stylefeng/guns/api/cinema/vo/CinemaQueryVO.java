package com.stylefeng.guns.api.cinema.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chenzhibin
 * @time 2020/4/23 16:17
 */
@Data
public class CinemaQueryVO implements Serializable {
    private static final long serialVersionUID = 4914597051358554196L;

    private Integer brandId;
    private Integer areaId;
    private Integer hallType;
    private Integer pageSize;
    private Integer nowPage;

}

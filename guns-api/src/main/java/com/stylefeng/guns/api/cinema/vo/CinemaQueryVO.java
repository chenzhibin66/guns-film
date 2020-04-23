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
    /**
     * 影院编号,默认为99,全部
     */
    private Integer brandId;
    /**
     * 影厅类型,默认为99,全部
     */
    private Integer hallType;
    /**
     * 行政区编号,默认为99,全部
     */
    private Integer districtId;
    /**
     * 每页条数,默认为12条
     */
    private Integer pageSize;
    /**
     * 当前页数,默认为第1页
     */
    private Integer nowPage;

}

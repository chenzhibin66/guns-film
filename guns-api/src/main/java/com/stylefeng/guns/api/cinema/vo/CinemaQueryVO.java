package com.stylefeng.guns.api.cinema.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chenzhibin
 * @time 2020/4/23 16:17
 */
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

    public Integer getBrandId() {
        if (brandId == null) {
            brandId = 99;
        }
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getHallType() {
        if (hallType == null) {
            hallType = 99;
        }
        return hallType;
    }

    public void setHallType(Integer hallType) {
        this.hallType = hallType;
    }

    public Integer getDistrictId() {
        if (districtId == null) {
            districtId = 99;
        }
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public Integer getPageSize() {
        if (pageSize == null) {
            pageSize = 12;
        }
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getNowPage() {
        if (nowPage == null) {
            nowPage = 1;
        }
        return nowPage;
    }

    public void setNowPage(Integer nowPage) {
        this.nowPage = nowPage;
    }
}

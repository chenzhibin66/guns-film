package com.stylefeng.guns.rest.modular.film.vo;

import lombok.Data;

/**
 * @author chenzhibin
 * @time 2020/4/9 23:35
 */
public class FilmRequestVO {
    /**
     * 查询类型,1-正在热映,2-即将上映,3-经典影片,默认为1
     */
    private Integer showType;
    /**
     * 排序方式,1-按热门搜索,2-按时间搜索,3-按评价搜索,默认为1
     */
    private Integer sortId;
    /**
     * 类型编号,默认为99
     */
    private Integer catId;
    /**
     * 区域编号,默认为99
     */
    private Integer sourceId;
    /**
     * 年代编号,默认为99
     */
    private Integer yearId;
    /**
     * 影片列表当前页,翻页使用,默认为1
     */
    private Integer nowPage;
    /**
     * 每页显示条数,默认18
     */
    private Integer pageSize;

    public Integer getShowType() {
        if (showType == null) {
            showType = 1;
        }
        return showType;
    }

    public void setShowType(Integer showType) {
        this.showType = showType;
    }

    public Integer getSortId() {
        if (sortId == null) {
            sortId = 1;
        }
        return sortId;
    }

    public void setSortId(Integer sortId) {
        this.sortId = sortId;
    }

    public Integer getCatId() {
        if (catId == null) {
            catId = 99;
        }
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public Integer getSourceId() {
        if (sourceId == null) {
            sourceId = 99;
        }
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public Integer getYearId() {
        if (yearId == null) {
            yearId = 99;
        }
        return yearId;
    }

    public void setYearId(Integer yearId) {
        this.yearId = yearId;
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

    public Integer getPageSize() {
        if (pageSize == null) {
            pageSize = 18;
        }
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}

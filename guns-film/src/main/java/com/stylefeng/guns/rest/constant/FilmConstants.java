package com.stylefeng.guns.rest.constant;

/**
 * 影片模块常量类
 *
 * @author chenzhibin
 * @time 2020/4/1 21:58
 */
public class FilmConstants {
    //分页当前页
    public static final int CURRENT_PAGE = 1;
    //一页数量大小
    public static final int PAGE_SIZE = 10;
    /**
     * 查询条件
     */
    public static final String ORDER_BY_FILM_SCORE = "film_score";
    public static final String ORDER_BY_FILM_BOX_OFFICE = "film_box_office";
    public static final String ORDER_BY_FILM_PRESALENUM = "film_preSaleNum";
    public static final String ORDER_BY_FILM_TIME = "film_time";
    /**
     * 影片状态,1-正在热映，2-即将上映，3-经典影片
     */
    public static final String NOW_SHOWING = "1";
    public static final String WILL_SHOWING = "2";
    public static final String CLASSIC_FILM = "3";
    /**
     * 1-按热门搜索，2-按时间搜索，3-按评价搜索
     */
    public static final int ORDER_BY_HOT = 1;
    public static final int QUERY_BY_TIME = 2;
    public static final int QUERY_BY_EVALUATE = 3;
}

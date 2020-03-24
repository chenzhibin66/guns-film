package com.stylefeng.guns.rest.utils;

import java.util.Date;

/**
 * 时间工具类
 *
 * @author chenzhibin
 * @time 2020/3/24 20:17
 */
public class TimeUtil {

    public static Date time2Date(long time) {
        Date date = new Date(time);
        return date;
    }
}

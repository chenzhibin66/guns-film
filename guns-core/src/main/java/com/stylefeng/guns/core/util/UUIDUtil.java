package com.stylefeng.guns.core.util;

import java.util.UUID;

/**
 * @author chenzhibin
 * @time 2020/4/28 10:15
 */
public class UUIDUtil {

    public static String getUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}

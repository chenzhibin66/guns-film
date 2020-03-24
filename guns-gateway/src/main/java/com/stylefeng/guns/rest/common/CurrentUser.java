package com.stylefeng.guns.rest.common;

/**
 * @author chenzhibin
 * @time 2020/3/24 15:19
 */
public class CurrentUser {
    /**
     * 线程绑定的存储空间
     **/
    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    /**
     * 将用户信息放入存储空间
     **/
    public static void saveUserId(String userId) {
        threadLocal.set(userId);
    }

    /**
     * 将用户信息取出
     **/
    public static String getCurrentUser() {
        return threadLocal.get();
    }


}

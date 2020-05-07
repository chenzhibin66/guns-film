package com.stylefeng.guns.rest.common.util;

/**
 * @author chenzhibin
 * @time 2020/5/7 17:06
 */
public class TokenBucket {
    private int bucketNums = 100;
    private int rate = 1;
    private int nowTokens;
    private long timestamp = getNowTime();

    private long getNowTime() {
        return System.currentTimeMillis();
    }

    private int min(int tokens) {
        if (bucketNums > tokens) {
            return tokens;
        } else {
            return bucketNums;
        }
    }

    public boolean getToken() {
        long nowTime = getNowTime();
        nowTokens = nowTokens + (int) ((nowTime - timestamp) * rate);
        nowTokens = min(nowTokens);
        timestamp = nowTime;
        if (nowTokens < 1) {
            return false;
        } else {
            nowTokens -= 1;
            return true;
        }
    }
}

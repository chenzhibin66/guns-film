package com.stylefeng.guns.core.util;

/**
 * 令牌桶算法
 *
 * @author chenzhibin
 * @time 2020/4/30 15:39
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

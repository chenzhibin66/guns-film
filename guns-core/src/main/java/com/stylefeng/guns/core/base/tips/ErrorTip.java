package com.stylefeng.guns.core.base.tips;

/**
 * 返回给前台的错误提示
 *
 * @author fengshuonan
 * @date 2016年11月12日 下午5:05:22
 */
public class ErrorTip extends Tip {

    public ErrorTip(int status, String message) {
        super();
        this.status = status;
        this.message = message;
    }
}

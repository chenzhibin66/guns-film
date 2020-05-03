package com.stylefeng.guns.api.alipay.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chenzhibin
 * @time 2020/5/2 9:23
 */
@Data
public class AliPayResultVO  implements Serializable {
    private static final long serialVersionUID = 1439951183137113897L;
    private String orderId;
    private Integer orderStatus;
    private String orderMsg;
}

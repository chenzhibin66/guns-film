package com.stylefeng.guns.api.alipay.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chenzhibin
 * @time 2020/5/2 9:22
 */
@Data
public class AliPayInfoVO implements Serializable {
    private static final long serialVersionUID = -4597523763670495557L;

    private String orderId;
    private String QRCodeAddress;
}

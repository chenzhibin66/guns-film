package com.stylefeng.guns.api.alipay;

import com.stylefeng.guns.api.alipay.vo.AliPayInfoVO;
import com.stylefeng.guns.api.alipay.vo.AliPayResultVO;

/**
 * @author chenzhibin
 * @time 2020/5/2 9:21
 */
public interface AliPayServiceAPI {

    AliPayInfoVO getQRCode(String orderId);

    AliPayResultVO getOrderStatus(String orderId);

}

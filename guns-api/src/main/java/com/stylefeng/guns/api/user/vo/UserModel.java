package com.stylefeng.guns.api.user.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户注册信息
 *
 * @author chenzhibin
 * @time 2020/3/24 14:55
 */
@Data
public class UserModel implements Serializable {
    private static final long serialVersionUID = 4695706051272785548L;
    /**
     * 用户名
     **/
    private String username;
    /**
     * 密码
     **/
    private String password;
    /**
     * 邮箱
     **/
    private String email;
    /**
     * 手机
     **/
    private String phone;
    /**
     * 地址
     **/
    private String address;
}

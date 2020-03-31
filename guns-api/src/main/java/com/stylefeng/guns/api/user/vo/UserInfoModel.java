package com.stylefeng.guns.api.user.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户基本信息
 *
 * @author chenzhibin
 * @time 2020/3/24 14:57
 */
@Data
public class UserInfoModel implements Serializable {
    private static final long serialVersionUID = -6932145279265016672L;
    /**
     * 用户uuid
     **/
    private Integer uuid;
    /**
     * 用户名
     **/
    private String username;
    /**
     * 用户昵称
     **/
    private String nickname;
    /**
     * 邮箱
     **/
    private String email;
    /**
     * 手机
     **/
    private String phone;
    /**
     * 性别
     **/
    private int sex;
    /**
     * 生日
     **/
    private String birthday;
    /**
     * 生活状态(0-单身,1-热恋中,2-已婚,3-为人父母)
     **/
    private String lifeState;
    /**
     * 个人介绍
     **/
    private String biography;
    /**
     * 用户地址
     **/
    private String address;
    /**
     * 头像url
     **/
    private String headAddress;
    /**
     * 创建时间
     **/
    private Date createTime;
    /**
     * 修改时间
     **/
    private Date updateTime;
}

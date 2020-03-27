package com.stylefeng.guns.rest.convert;

import com.stylefeng.guns.api.user.vo.UserInfoModel;
import com.stylefeng.guns.api.user.vo.UserModel;
import com.stylefeng.guns.rest.entity.UserDO;

import java.util.Date;

/**
 * User模块相关转换
 *
 * @author chenzhibin
 * @time 2020/3/24 17:55
 */
public class UserConvert {

    public static UserDO convert(UserModel userModel) {
        if (null == userModel) {
            return null;
        }
        UserDO userDO = new UserDO();
        userDO.setUserName(userModel.getUsername());
        userDO.setEmail(userModel.getEmail());
        userDO.setUserPhone(userModel.getPhone());
        userDO.setAddress(userModel.getAddress());
        userDO.setCreateTime(new Date());
        return userDO;
    }

    public static UserInfoModel convert(UserDO userDO) {
        if (null == userDO) {
            return null;
        }
        UserInfoModel userInfoModel = new UserInfoModel();
        userInfoModel.setUuid(userDO.getUuid());
        userInfoModel.setUsername(userDO.getUserName());
        userInfoModel.setNickname(userDO.getNickName());
        userInfoModel.setEmail(userDO.getEmail());
        userInfoModel.setPhone(userDO.getUserPhone());
        userInfoModel.setSex(userDO.getUserSex());
        userInfoModel.setBirthday(userDO.getBirthday());
        userInfoModel.setLifeState(String.valueOf(userDO.getLifeState()));
        userInfoModel.setBiography("" + userDO.getBiography());
        userInfoModel.setAddress(userDO.getAddress());
        userInfoModel.setHeadAddress(userDO.getHeadUrl());
        userInfoModel.setCreateTime(userDO.getCreateTime());
        userInfoModel.setUpdateTime(userDO.getUpdateTime());
        return userInfoModel;
    }

    public static UserDO convert(UserInfoModel userInfoModel) {
        if (null == userInfoModel) {
            return null;
        }
        UserDO userDO = new UserDO();
        userDO.setUuid(userInfoModel.getUuid());
        userDO.setNickName(userInfoModel.getNickname());
        userDO.setBirthday(userInfoModel.getBirthday());
        userDO.setEmail(userInfoModel.getEmail());
        userDO.setUserPhone(userInfoModel.getPhone());
        userDO.setAddress(userInfoModel.getAddress());
        userDO.setHeadUrl(userInfoModel.getHeadAddress());
        userDO.setBiography(userInfoModel.getBiography());
        userDO.setLifeState(Integer.parseInt(userInfoModel.getLifeState()));
        userDO.setUpdateTime(new Date());
        return userDO;

    }
}

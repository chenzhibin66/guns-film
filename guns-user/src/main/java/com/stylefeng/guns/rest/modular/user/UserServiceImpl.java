package com.stylefeng.guns.rest.modular.user;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.api.user.UserAPI;
import com.stylefeng.guns.api.user.vo.UserInfoModel;
import com.stylefeng.guns.api.user.vo.UserModel;
import com.stylefeng.guns.core.util.MD5Util;
import com.stylefeng.guns.rest.convert.UserConvert;
import com.stylefeng.guns.rest.entity.UserDO;
import com.stylefeng.guns.rest.mapper.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Component
/*
@Service(interfaceClass = UserAPI.class, loadbalance = "roundrobin")
*/
@Service
public class UserServiceImpl implements UserAPI {

    @Resource
    private UserRepository userRepository;

    @Override
    public int login(String username, String password) {
        UserDO userDO = new UserDO();
        userDO.setUserName(username);
        UserDO result = userRepository.selectOne(userDO);
        if (result != null && result.getUuid() > 0) {
            String md5Password = MD5Util.encrypt(password);
            if (result.getUserPwd().equals(md5Password)) {
                return result.getUuid();
            }
        }
        return 0;
    }

    @Override
    public boolean register(UserModel userModel) {
        UserDO userDO = UserConvert.convert(userModel);
        userDO.setUserPwd(MD5Util.encrypt(userModel.getPassword()));
        Integer insert = userRepository.insert(userDO);
        return (insert > 0) ? true : false;
    }

    @Override
    public boolean checkUsername(String username) {
        EntityWrapper<UserDO> wrapper = new EntityWrapper<>();
        wrapper.eq("user_name", username);
        Integer result = userRepository.selectCount(wrapper);
        if (result != null && result > 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public UserInfoModel getUserInfo(int uuid) {
        UserDO userDO = userRepository.selectById(uuid);
        return UserConvert.convert(userDO);
    }

    @Override
    public UserInfoModel updateUserInfo(UserInfoModel userInfoModel) {
        UserDO userDO = UserConvert.convert(userInfoModel);
        Integer isSuccess = userRepository.updateById(userDO);
        if (isSuccess > 0) {
            UserInfoModel newUserInfo = getUserInfo(userDO.getUuid());
            return newUserInfo;
        } else {
            return userInfoModel;
        }
    }
}

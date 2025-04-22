package com.yijun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yijun.domain.ResponseResult;
import com.yijun.domain.User;

public interface UserService extends IService<User> {
    //查询个人信息
    ResponseResult userInfo();

    //更新个人信息
    ResponseResult updateUserInfo(User user);
}

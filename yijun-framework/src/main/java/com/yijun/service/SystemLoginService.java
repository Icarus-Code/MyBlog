package com.yijun.service;

import com.yijun.domain.ResponseResult;
import com.yijun.domain.User;

public interface SystemLoginService {

    //登录
    ResponseResult login(User user);

    //退出登录
    ResponseResult logout();
}

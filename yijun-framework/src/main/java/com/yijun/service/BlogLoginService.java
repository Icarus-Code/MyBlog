package com.yijun.service;

import com.yijun.domain.ResponseResult;
import com.yijun.domain.User;


public interface BlogLoginService {
    ResponseResult login(User user);
}

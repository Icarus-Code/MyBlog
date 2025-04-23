package com.yijun.controller;

import com.yijun.annotation.mySystemlog;
import com.yijun.domain.ResponseResult;
import com.yijun.domain.User;
import com.yijun.enums.AppHttpCodeEnum;
import com.yijun.exception.SystemException;
import com.yijun.service.BlogLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogLoginController {

    @Autowired
    private BlogLoginService blogLoginService;

    @PostMapping("/login")
    @mySystemlog(xxbusinessName = "用户登录")
    public ResponseResult login(@RequestBody User user) {
        if (!StringUtils.hasText(user.getUserName())) {
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return blogLoginService.login(user);
    }

    @PostMapping("/logout")
    @mySystemlog(xxbusinessName = "用户登出")
    public ResponseResult logout() {
        return blogLoginService.logout();
    }
}
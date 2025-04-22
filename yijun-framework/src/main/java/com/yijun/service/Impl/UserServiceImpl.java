package com.yijun.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yijun.domain.ResponseResult;
import com.yijun.domain.User;
import com.yijun.mapper.UserMapper;
import com.yijun.service.UserService;
import com.yijun.utils.BeanCopyUtils;
import com.yijun.utils.SecurityUtils;
import com.yijun.vo.UserInfoVo;
import org.springframework.stereotype.Service;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    //查询个人信息
    public ResponseResult userInfo() {

        //获取当前用户的用户id。SecurityUtils是我们在yijun-framework工程写的类
        Long userId = SecurityUtils.getUserId();

        //根据用户id查询用户信息
        User user = getById(userId);

        //封装成UserInfoVo(我们在yijun-framework工程写的类)，然后返回
        UserInfoVo vo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        return ResponseResult.okResult(vo);
    }

    @Override
    //更新个人信息
    public ResponseResult updateUserInfo(User user) {
        //updateById是mybatisplus提供的方法
        updateById(user);
        return ResponseResult.okResult();
    }
}

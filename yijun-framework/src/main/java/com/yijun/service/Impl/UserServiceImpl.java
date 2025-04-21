package com.yijun.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yijun.domain.ResponseResult;
import com.yijun.domain.User;
import com.yijun.mapper.UserMapper;
import com.yijun.service.UserService;
import com.yijun.utils.BeanCopyUtils;
import com.yijun.vo.UserInfoVo;
import org.springframework.stereotype.Service;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    //已增加UserServiceImpl实现类，此时应该是没有实现方法的
}

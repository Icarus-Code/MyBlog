package com.yijun.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yijun.domain.UserRole;
import com.yijun.mapper.UserRoleMapper;
import com.yijun.service.UserRoleService;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}

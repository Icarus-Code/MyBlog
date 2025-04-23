package com.yijun.controller;

import com.yijun.domain.LoginUser;
import com.yijun.domain.Menu;
import com.yijun.domain.ResponseResult;
import com.yijun.domain.User;
import com.yijun.enums.AppHttpCodeEnum;
import com.yijun.exception.SystemException;
import com.yijun.service.MenuService;
import com.yijun.service.RoleService;
import com.yijun.service.SystemLoginService;
import com.yijun.utils.BeanCopyUtils;
import com.yijun.utils.RedisCache;
import com.yijun.utils.SecurityUtils;
import com.yijun.vo.AdminUserInfoVo;
import com.yijun.vo.RoutersVo;
import com.yijun.vo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoginController {

    @Autowired
    private SystemLoginService systemLoginService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user) {
        if (!StringUtils.hasText(user.getUserName())) {
            //提示 必须要传用户名
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return systemLoginService.login(user);
    }

    //---------------------------查询(超级管理员|非超级管理员)的权限和角色信息-----------------------------

    @GetMapping("/getInfo")
    //AdminUserInfoVo是我们在yijun-framework工程写的类
    public ResponseResult<AdminUserInfoVo> getInfo() {
        //获取当前登录的用户。SecurityUtils是我们在yijun-framework写的类
        LoginUser loginUser = SecurityUtils.getLoginUser();
        //根据用户id查询权限信息
        List<String> perms = menuService.selectPermsByUserId(loginUser.getUser().getId());
        //根据用户id查询角色信息
        List<String> roleKeyList = roleService.selectRoleKeyByUserId(loginUser.getUser().getId());

        //获取用户信息
        User user = loginUser.getUser();
        //BeanCopyUtils是我们在yijun-framework写的类
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);

        //封装响应返回
        AdminUserInfoVo adminUserInfoVo = new AdminUserInfoVo(perms, roleKeyList, userInfoVo);
        return ResponseResult.okResult(adminUserInfoVo);
    }

    //-------------------------------------查询路由信息(权限菜单)--------------------------------------

    @GetMapping("/getRouters")
    //RoutersVo是我们在yijun-framework工程写的类
    public ResponseResult<RoutersVo> getRouters() {
        //获取用户id
        Long userId = SecurityUtils.getUserId();

        //根据用户id来查询menu(权限菜单)。要求查询结果是tree的形式，也就是子父菜单树
        List<Menu> menus = menuService.selectRouterMenuTreeByUserId(userId);
        //封装响应返回
        return ResponseResult.okResult(new RoutersVo(menus));
    }

    //--------------------------退出登录的接口(不建议直接写在controller)--------------------------------

    //@Autowired
    //private RedisCache redisCache;
    //
    //@PostMapping("/user/logout")
    //public ResponseResult logout(){
    //    //获取当前登录的用户id
    //    Long userId = SecurityUtils.getUserId();
    //
    //    //删除redis中对应的值
    //    redisCache.deleteObject("login:"+userId);
    //    return ResponseResult.okResult();
    //}

    //-----------------------------退出登录的接口(我们写在service比较好---------------------------------

    @Autowired
    private RedisCache redisCache;

    @PostMapping("/user/logout")
    public ResponseResult logout() {
        //退出登录
        return systemLoginService.logout();
    }
}

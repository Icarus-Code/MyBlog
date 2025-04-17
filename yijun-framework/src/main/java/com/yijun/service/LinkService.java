package com.yijun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yijun.domain.Link;
import com.yijun.domain.ResponseResult;

public interface LinkService extends IService<Link> {
    //查询友链
    ResponseResult getAllLink();
}

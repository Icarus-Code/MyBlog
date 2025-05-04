package com.yijun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yijun.domain.Link;
import com.yijun.domain.ResponseResult;
import com.yijun.vo.PageVo;

public interface LinkService extends IService<Link> {

    //查询友链
    ResponseResult getAllLink();

    //分页查询友链
    PageVo selectLinkPage(Link link, Integer pageNum, Integer pageSize);

}

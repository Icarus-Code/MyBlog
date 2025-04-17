package com.yijun.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yijun.constants.SystemCanstants;
import com.yijun.domain.Link;
import com.yijun.domain.ResponseResult;
import com.yijun.mapper.LinkMapper;
import com.yijun.service.LinkService;
import com.yijun.utils.BeanCopyUtils;
import com.yijun.vo.LinkVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("linkService")
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    @Override
    public ResponseResult getAllLink() {

        //查询所有审核通过的友链
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        //要求查的是友链表status字段的值为0，注意SystemCanstants是我们写的一个常量类，用来解决字面值的书写问题
        queryWrapper.eq(Link::getStatus, SystemCanstants.LINK_STATUS_NORMAL);
        List<Link> links = list(queryWrapper);
        //把最后的查询结果封装成LinkListVo(我们写的实体类)。BeanCopyUtils是我们写的工具类
        List<LinkVo> linkVos = BeanCopyUtils.copyBeanList(links, LinkVo.class);

        //封装响应返回。ResponseResult是我们在yijun-framework工程的domain目录写的实体类
        return ResponseResult.okResult(linkVos);
    }
}

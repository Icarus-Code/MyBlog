package com.yijun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yijun.domain.ResponseResult;
import com.yijun.domain.Tag;
import com.yijun.dto.TagListDto;
import com.yijun.vo.PageVo;


public interface TagService extends IService<Tag> {
    //查询标签列表
    ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto);
}

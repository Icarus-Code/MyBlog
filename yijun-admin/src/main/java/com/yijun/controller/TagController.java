package com.yijun.controller;

import com.yijun.domain.ResponseResult;
import com.yijun.dto.TagListDto;
import com.yijun.service.TagService;
import com.yijun.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/content/tag")
public class TagController {

    @Autowired
    //TagService是我们在yijun-framework工程的接口
    private TagService tagService;

    //查询标签列表
    @GetMapping("/list")
    //ResponseResult是我们在yijun-framework工程的实体类
    public ResponseResult<PageVo> list(Integer pageNum, Integer pageSize, TagListDto tagListDto) {
        //pageTagList是我们在yijun-framework工程写的方法
        return tagService.pageTagList(pageNum, pageSize, tagListDto);
    }
}

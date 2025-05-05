package com.yijun.controller;

import com.yijun.domain.ResponseResult;
import com.yijun.dto.AddTagDto;
import com.yijun.dto.EditTagDto;
import com.yijun.dto.TagListDto;
import com.yijun.service.TagService;
import com.yijun.utils.BeanCopyUtils;
import com.yijun.vo.PageVo;
import com.yijun.domain.Tag;
import com.yijun.vo.TagVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    //-------------------------------新增标签------------------------------------

    @PostMapping
    public ResponseResult addTag(@RequestBody AddTagDto tagDto) {
        Tag tag = BeanCopyUtils.copyBean(tagDto, Tag.class);
        tagService.save(tag);
        return ResponseResult.okResult();
    }

    //-------------------------------删除标签------------------------------------

    @DeleteMapping
    public ResponseResult deleteTag(@RequestParam("ids") String ids) {
        if (!ids.contains(",")) {
            tagService.deleteTag(Long.valueOf(ids));
        } else {
            String[] idArr = ids.split(",");
            for (String id : idArr) {
                tagService.deleteTag(Long.valueOf(id));
            }
        }
        return ResponseResult.okResult();
    }

    //-------------------------------修改标签------------------------------------


    @GetMapping("/{id}")
    //①根据标签的id来查询标签
    public ResponseResult getLableById(@PathVariable Long id) {
        return tagService.getLableById(id);
    }

    @PutMapping
    //②根据标签的id来修改标签
    public ResponseResult updateById(@RequestBody TagVo tagVo) {
        return tagService.myUpdateById(tagVo);
    }


    //---------------------------写博文-查询文章标签的接口---------------------------

    @GetMapping("/listAllTag")
    public ResponseResult listAllTag() {
        List<TagVo> list = tagService.listAllTag();
        return ResponseResult.okResult(list);
    }
}

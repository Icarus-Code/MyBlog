package com.yijun.controller;

import com.yijun.domain.ResponseResult;
import com.yijun.dto.AddTagDto;
import com.yijun.dto.EditTagDto;
import com.yijun.dto.TagListDto;
import com.yijun.service.TagService;
import com.yijun.utils.BeanCopyUtils;
import com.yijun.vo.PageVo;
import com.yijun.domain.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseResult add(@RequestBody AddTagDto tagDto) {
        Tag tag = BeanCopyUtils.copyBean(tagDto, Tag.class);
        tagService.save(tag);
        return ResponseResult.okResult();
    }

    @DeleteMapping
    public ResponseResult remove(@RequestParam(value = "ids") String ids) {
        if (!ids.contains(",")) {
            tagService.removeById(ids);
        } else {
            String[] idArr = ids.split(",");
            for (String id : idArr) {
                tagService.removeById(id);
            }
        }
        return ResponseResult.okResult();
    }

    ////-------------------------------修改标签------------------------------------

    @GetMapping("/{id}")
    //①根据标签的id来查询标签
    public ResponseResult getInfo(@PathVariable(value = "id") Long id) {
        Tag tag = tagService.getById(id);
        return ResponseResult.okResult(tag);
    }

    @PutMapping
    //②根据标签的id来修改标签
    public ResponseResult edit(@RequestBody EditTagDto tagDto) {
        Tag tag = BeanCopyUtils.copyBean(tagDto, Tag.class);
        tagService.updateById(tag);
        return ResponseResult.okResult();
    }
}

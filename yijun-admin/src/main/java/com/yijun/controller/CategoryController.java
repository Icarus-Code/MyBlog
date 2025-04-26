package com.yijun.controller;

import com.yijun.domain.Category;
import com.yijun.domain.ResponseResult;
import com.yijun.dto.CategoryDto;
import com.yijun.service.CategoryService;
import com.yijun.utils.BeanCopyUtils;
import com.yijun.vo.CategoryVo;
import com.yijun.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/content/category")
public class CategoryController {

    //---------------------------写博文-查询文章分类的接口--------------------------------

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/listAllCategory")
    public ResponseResult listAllCategory() {
        List<CategoryVo> list = categoryService.listAllCategory();
        return ResponseResult.okResult(list);
    }

    //----------------------------分页查询文章的分类列表---------------------------------

    @GetMapping("/list")
    public ResponseResult list(Category category, Integer pageNum, Integer pageSize) {
        PageVo pageVo = categoryService.selectCategoryPage(category, pageNum, pageSize);
        return ResponseResult.okResult(pageVo);
    }

    //-----------------------------增加文章的分类--------------------------------------

    @PostMapping
    public ResponseResult add(@RequestBody CategoryDto categoryDto) {
        Category category = BeanCopyUtils.copyBean(categoryDto, Category.class);
        categoryService.save(category);
        return ResponseResult.okResult();
    }

    //-----------------------------修改文章的分类--------------------------------------

    @DeleteMapping
    public ResponseResult remove(@RequestParam(value = "ids") String ids) {
        if (!ids.contains(",")) {
            categoryService.removeById(ids);
        } else {
            String[] idArr = ids.split(",");
            for (String id : idArr) {
                categoryService.removeById(id);
            }
        }
        return ResponseResult.okResult();
    }

    //-----------------------------修改文章的分类--------------------------------------

    @GetMapping(value = "/{id}")
    //①根据分类的id来查询分类
    public ResponseResult getInfo(@PathVariable(value = "id") Long id) {
        Category category = categoryService.getById(id);
        return ResponseResult.okResult(category);
    }

    @PutMapping
    //②根据分类的id来修改分类
    public ResponseResult edit(@RequestBody Category category) {
        categoryService.updateById(category);
        return ResponseResult.okResult();
    }
}

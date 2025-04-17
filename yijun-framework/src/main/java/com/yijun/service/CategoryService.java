package com.yijun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yijun.domain.Category;
import com.yijun.domain.ResponseResult;

public interface CategoryService extends IService<Category> {
    //查询文章分类的接口
    ResponseResult getCategoryList();
}

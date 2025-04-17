package com.yijun.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.yijun.domain.Category;
import com.yijun.domain.ResponseResult;
import com.yijun.vo.ArticleListVo;

import java.util.List;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
    List<ArticleListVo> searchList(String search);
}

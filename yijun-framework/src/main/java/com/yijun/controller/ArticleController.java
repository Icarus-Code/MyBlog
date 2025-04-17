package com.yijun.controller;

import com.yijun.domain.Article;
import com.yijun.domain.Category;
import com.yijun.domain.ResponseResult;
import com.yijun.mapper.CategoryMapper;
import com.yijun.service.ArticleService;
import com.yijun.vo.ArticleListVo;
import com.yijun.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    //注入公共模块的ArticleService接口
    private ArticleService articleService;

    @Autowired
    //注入公共模块的CategoryMapper接口
    private CategoryMapper categoryMapper;

    //----------------------------------测试mybatisPlus---------------------------------

    @GetMapping("/list")
    //Article是公共模块的实体类
    public List<Article> test(){
        //查询数据库的所有数据
        return articleService.list();
    }

    //----------------------------------测试统一响应格式-----------------------------------

    @GetMapping("/hotArticleList")
    //ResponseResult是yijun-framework工程的domain目录的类
    public ResponseResult hotArticleList(){
        //查询热门文章，封装成ResponseResult返回
        ResponseResult result = articleService.hotArticleList();
        return result;
    }

    //----------------------------------分页查询文章的列表---------------------------------

    @GetMapping("/articleList")
    //ResponseResult是yijun-framework工程的domain目录的类
    public ResponseResult articleList(Integer pageNum,Integer pageSize,Long categoryId,@RequestParam(required = false) String search){
        if (search!=""){
            List<ArticleListVo> articleListVo = categoryMapper.searchList(search);
            if (articleListVo.size()!=0) {
                PageVo pageVo = new PageVo(articleListVo,articleListVo.get(0).getTotal());
                List<ArticleListVo> list = pageVo.getRows();
                for (ArticleListVo one : list) {
                    Category category = categoryMapper.selectById(one.getId());
                    one.setCategoryName(category.getName());
                    one.setCategoryId(category.getId());
                }

                return ResponseResult.okResult(pageVo);
            }

        }
        ResponseResult responseResult = articleService.articleList(pageNum, pageSize, categoryId);
        return responseResult;
    }

}

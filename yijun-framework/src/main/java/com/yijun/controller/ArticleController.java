package com.yijun.controller;

import com.yijun.annotation.mySystemlog;
import com.yijun.domain.Article;
import com.yijun.domain.Category;
import com.yijun.domain.ResponseResult;
import com.yijun.mapper.CategoryMapper;
import com.yijun.service.ArticleService;
import com.yijun.vo.ArticleListVo;
import com.yijun.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryMapper categoryMapper;

    //----------------------------------测试mybatisPlus---------------------------------
    @GetMapping("/list")
    @mySystemlog(xxbusinessName = "获取所有文章列表")
    public List<Article> test(){
        return articleService.list();
    }

    //----------------------------------测试统一响应格式-----------------------------------
    @GetMapping("/hotArticleList")
    @mySystemlog(xxbusinessName = "获取热门文章列表")
    public ResponseResult hotArticleList(){
        ResponseResult result = articleService.hotArticleList();
        return result;
    }

    //----------------------------------分页查询文章的列表---------------------------------
    @GetMapping("/articleList")
    @mySystemlog(xxbusinessName = "分页查询文章列表")
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

    //------------------------------------查询文章详情------------------------------------
    @GetMapping("/{id}")
    @mySystemlog(xxbusinessName = "查询文章详情")
    public ResponseResult getArticleDetail(@PathVariable("id") Long id) {
        return articleService.getArticleDetail(id);
    }

    //------------------------------------更新文章的阅读次数---------------------------------
    @PutMapping("/updateViewCount/{id}")
    @mySystemlog(xxbusinessName = "根据文章id从mysql查询文章")//接口描述，用于'日志记录'功能
    public ResponseResult updateViewCount(@PathVariable("id") Long id) {
        return articleService.updateViewCount(id);
    }
}

package com.yijun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yijun.domain.Comment;
import com.yijun.domain.ResponseResult;

public interface CommentService extends IService<Comment> {

    //查询评论区的评论
    ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize);

    //在文章的评论区发送评论
    ResponseResult addComment(Comment comment);
}

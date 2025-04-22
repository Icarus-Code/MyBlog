package com.yijun.controller;

import com.yijun.annotation.mySystemlog;
import com.yijun.constants.SystemCanstants;
import com.yijun.domain.Comment;
import com.yijun.domain.ResponseResult;
import com.yijun.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("commentList")
    @mySystemlog(xxbusinessName = "获取文章评论列表")
    public ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize) {
        return commentService.commentList(SystemCanstants.ARTICLE_COMMENT, articleId, pageNum, pageSize);
    }

    @PostMapping
    @mySystemlog(xxbusinessName = "添加文章评论")
    public ResponseResult addComment(@RequestBody Comment comment) {
        return commentService.addComment(comment);
    }

    @GetMapping("/linkCommentList")
    @mySystemlog(xxbusinessName = "获取友链评论列表")
    public ResponseResult linkCommentList(Integer pageNum, Integer pageSize) {
        return commentService.commentList(SystemCanstants.LINK_COMMENT, null, pageNum, pageSize);
    }
}
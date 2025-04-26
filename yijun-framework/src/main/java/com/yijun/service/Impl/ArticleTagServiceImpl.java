package com.yijun.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yijun.domain.ArticleTag;
import com.yijun.mapper.ArticleTagMapper;
import com.yijun.service.ArticleTagService;
import org.springframework.stereotype.Service;

@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {

}

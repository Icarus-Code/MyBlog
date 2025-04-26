package com.yijun.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yijun.mapper.ArticleVoMapper;
import com.yijun.service.ArticleVoService;
import com.yijun.vo.ArticleVo;
import org.springframework.stereotype.Service;

@Service
public class ArticleVoServiceImpl extends ServiceImpl<ArticleVoMapper, ArticleVo> implements ArticleVoService {

}

package com.yijun.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yijun.domain.Tag;
import com.yijun.mapper.TagMapper;
import com.yijun.service.TagService;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

}

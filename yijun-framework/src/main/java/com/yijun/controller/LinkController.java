package com.yijun.controller;

import com.yijun.domain.ResponseResult;
import com.yijun.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("link")
public class LinkController {

    @Autowired
    //LinkService是我们在yijun-framework工程写的接口
    private LinkService linkService;


    @GetMapping("/getAllLink")
    //ResponseResult是我们在yijun-framework工程写的实体类
    public ResponseResult getAllLink() {
        return linkService.getAllLink();
    }

}

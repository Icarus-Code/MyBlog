package com.yijun.controller;

import com.yijun.domain.ResponseResult;
import com.yijun.service.OssUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {

    @Autowired
    //UploadService是我们在yijun-framework写的接口
    private OssUploadService ossUploadService;

    @PostMapping("/upload")
    //MultipartFile是spring提供的接口，ResponseResult是我们在yijun-framework写的实体类
    public ResponseResult uploadImg(MultipartFile img) {
        //图片上传到七牛云
        return ossUploadService.uploadImg(img);
    }
}

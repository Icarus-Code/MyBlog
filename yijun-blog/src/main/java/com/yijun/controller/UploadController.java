package com.yijun.controller;

import com.yijun.annotation.mySystemlog;
import com.yijun.domain.ResponseResult;
import com.yijun.service.OssUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {

    @Autowired
    private OssUploadService ossUploadService;

    @PostMapping("/upload")
    @mySystemlog(xxbusinessName = "上传图片到七牛云")
    public ResponseResult uploadImg(MultipartFile img) {
        return ossUploadService.uploadImg(img);
    }
}
package com.yijun.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    //分类名
    private String name;
    //描述
    private String description;
    //状态0:正常,1禁用
    private String status;

}

/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.controller;

import com.unisinsight.framework.uuv.dto.response.ApplicationCategoryResDTO;
import com.unisinsight.framework.uuv.service.ApplicationCategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 应用类型Controller层
 *
 * @author dengxiangtian [KF.dengxiangtian@h3c.com]
 * @date 2018/9/6 17:12
 * @since 1.0
 */
@RestController
@RequestMapping("/v0.1/appcategorys")
public class ApplicationCategoryController {
    @Resource
    private ApplicationCategoryService appCategoryService;

    /**
     * 获取所有应用类型
     * @return
     */
    @GetMapping
    public List<ApplicationCategoryResDTO> getAllAppCatgory() {
      return appCategoryService.findAll();
    }

}

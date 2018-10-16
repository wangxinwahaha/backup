/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.controller;

import com.unisinsight.framework.uuv.dto.response.TitleResDTO;
import com.unisinsight.framework.uuv.service.TitleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 职级Controller层
 *
 * @author fenglingzi [KF.fenglingzi@h3c.com]
 * @date 2018/9/7 9:40
 * @since 1.0
 */
@RestController
@RequestMapping("/v0.1/titles")
public class TitleController {
    @Resource
    private TitleService titleService;

    /**
     * 获取职级列表
     * @return
     */
    @GetMapping()
    public List<TitleResDTO> findAll() {
        return titleService.findAll();
    }

}

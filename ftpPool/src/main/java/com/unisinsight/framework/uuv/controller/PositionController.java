/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.controller;

import com.unisinsight.framework.uuv.dto.response.PositionResDTO;
import com.unisinsight.framework.uuv.service.PositionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 岗位Controller层
 *
 * @author fenglingzi [KF.fenglingzi@h3c.com]
 * @date 2018/9/6 17:12
 * @since 1.0
 */
@RestController
@RequestMapping("/v0.1/positions")
public class PositionController {
    @Resource
    private PositionService positionService;

    /**
     * 获取岗位列表
     * @return
     */
    @GetMapping()
    public List<PositionResDTO> findAll() {
        return positionService.findAll();
    }

}

/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.service.impl;

import com.github.pagehelper.PageHelper;
import com.unisinsight.framework.uuv.dto.response.PositionResDTO;
import com.unisinsight.framework.uuv.manager.PositionManager;
import com.unisinsight.framework.uuv.model.PositionDO;
import com.unisinsight.framework.uuv.service.PositionService;

import com.unisinsight.framework.uuv.common.utils.BeanConvert;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * description 岗位实现类
 *
 * @author fenglingzi [KF.fenglingzi@h3c.com]
 * @date 2018/9/7 10:21
 * @since 1.0
 */
@Service
public class PositionServiceImpl  implements PositionService {
    @Resource
    private PositionManager positionManager;

    /**
     * 获取岗位列表
     * @return
     */
    @Override
    public List<PositionResDTO> findAll() {
        PageHelper.orderBy("display_order asc");
        List<PositionDO> positionList = positionManager.selectAll();

        return BeanConvert.convertList(positionList, PositionResDTO.class);
    }

}

/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.service.impl;

import com.github.pagehelper.PageHelper;
import com.unisinsight.framework.uuv.dto.response.TitleResDTO;
import com.unisinsight.framework.uuv.manager.TitleManager;
import com.unisinsight.framework.uuv.model.TitleDO;
import com.unisinsight.framework.uuv.service.TitleService;

import com.unisinsight.framework.uuv.common.utils.BeanConvert;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * description 职级实现类
 *
 * @author fenglingzi [KF.fenglingzi@h3c.com]
 * @date 2018/9/7 10:17
 * @since 1.0
 */
@Service
public class TitleServiceImpl  implements TitleService {
    @Resource
    private TitleManager titleManager;

    /**
     * 获取职级列表
     * @return
     */
    @Override
    public List<TitleResDTO> findAll() {
        PageHelper.orderBy("display_order asc");

        List<TitleDO> titleList = titleManager.selectAll();

        return BeanConvert.convertList(titleList, TitleResDTO.class);
    }
}

/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.service.impl;

import com.unisinsight.framework.uuv.dto.response.ApplicationCategoryResDTO;
import com.unisinsight.framework.uuv.mapper.ApplicationCategoryMapper;
import com.unisinsight.framework.uuv.model.ApplicationCategoryDO;
import com.unisinsight.framework.uuv.service.ApplicationCategoryService;

import com.unisinsight.framework.uuv.common.utils.BeanConvert;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 应用类型实现类
 *
 * @author dengxiangtian [KF.dengxiangtian@h3c.com]
 * @date 2018/9/7 10:19
 * @since 1.0
 */
@Service
public class ApplicationCategoryServiceImpl  implements ApplicationCategoryService {
    @Resource
    private ApplicationCategoryMapper applicationCategoryMapper;

    @Override
    public ApplicationCategoryResDTO findById(Integer id) {
        ApplicationCategoryDO applicationCategoryDO = applicationCategoryMapper.selectByPrimaryKey(id);
        return BeanConvert.convert(applicationCategoryDO, ApplicationCategoryResDTO.class);
    }

    @Override
    public List<ApplicationCategoryResDTO> findAll() {
        List<ApplicationCategoryDO> applicationCategoryDOList = applicationCategoryMapper.selectAll();
        return BeanConvert.convertList(applicationCategoryDOList, ApplicationCategoryResDTO.class);
    }

}

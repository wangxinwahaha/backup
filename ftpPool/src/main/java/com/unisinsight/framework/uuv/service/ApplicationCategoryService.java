/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.service;
import com.unisinsight.framework.uuv.dto.response.ApplicationCategoryResDTO;

import java.util.List;

/**
 * description 应用目录接口
 *
 * @author dengxiangtian [KF.dengxiangtian@h3c.com]
 * @date 2018/9/7 10:15
 * @since 1.0
 */
public interface ApplicationCategoryService  {

    /**
    * 通过ID查找
    * @param id
    * @return
    */
    ApplicationCategoryResDTO findById(Integer id);

    /**
    * 获取所有
    * @return
    */
    List<ApplicationCategoryResDTO> findAll();
}

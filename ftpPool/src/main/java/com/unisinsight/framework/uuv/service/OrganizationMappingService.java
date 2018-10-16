/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.service;
import com.unisinsight.framework.uuv.dto.request.OrganizationMappingReqDTO;
import com.unisinsight.framework.uuv.dto.response.OrganizationMappingResDTO;

import java.util.List;

/**
 * description 组织关系接口
 *
 * @author yangjing [yang_jing@h3c.com]
 * @date 2018/9/7 10:13
 * @since 1.0
 */
public interface OrganizationMappingService  {

/**
     * 新增
     * @param reqDTO
     * @return
     */
    Integer save(OrganizationMappingReqDTO reqDTO);


    /**
    * 通过主键删除
    * @param id
    * @return
    */
    Integer deleteById(Integer id);

    /**
    * 更新
    * @param updateDTO
    * @return
    */
    Integer update(OrganizationMappingReqDTO updateDTO);

    /**
    * 通过ID查找
    * @param id
    * @return
    */
    OrganizationMappingResDTO findById(Integer id);

    /**
    * 获取所有
    * @return
    */
    List<OrganizationMappingResDTO> findAll();


}

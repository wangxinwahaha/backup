/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.service;
import com.unisinsight.framework.uuv.dto.request.OrganizationPreferenceReqDTO;
import com.unisinsight.framework.uuv.dto.response.OrganizationPreferenceResDTO;

import java.util.List;

/**
 * description 组织拓展字段接口
 *
 * @author yangjing [yang_jing@h3c.com]
 * @date 2018/9/7 10:12
 * @since 1.0
 */
public interface OrganizationPreferenceService  {

/**
     * 新增
     * @param reqDTO
     * @return
     */
    Integer save(OrganizationPreferenceReqDTO reqDTO);


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
    Integer update(OrganizationPreferenceReqDTO updateDTO);

    /**
    * 通过ID查找
    * @param id
    * @return
    */
    OrganizationPreferenceResDTO findById(Integer id);

    /**
    * 获取所有
    * @return
    */
    List<OrganizationPreferenceResDTO> findAll();


}

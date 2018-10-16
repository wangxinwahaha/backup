/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.mapper;

import com.unisinsight.framework.uuv.base.Mapper;
import com.unisinsight.framework.uuv.model.OrganizationMappingDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 组织关系mapper层
 *
 * @author yangjing [yang_jing@h3c.com]
 * @date 2018/9/7 9:56
 * @since 1.0
 */
public interface OrganizationMappingMapper extends Mapper<OrganizationMappingDO> {

    /**
     * 通过type、parentId、subId删除用户
     * @param parentId
     * @param subId
     */
    void deleteByTypeAndOrgIdAndSubId(@Param(value = "parentId") Integer parentId,
                                      @Param(value = "subId") Integer subId);
}
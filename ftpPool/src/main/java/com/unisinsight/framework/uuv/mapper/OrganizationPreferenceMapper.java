/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.mapper;

import com.unisinsight.framework.uuv.base.Mapper;
import com.unisinsight.framework.uuv.model.OrganizationPreferenceDO;

/**
 * 组织扩展字段mapper层
 *
 * @author yangjing [yang_jing@h3c.com]
 * @date 2018/9/7 9:57
 * @since 1.0
 */
public interface OrganizationPreferenceMapper extends Mapper<OrganizationPreferenceDO> {

    /**
     * 通过组织id删除组织
     * @param orgId
     */
    void deleteOrganizationByOrgId(Integer orgId);

}
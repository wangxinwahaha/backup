/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.model;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * 组织关系实体类
 *
 * @author yangjing [yang_jing@h3c.com]
 * @date 2018/9/7 10:06
 * @since 1.0
 */
@Table(name = "uuv.organization_user_mapping")
public class OrganizationMappingDO {

    @Column(name = "org_id")
    private Integer orgId;

    @Column(name = "user_id")
    private Integer userId;

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "OrganizationMappingDO{" +
                "orgId=" + orgId +
                ", userId=" + userId +
                '}';
    }
}
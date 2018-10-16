/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.dto.response;

import java.io.Serializable;

/**
 * description 数据响应对象
 *
 * @author longjiang [KF.longjiang@h3c.com]
 * @date 2018/9/7 10:03
 * @since 1.0
 */
public class OrganizationTreeResDTO implements Serializable {
    private Integer orgId;

    private Integer parentId;

    private String orgCode;

    private String orgName;

    private Float displayOrder;

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Float getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Float displayOrder) {
        this.displayOrder = displayOrder;
    }

    @Override
    public String toString() {
        return "OrganizationTreeResDTO{" +
                "orgId=" + orgId +
                ", parentId=" + parentId +
                ", orgCode='" + orgCode + '\'' +
                ", orgName='" + orgName + '\'' +
                ", displayOrder=" + displayOrder +
                '}';
    }
}

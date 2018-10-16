/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.dto.response;

import java.io.Serializable;

/**
 * description
 *
 * @author yangjing [yang_jing@h3c.com]
 * @date 2018/9/7 10:01
 * @since 1.0
 */
public class OrganizationMappingResDTO implements Serializable {

    private Integer parentId;

    private Integer subId;

    private Integer type;

    private Float displayOrder;

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getSubId() {
        return subId;
    }

    public void setSubId(Integer subId) {
        this.subId = subId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Float getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Float displayOrder) {
        this.displayOrder = displayOrder;
    }

    @Override
    public String toString() {
        return "OrganizationMappingResDTO{" +
                "parentId=" + parentId +
                ", subId=" + subId +
                ", type=" + type +
                ", displayOrder=" + displayOrder +
                '}';
    }
}

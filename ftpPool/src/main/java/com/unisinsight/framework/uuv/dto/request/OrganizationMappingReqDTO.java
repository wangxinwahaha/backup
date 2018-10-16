/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.dto.request;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * description 数据请求对象
 *
 * @author liuran [KF.liuran@h3c.com]
 * @date 2018/9/7 9:52
 * @since 1.0
 */
public class OrganizationMappingReqDTO implements Serializable {

    /**
     * 上级组织ID
     */
    @NotEmpty
    private Integer parentId;

    /**
     * 当前组织ID
     */
    @NotEmpty
    private Integer subId;

    /**
     * 0代表组织，1代表用户
     */
    @NotEmpty
    private Integer type;

    /**
     * 显示顺序
     */
    private Float displayOrder;

    /**
     * 获取上级组织ID
     *
     * @return parent_id - 上级组织ID
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置上级组织ID
     *
     * @param parentId 上级组织ID
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取当前组织ID
     *
     * @return sub_id - 当前组织ID
     */
    public Integer getSubId() {
        return subId;
    }

    /**
     * 设置当前组织ID
     *
     * @param subId 当前组织ID
     */
    public void setSubId(Integer subId) {
        this.subId = subId;
    }

    /**
     * 获取0代表组织，1代表用户
     *
     * @return type - 0代表组织，1代表用户
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置0代表组织，1代表用户
     *
     * @param type 0代表组织，1代表用户
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取显示顺序
     *
     * @return display_order - 显示顺序
     */
    public Float getDisplayOrder() {
        return displayOrder;
    }

    /**
     * 设置显示顺序
     *
     * @param displayOrder 显示顺序
     */
    public void setDisplayOrder(Float displayOrder) {
        this.displayOrder = displayOrder;
    }

    @Override
    public String toString() {
        return "OrganizationMappingReqDTO{" +
                "parentId=" + parentId +
                ", subId=" + subId +
                ", type=" + type +
                ", displayOrder=" + displayOrder +
                '}';
    }
}

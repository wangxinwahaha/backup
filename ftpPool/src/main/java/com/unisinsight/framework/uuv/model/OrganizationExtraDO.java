/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 组织扩展实体类
 *
 * @author longjiang [KF.longjiang@h3c.com]
 * @date 2018/9/7 10:06
 * @since 1.0
 */
public class OrganizationExtraDO implements Comparable<OrganizationExtraDO> {
    private Integer parentId;

    private Integer orgId;

    private String orgCode;

    private String orgName;

    private Integer nodeLevel;

    private Float displayOrder;

    private List<OrganizationExtraDO> child;

    public List<OrganizationExtraDO> getChild() {
        return child;
    }

    public void addChild(OrganizationExtraDO org){
        if (child == null){
            child = new ArrayList<>();
        }
        child.add(org);
    }

    public void setChild(List<OrganizationExtraDO> child) {
        this.child = child;
    }

    public Float getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Float displayOrder) {
        this.displayOrder = displayOrder;
    }

    public Integer getNodeLevel() {
        return nodeLevel;
    }

    public void setNodeLevel(Integer nodeLevel) {
        this.nodeLevel = nodeLevel;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
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

    @Override
    public int compareTo(OrganizationExtraDO o) {
        if (this.displayOrder >= o.getDisplayOrder()) {
            return 1;
        }
        return -1;
    }

    @Override
    public String toString() {
        return "OrganizationExtraDO{" +
                "parentId=" + parentId +
                ", orgId=" + orgId +
                ", orgCode='" + orgCode + '\'' +
                ", orgName='" + orgName + '\'' +
                ", nodeLevel=" + nodeLevel +
                ", displayOrder=" + displayOrder +
                ", child=" + child +
                '}';
    }
}

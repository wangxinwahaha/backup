/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.model;

import javax.persistence.*;

/**
 * 角色权限关系实体类
 *
 * @author fenglingzi [KF.fenglingzi@h3c.com]
 * @date 2018/9/7 10:12
 * @since 1.0
 */
@Table(name = "uuv.role_privilege_mapping")
public class RolePrivilegeMappingDO {

    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "privilege_id")
    private Integer privilegeId;

    @Column(name = "display_order")
    private Float displayOrder;

    /**
     * @return role_id
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * @return privilege_id
     */
    public Integer getPrivilegeId() {
        return privilegeId;
    }

    /**
     * @param privilegeId
     */
    public void setPrivilegeId(Integer privilegeId) {
        this.privilegeId = privilegeId;
    }

    /**
     * @return display_order
     */
    public Float getDisplayOrder() {
        return displayOrder;
    }

    /**
     * @param displayOrder
     */
    public void setDisplayOrder(Float displayOrder) {
        this.displayOrder = displayOrder;
    }

    @Override
    public String toString() {
        return "RolePrivilegeMappingDO{" +
                "roleId=" + roleId +
                ", privilegeId=" + privilegeId +
                ", displayOrder=" + displayOrder +
                '}';
    }
}
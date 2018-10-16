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
 * @date 2018/9/7 10:04
 * @since 1.0
 */
public class RoleResDTO implements Serializable {
    private Integer roleId;

    private String roleCode;

    private String roleName;

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "RoleResDTO{" +
                "roleId=" + roleId +
                ", roleCode='" + roleCode + '\'' +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}

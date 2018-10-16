/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.dto.request;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * description 数据请求对象
 *
 * @author longjiang [KF.longjiang@h3c.com]
 * @date 2018/9/7 9:57
 * @since 1.0
 */
public class RoleReqDTO extends BaseReqDTO {
    @Length(max = 16, message = "角色编号长度超过16")
    private String roleCode;

    @Length(max = 32, message = "角色名称长度超过32")
    @NotBlank(message = "角色名称为空")
    private String roleName;

    @Length(max = 200, message = "描述长度超过200")
    private String description;

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return role_code
     */
    public String getRoleCode() {
        return roleCode;
    }

    /**
     * @param roleCode
     */
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    /**
     * @return role_name
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * @param roleName
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "RoleReqDTO{" +
                "roleCode='" + roleCode + '\'' +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}

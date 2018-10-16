/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.dto.response;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * description
 *
 * @author fenglingzi [KF.fenglingzi@h3c.com]
 * @date 2018/09/08 11:13
 * @since 1.0
 */
public class UserRoleMappingResDTO implements Serializable {

    private Integer userId;

    private Integer roleId;

    /**
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

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

    @Override
    public String toString() {
        return "UserRoleMappingDO{" +
                "userId=" + userId +
                ", roleId=" + roleId +
                '}';
    }
}

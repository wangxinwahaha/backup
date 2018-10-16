/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.model;

import java.util.Date;
import javax.persistence.*;

/**
 * 角色实体类
 *
 * @author longjiang [KF.longjiang@h3c.com]
 * @date 2018/9/7 10:12
 * @since 1.0
 */
@Table(name = "uuv.role")
public class RoleDO {
    @Id
    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "app_id")
    private Integer appId;

    @Column(name = "role_code")
    private String roleCode;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_at")
    private Date updatedAt;

    private String description;

    private Integer status;

    private Integer deleted;

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
     * @return app_id
     */
    public Integer getAppId() {
        return appId;
    }

    /**
     * @param appId
     */
    public void setAppId(Integer appId) {
        this.appId = appId;
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

    /**
     * @return created_at
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return created_by
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return updated_by
     */
    public String getUpdatedBy() {
        return updatedBy;
    }

    /**
     * @param updatedBy
     */
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * @return updated_at
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @param updatedAt
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return deleted
     */
    public Integer getDeleted() {
        return deleted;
    }

    /**
     * @param deleted
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "RoleDO{" +
                "roleId=" + roleId +
                ", appId=" + appId +
                ", roleCode='" + roleCode + '\'' +
                ", roleName='" + roleName + '\'' +
                ", createdAt=" + createdAt +
                ", createdBy='" + createdBy + '\'' +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedAt=" + updatedAt +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", deleted=" + deleted +
                '}';
    }
}